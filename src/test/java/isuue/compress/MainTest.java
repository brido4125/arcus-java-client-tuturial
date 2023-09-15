package isuue.compress;

import net.spy.memcached.compat.CloseUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MainTest {

  String json = "{\n" +
          "    \"name\": \"식빵\",\n" +
          "    \"family\": \"웰시코기\",\n" +
          "    \"age\": 1,\n" +
          "    \"weight\": 2.14\n" +
          "}";

  @Test
  void compressTest() throws IOException {
    byte[] bytes = json.getBytes();
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    GZIPOutputStream gz = null;
    try {
      gz = new GZIPOutputStream(bos);
      gz.write(bytes);
    } catch (IOException e) {
      throw new RuntimeException("Compress error");
    } finally {
      CloseUtil.close(gz);
      CloseUtil.close(bos);
    }
    byte[] compressed = bos.toByteArray();
    System.out.println("before.length = " + bytes.length);
    System.out.println("compressed.length = " + compressed.length);

    byte[] decompress = decompress(compressed);
    System.out.println("decompress = " + decompress.length);
  }

  @Test
  void compressTest2() throws IOException {
    byte[] bytes = json.getBytes();
    byte[] compress = compress(bytes);
    System.out.println("compress.length = " + compress.length);
  }

  protected byte[] compress(byte[] in) {
    if (in == null) {
      throw new NullPointerException("Can't compress null");
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    GZIPOutputStream gz = null;
    try {
      gz = new GZIPOutputStream(bos);
      gz.write(in);
    } catch (IOException e) {
      throw new RuntimeException("IO exception compressing data", e);
    } finally {
      CloseUtil.close(gz);
      CloseUtil.close(bos);
    }
    byte[] rv = bos.toByteArray();
    return rv;
  }

  protected byte[] decompress(byte[] in) {
    ByteArrayOutputStream bos = null;
    if (in != null) {
      ByteArrayInputStream bis = new ByteArrayInputStream(in);
      bos = new ByteArrayOutputStream();
      GZIPInputStream gis;
      try {
        gis = new GZIPInputStream(bis);

        byte[] buf = new byte[8192];
        int r = -1;
        while ((r = gis.read(buf)) > 0) {
          bos.write(buf, 0, r);
        }
      } catch (IOException e) {
        bos = null;
      }
    }
    return bos == null ? null : bos.toByteArray();
  }
}
