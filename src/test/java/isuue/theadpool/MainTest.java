package isuue.theadpool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {
  @Test
  public void threadPoolTest() {
    final AtomicInteger integer = new AtomicInteger(0);
    final List<Future<?>> futures = new ArrayList<Future<?>>();
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(20));

    for (int i = 0; i < 30; i++) {
      futures.add(threadPool.submit(new Runnable() {
        @Override
        public void run() {
          System.out.println("Thread.currentThread().getId() = " + Thread.currentThread().getId());
          System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
          try {
            Thread.sleep(5000);
          } catch (Exception e) {
            e.printStackTrace();
          }
          integer.getAndIncrement();
        }
      }));
    }

    for (Future<?> future : futures) {
      try {
        future.get();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println("threadPool.getActiveCount() = " + threadPool.getActiveCount());
    System.out.println("threadPool.getPoolSize() = " + threadPool.getPoolSize());
  }
}
