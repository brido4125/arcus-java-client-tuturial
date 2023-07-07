package refactor.future;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.System.currentTimeMillis;

@Disabled
public class FutureTest {


    @Test
    void callableTaskTest() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        long current = currentTimeMillis();

        Future<String> readData = threadPool.submit(new DataReader());
        Future<String> processData = threadPool.submit(new DataProcessor());

        while (!readData.isDone() || !processData.isDone()) {
            System.out.println("Waiting for data to be ready");
            Thread.sleep(500);
        }

        System.out.println("readData.get() = " + readData.get());
        System.out.println("processData.get() = " + processData.get());

        System.out.println("Time taken = " + (currentTimeMillis() - current));
    }

    @Test
    void futureTest() {

    }
}
