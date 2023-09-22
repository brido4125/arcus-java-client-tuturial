package isuue.completablefuture;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

public class ThenApplyTest {
  private static String getTime() {
    return " Time: " + LocalTime.now();
  }

  private static String getThread() {
    return " Thread: " + Thread.currentThread().getName();
  }

  // util method for waiting specific number of seconds
  private static void wait(int second) {
    try {
      Thread.sleep(second * 1000L);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  // thenApplyAsync -> Main Thread가 busy하지 않은 상황이라도, 무조건 CommonPool에서 수행하도록 해줌
  @Test
  void main() {
    long startTime = System.currentTimeMillis();

    // it will be executed on a thread from ForkJoin pool
    CompletableFuture<String> result1 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Supply async" + getTime() + getThread());
      return "async task";
    });

    wait(1); // this line will be executed in main thread -> it makes main thread not busy

    // it will also be executed on main thread
    CompletableFuture<String> result2 = result1.thenApplyAsync(prevResult -> {
      System.out.println("then apply" + getTime() + getThread());
      wait(2);
      return prevResult + " has been done. Now then apply is doing its work";
    });

    System.out.println("main thread" + getTime() + getThread());
    // it will be executed on main thread
    wait(2);

    String result = result2.join();
    System.out.println("final result: " + result);

    long timeTaken = System.currentTimeMillis() - startTime;
    System.out.println("total time taken (in milliseconds): " + timeTaken);
  }
}
