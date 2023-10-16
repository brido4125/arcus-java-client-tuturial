package functional.mulitThread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // main thread가 다른 쓰레드의 동작을 종료 시키는 방법
        // 1. sleep 후 interrupt
        // main thread가 다른 쓰레드의 동작을 기다리는 방법
        // join 호출

        // Executor executor = command -> {};
        //ExecutorService가 좀 더 많은 메서드 제공하는 인터페이스
        //ExecutorService executorService = new ThreadPoolExecutor();

        //executorService는 shutdown을 호출해야 쓰레드풀 종료
        //해당 shutdown()는 graceful shutdown() -> 현재 진행 중인 작업은 끝내고 나서 해당 스레드풀 종료

        //Callable은 리턴 타입인 Future를 반환한다. 반면 Runnalbe은 리턴 타입이 없다.
        //동일하게 executor.submit()하면됨
        //Future는 cancel() 호출하면 get() 호출 시 exception 발생

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // invokeAll vs invokeAny
        Callable<String> one = () -> {
            Thread.sleep(1000);
            return "one";
        };

        Callable<String> two = () -> {
            Thread.sleep(2000);
            return "tow";
        };

        Callable<String> three = () -> {
            Thread.sleep(3000);
            return "three";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(one, two, three));
        for (Future<String> f : futures) {
            f.get();
        }
        System.out.println("done");

        String s = executorService.invokeAny(Arrays.asList(one, two, three));
        System.out.println("Invoke Any s = " + s);

    }
}
