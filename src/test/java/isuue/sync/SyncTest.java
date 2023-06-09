package isuue.sync;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class SyncTest {
    private static String TEST = "INIT";
    private CountDownLatch latch = new CountDownLatch(2);

    @Test
    void syncTest() throws InterruptedException {

        Thread thread1 = new Thread(this::setUp);
        thread1.start();
        sleep(1000);
        Thread thread2 = new Thread(this::setUp);
        thread2.start();

        latch.await();
    }

    /*
    * synchronized -> block 내의 로직에만 영향을 줌
    * synchronized 인자는 인스터스 참조여야함
    * */
    void setUp() {
        if (!TEST.equals("INIT")) {
            System.out.println("TEST = " + TEST);
        }
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        synchronized (TEST) {
            sleep(5000);
            if (TEST.equals("INIT")) {
                TEST = "TEST";
            }
        }

        latch.countDown();
    }

    void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

