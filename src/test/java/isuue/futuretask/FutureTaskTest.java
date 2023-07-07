package isuue.futuretask;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Disabled
public class FutureTaskTest {

  @Test
  void test() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Task<String> task = new Task<>(() -> {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      System.out.println("callable");
      return "callable";
    });
    executorService.execute(task);
    String s = task.get();
    System.out.println("s = " + s);
  }


  @Test
  void callableBlockingTest() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    NoneFutureTask task = new NoneFutureTask();
    Future submit = executorService.submit(task);//submit 자체가 Future 리턴 -> 결국 get 호출시 Blocking 가능성
    System.out.println("main thread");

  }

  private void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  private static class NoneFutureTask implements Callable {
    @Override
    public Object call() throws Exception {
      System.out.println("NoneFutureTask.call");
      return "NoneFutureTask.call";
    }
  }

  private static class Task<T> extends FutureTask<T> {
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public Task(Callable<T> callable) {
      super(callable);
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
      this.run();
      return super.get();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException,
            ExecutionException, TimeoutException {
      this.run();
      return super.get(timeout, unit);
    }

    @Override
    public void run() {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      System.out.println("run1");
      if (this.isRunning.compareAndSet(false, true)) {
        System.out.println("run2");
        super.run();
      }
    }
  }
}
