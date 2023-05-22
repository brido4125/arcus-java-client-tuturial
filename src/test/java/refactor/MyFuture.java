package refactor;

import net.spy.memcached.ops.Operation;
import refactor.cancel.FutureCancel;
import refactor.cancel.MultiKeyCancel;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyFuture implements Future<Boolean> {

    private final Collection<Operation> ops;

    private final FutureCancel futureCancel;

    public MyFuture(Collection<Operation> ops) {
        this.ops = ops;
        futureCancel = new MultiKeyCancel(ops);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return futureCancel.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Boolean get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
