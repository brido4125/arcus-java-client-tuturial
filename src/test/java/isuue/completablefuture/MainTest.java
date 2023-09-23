package isuue.completablefuture;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainTest {

  @Test
  void runAsync() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      System.out.println("Thread: " + Thread.currentThread().getName());
    });
    future.get();
    System.out.println("Thread: " + Thread.currentThread().getName());
  }

  @Test
  void supplyAsync() throws ExecutionException, InterruptedException {

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return "Thread: " + Thread.currentThread().getName();
    });

    System.out.println("Thread: " + Thread.currentThread().getName());
    System.out.println(future.get());
  }


  //thenApply는 값 반환 가능
  @Test
  void thenApply() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      return "Thread: " + Thread.currentThread().getName();
    }).thenApply(String::toUpperCase);

    System.out.println(future.get());
  }

  //thenAccept는 값을 반환하지 않음
  @Test
  void thenAccept() throws ExecutionException, InterruptedException {
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
      return "Thread: " + Thread.currentThread().getName();
    }).thenAccept(s -> {
      System.out.println(s.toUpperCase());
    });

    //future.get();
  }

  @Test
  void whenComplete() throws InterruptedException {
    runTasks(0);
    Thread.sleep(1000);
    runTasks(2);
  }

  private void runTasks(int i) {
    System.out.printf("-- input: %s --%n", i);
    CompletableFuture
            .supplyAsync(() -> {
              log("supply async thread");
              return 16 / i;
            })
            .whenComplete((input, exception) -> {
              log("when complete start");
              if (exception != null) {
                System.out.println("exception occurs");
                System.err.println(exception);
              } else {
                System.out.println("no exception, got result: " + input);
              }
            })
            .thenApply(input -> input * 3)
            .thenAccept(System.out::println);

  }

  public void log(String msg) {
    System.out.println(LocalTime.now() + " ("
            + Thread.currentThread().getName() + ") " +  msg);
  }

  @Test
  void create() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future = new CompletableFuture<>();
    Executors.newCachedThreadPool().submit(() -> {
      Thread.sleep(2000);
      future.complete("Finished");
      return null;
    });

    log(future.get());
  }

  @Test
  void async() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      log("Starting...");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "Finished works";
    });
    log("get(): " + future.get());
  }

  @Test
  void supplyAndConsume() {
    Supplier<Integer> initValue = () -> 100;
    Consumer<Integer> valueConsumer = v -> {
      double pow = Math.pow(v, 2);
      System.out.println("pow = " + pow);
    };

    CompletableFuture<Integer> future = new CompletableFuture<>();

  }

  @Test
  void main() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future = new CompletableFuture<>();
    System.out.println("future.isDone() = " + future.isDone());
    future.complete("my value");
    System.out.println("future.isDone() = " + future.isDone());
    System.out.println("future.get() = " + future.get());
  }
}
