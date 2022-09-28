import thread.MyThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadGroup group = new ThreadGroup("Основная группа");
        Callable<Integer> myCallable = new MyThread<>(group, "Поток 1");
        Callable<Integer> myCallable2 = new MyThread<>(group, "Поток 2");
        Callable<Integer> myCallable3 = new MyThread<>(group, "Поток 3");
        Callable<Integer> myCallable4 = new MyThread<>(group, "Поток 4");
        List<Callable<Integer>> list = new ArrayList<>(List.of(myCallable,
                myCallable2,
                myCallable3,
                myCallable4,
                new MyThread<>(group, "Поток 5"),
                new MyThread<>(group, "Поток 6"),
                new MyThread<>(group, "Поток 7"),
                new MyThread<>(group, "Поток 8")
        ));

        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        final ExecutorService threadPool2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Integer task = threadPool.invokeAny(list);
        Integer task2 = threadPool2.invokeAny(list);
        List<Future<Integer>> task3 = threadPool2.invokeAll(list);

        System.out.println("Количество сообщений в одном потоке 1: " + task);
        System.out.println("Количество сообщений в одном потоке 2: " + task2);
        for (Future<Integer> integerFuture : task3) {
            System.out.println("Третий способ получить значения: " + integerFuture.get());
        }

        threadPool.shutdown();
        threadPool2.shutdown();
    }
}
