package refactor.cancel;

public interface FutureCancel {
    boolean cancel(boolean mayInterruptIfRunning);
}
