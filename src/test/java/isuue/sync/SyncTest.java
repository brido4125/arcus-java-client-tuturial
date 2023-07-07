package isuue.sync;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Disabled
public class SyncTest {
    private static String TEST = "INIT";
    private CountDownLatch latch;

    @Test
    void syncTest() throws InterruptedException {
        latch = new CountDownLatch(2);

        Thread thread1 = new Thread(this::setUp);
        thread1.start();
        sleep(1000);
        Thread thread2 = new Thread(this::setUp);
        thread2.start();

        latch.await();
    }

    @Test
    @DisplayName("synchronized의 인자가 변수명이 동일하더라도 서로 다른 객체라면 동기화가 되지 않음")
    void syncTest2() throws InterruptedException {
        latch = new CountDownLatch(2);
        Thread thread1 = new Thread(() -> setUpWithObject(latch));
        thread1.start();
        sleep(500);
        Thread thread2 = new Thread(() -> setUpWithObject(latch));
        thread2.start();

        latch.await();
    }

    @Test
    @DisplayName("Collection 객체는 내부의 요소가 달라져야 서로 다른 hashcode 값을 가짐")
    void syncTest3() throws InterruptedException {
        latch = new CountDownLatch(2);
        Thread thread1 = new Thread(() -> setUpWithListConcreteElem(latch,"hi"));
        thread1.start();
        sleep(500);
        Thread thread2 = new Thread(() -> setUpWithListConcreteElem(latch,"hello"));
        thread2.start();

        latch.await();
    }

    void setUpWithObject(CountDownLatch latch) {
        Object test = new Object();
        synchronized (test) {
            System.out.println("test.hashCode() = " + test.hashCode());
            sleep(5000);
        }
        latch.countDown();
    }

    void setUpWithListConcreteElem(CountDownLatch latch, String target) {
        List<String> test = new LinkedList<>();
        test.add(target);
        synchronized (test) {
            System.out.println("test.hashCode() = " + test.hashCode());
        }
        latch.countDown();
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

