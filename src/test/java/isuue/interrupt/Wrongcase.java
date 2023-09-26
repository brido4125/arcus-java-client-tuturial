package isuue.interrupt;

public class Wrongcase {
  static class WrongThread extends Thread {
    @Override
    public void run() {
      while (true) {
        System.out.println("do something...");
        System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread target = new WrongThread();
    target.start();
    Thread.sleep(2000);
    target.interrupt();
  }
}
