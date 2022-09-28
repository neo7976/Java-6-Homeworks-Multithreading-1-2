package thread;

import java.util.concurrent.Callable;

public class MyThread<S> extends Thread implements Callable<Integer> {
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2500);
                System.out.printf("Я %s. Всем привет! Идентификатор %s \n", getName(), getId());
                count++;
            }
        } catch (InterruptedException e) {
            System.out.println();
        } finally {
            System.out.printf("%s Завершен! Группа - %s\n", getName(), getThreadGroup());
        }
        return count;
    }
}

