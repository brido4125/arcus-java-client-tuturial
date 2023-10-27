package functional.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class Main {
  public static void main(String[] args) {
    ForkJoinPool f1 = new ForkJoinPool(10);

    f1.execute(() -> {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    f1.execute(() -> {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    f1.execute(() -> {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    System.out.println("f1 = " + f1);
    System.out.println("f1.getPoolSize() = " + f1.getPoolSize());

    ForkJoinPool f2 = new ForkJoinPool(10);
    System.out.println("f2 = " + f2);

  }
}
