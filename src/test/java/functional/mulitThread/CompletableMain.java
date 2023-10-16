package functional.mulitThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(() -> {
            return "Hello";
        });
        submit.get();//get() 로직 이전에는 Future의 결과값으로 하려는 작업을 수행할 수 없다. Blocking Call


        //return 타입이 없는 경우 -> runAsync
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
        });

        voidCompletableFuture.get();

        //return 타입이 있는 경우 -> supplyAsync
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });

        //return으로 받은 결과 값을 콜백으로 aysnc하게 사용 -> thenApply 리턴이 있는 경우 Function 인터페이스
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });
        cf1.thenApply(String::toLowerCase);

        //return으로 받은 결과 값을 콜백으로 aysnc하게 사용 -> thenAccept 리턴이 없는 경우 Consumer 인터페이스
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });
        cf2.thenAccept(String::toLowerCase);

        //aysnc하게 동작만 수행 -> thenRun Runnable을 인자로 받음
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });
        cf3.thenRun(() -> System.out.println("Just Runnable"));

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        });

        //두 Future 중에 의존성이 필요한 로직에서 합치는 예시, 여기서는 Hello가 World에 필요함
        CompletableFuture<String> helloWorld = hello.thenCompose(CompletableMain::getWorld);
        helloWorld.get();

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("world" + Thread.currentThread().getName());
            return "world";
        });

        //이 예제는 두 Future간의 의존성 없는 경우, thenCombine으로 바로 두개 결과 받을 수 있음
        CompletableFuture<String> helloWorld2 = hello.thenCombine(world, (h, w) -> h + w);


        boolean throwError = true;

        CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalStateException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).whenComplete((s, t) -> {
            System.out.println("s = " + s);
            System.out.println("t = " + t);
        });//t만 인자로 받으려면 exceptionally, 반환값을 리턴해야하는 경우는 handle 사용


    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return message + "World";
        });
    }
}
