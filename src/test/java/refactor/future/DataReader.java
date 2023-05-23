package refactor.future;

import java.util.concurrent.Callable;

public class DataReader implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Reading data from file");
        Thread.sleep(5000);
        return "Some data";
    }
}
