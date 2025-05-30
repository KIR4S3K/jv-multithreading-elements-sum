package core.basesyntax;

import java.util.concurrent.RecursiveTask;

public class MyTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10;
    private final int startPoint;
    private final int finishPoint;

    public MyTask(int startPoint, int finishPoint) {
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
    }

    @Override
    protected Long compute() {
        if (finishPoint - startPoint <= THRESHOLD) {
            long sum = 0;
            for (int i = startPoint; i < finishPoint; i++) {
                sum += i;
            }
            return sum;
        } else {
            int mid = startPoint + (finishPoint - startPoint) / 2;
            MyTask leftTask = new MyTask(startPoint, mid);
            MyTask rightTask = new MyTask(mid, finishPoint);
            leftTask.fork();
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }
}
