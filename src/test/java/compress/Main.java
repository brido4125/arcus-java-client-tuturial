package compress;

import net.spy.memcached.compat.CloseUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;

public class Main {
  @Test
  void deflater() {
    byte[] bytes = new byte[1024 * 1024 * 4];
    long start = System.currentTimeMillis();
    byte[] compress = compress(bytes);
    long end = System.currentTimeMillis();

    long start2 = System.currentTimeMillis();
    byte[] compress2 = compressByGZip(bytes);
    long end2 = System.currentTimeMillis();

    long res1 = end - start;
    long res2 = end2 - start2;

    System.out.println("res1 = " + res1);
    System.out.println("res2 = " + res2);

  }

  public byte[] compress(byte[] in) {
    if (in == null) {
      throw new NullPointerException("Can't compress null");
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DeflaterOutputStream gz = null;
    try {
      gz = new DeflaterOutputStream(bos);
      gz.write(in);
    } catch (IOException e) {
      throw new RuntimeException("IO exception compressing data", e);
    } finally {
      CloseUtil.close(gz);
      CloseUtil.close(bos);
    }
    return bos.toByteArray();
  }

  public byte[] decompress(byte[] in) {
    ByteArrayOutputStream bos = null;
    if (in != null) {
      ByteArrayInputStream bis = new ByteArrayInputStream(in);
      bos = new ByteArrayOutputStream();
      InflaterInputStream gis;
      try {
        gis = new InflaterInputStream(bis);
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

  public byte[] compressByGZip(byte[] in) {
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
    return bos.toByteArray();
  }

  public byte[] decompressByGZip(byte[] in) {
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
