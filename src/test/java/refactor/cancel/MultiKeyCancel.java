package refactor.cancel;

import net.spy.memcached.ops.Operation;
import net.spy.memcached.ops.OperationState;

import java.util.Collection;

public class MultiKeyCancel implements FutureCancel {

    private final Collection<Operation> ops;

    public MultiKeyCancel(Collection<Operation> ops) {
        this.ops = ops;
    }

    public boolean cancel(boolean ign) {
        boolean rv = false;
        for (Operation op : ops) {
            op.cancel("by application.");
            rv |= op.getState() == OperationState.WRITE_QUEUED;
        }
        return rv;
    }
}
