package isuue.interrupt;

public class Main {
  public static void main(String[] args) {
    Thread target = new TargetThread();

    target.start();
    try {
      Thread.sleep(1000);
      target.interrupt(); // 외부에서의 인터럽트 요청
    } catch (InterruptedException e) {
      System.out.println("finished");
    } catch (RuntimeException e) {
      System.out.println("e = " + e);
    }
  }

  static class TargetThread extends Thread {
    @Override
    public void run() throws RuntimeException {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          System.out.println("Outer sleep...");
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          try {
            System.out.println("Inner sleep ...");
            Thread.sleep(1000);
            System.out.println("Inner sleep done");

          } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
    }
  }
}
