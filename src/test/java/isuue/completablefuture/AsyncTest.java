package isuue.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class AsyncTest {

  @Test
  public void test_supplyAsync_whenComplete() throws InterruptedException {

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

      System.out.println("on supplyAsync: " + Thread.currentThread().getName());
      return "ret";
    }).whenComplete((s, throwable) -> {
      System.out.println("on whenComplete: " + Thread.currentThread().getName());
      System.out.println(s);
    });

    for (int i = 0; i < 100; i++) {
      Thread.sleep(1L);
    }
    System.out.println("outside: " + Thread.currentThread().getName());
  }

  @Test
  public void test_supplyAsync_with_whenCompleteAsync() throws InterruptedException {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      System.out.println("on supplyAsync: " + Thread.currentThread().getName());
      return "ret";
    }).whenComplete((s, throwable) -> {
      System.out.println("on whenCompleteAsync: " + Thread.currentThread().getName());
      System.out.println(s);
    });

    for (int i = 0; i < 100; i++) {
      Thread.sleep(1L);
    }
    System.out.println("outside: " + Thread.currentThread().getName());
  }
}
