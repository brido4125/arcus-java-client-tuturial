import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.ops.CollectionOperationStatus;
import net.spy.memcached.transcoders.SerializingTranscoder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ArcusMop extends ArcusInfo {
    public ArcusMop(String arcusAdmin, String serviceCode) {
        super(arcusAdmin, serviceCode);
    }


}
