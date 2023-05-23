package refactor.future;

import net.sf.ehcache.management.Cache;

import java.util.concurrent.Callable;

public class DataProcessor implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Processing data");
        Thread.sleep(5000);
        return "Some processed data";
    }
}
