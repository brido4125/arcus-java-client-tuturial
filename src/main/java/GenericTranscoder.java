import net.spy.memcached.CachedData;
import net.spy.memcached.transcoders.BaseSerializingTranscoder;
import net.spy.memcached.transcoders.Transcoder;

public class GenericTranscoder<V> extends BaseSerializingTranscoder implements Transcoder<V>{


  public GenericTranscoder(int max) {
    super(CachedData.MAX_SIZE);
  }

  @Override
  public boolean asyncDecode(CachedData cachedData) {
    return false;
  }

  @Override
  public CachedData encode(V v) {
    return null;
  }

  @Override
  public V decode(CachedData cachedData) {
    return null;
  }

  @Override
  public int getMaxSize() {
    return 0;
  }
}
