package isuue.futuretask;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
public class FutureTaskTest {

  private final String RETURN_VAL = "callable task complete";

  @Test
  @DisplayName("FutureTask 수행 후, Callable의 수행")
  void test() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Task<String> task = new Task<>(() -> {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      System.out.println("callable");
      return RETURN_VAL;
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

  @Test
  @DisplayName("FutureTask가 수행전에 cancel(True) 호출")
  void cancelTestBeforeProgress(){
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Task<String> task = new Task<>(() -> {
      System.out.println("callable");
      sleep(1000);
      return RETURN_VAL;
    });
    boolean cancel = task.cancel(true);
    executorService.execute(task); //task start

    assertThat(cancel).isTrue();
    assertThatThrownBy(task::get).isInstanceOf(CancellationException.class);
  }

  @Test
  @DisplayName("FutureTask가 수행전에 cancel(False) 호출")
  void cancelTestBeforeProgressWithFalse(){
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Task<String> task = new Task<>(() -> {
      System.out.println("callable");
      sleep(1000);
      return RETURN_VAL;
    });
    boolean cancel = task.cancel(false);
    executorService.execute(task); //task start

    assertThat(cancel).isTrue();
    assertThatThrownBy(task::get).isInstanceOf(CancellationException.class);
  }

  @Test
  @DisplayName("FutureTask가 수행중에 cancel(True) 호출")
  void cancelTestWithProgress(){
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Task<String> task = new Task<>(() -> {
      System.out.println("callable");
      return RETURN_VAL;
    });

    executorService.execute(task); //task start
    boolean cancel = task.cancel(true);

    assertThat(cancel).isTrue();
    assertThatThrownBy(task::get).isInstanceOf(CancellationException.class);
  }

  @Test
  @DisplayName("FutureTask가 완료되고 cancel(True) 호출")
  void cancelTestWithDone() throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Task<String> task = new Task<>(() -> {
      System.out.println("callable");
      return RETURN_VAL;
    });

    executorService.execute(task); //task start
    sleep(1000);
    boolean cancel = task.cancel(true);

    assertThat(cancel).isFalse();
    assertThat(task.get()).isEqualTo(RETURN_VAL);
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
      if (this.isRunning.compareAndSet(false, true)) {
        super.run();
      }
    }
  }
}
