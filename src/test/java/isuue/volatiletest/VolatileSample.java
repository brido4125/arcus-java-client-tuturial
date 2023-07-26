package isuue.volatiletest;

public class VolatileSample extends Thread {
  private volatile double insanceVariable = 0.0;

  void setDouble(double value) {
    insanceVariable = value;
  }

  @Override
  public void run() {
    while (insanceVariable == 0.0){
      // do nothing
    }
    System.out.println(insanceVariable);
  }
}
