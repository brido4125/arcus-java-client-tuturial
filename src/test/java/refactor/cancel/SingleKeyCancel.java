package refactor.cancel;

import net.spy.memcached.ops.Operation;
import net.spy.memcached.ops.OperationState;

public class SingleKeyCancel implements FutureCancel {

    private final Operation op;

    public SingleKeyCancel(Operation op) {
        this.op = op;
    }

    @Override
    public boolean cancel(boolean ign) {
        assert op != null : "No operation";
        op.cancel("by application.");
        // This isn't exactly correct, but it's close enough.  If we're in
        // a writing state, we *probably* haven't started.
        return op.getState() == OperationState.WRITE_QUEUED;
    }
}
