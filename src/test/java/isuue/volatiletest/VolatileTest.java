package isuue.volatiletest;


public class VolatileTest {

  /*
  * 가시성 문제 테스트는 psvm 사용해서 테스트 필요
  * */
  public static void main(String[] args) {
    VolatileTest test = new VolatileTest();
    test.runVolatileSample();
  }

  private void runVolatileSample() {
    VolatileSample sample = new VolatileSample();
    sample.start();
    sleep(1000);
    System.out.println("Sleep End");
    sample.setDouble(-1);
    System.out.println("Set End");
  }

  private void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
