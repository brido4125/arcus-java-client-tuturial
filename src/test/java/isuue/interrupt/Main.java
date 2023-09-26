package isuue.interrupt;

public class Main {
  public static void main(String[] args) {
    Thread target = new TargetThread();

    target.setUncaughtExceptionHandler(((t, e) -> {
      System.out.println(Thread.currentThread().getName());
      System.out.println("e = " + e);
      System.out.println("t.isInterrupted() = " + t.isInterrupted());
      System.out.println("Uncaught exception in thread " + t.getName() + ": " + e);
    }));

    target.start();
    try {
      Thread.sleep(1000);
      target.interrupt();
      Thread.sleep(2000);
      System.out.println("===========================");
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
        System.out.println("sleeping...");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          System.out.println("Interrupted!");
          Thread.currentThread().interrupt();
          throw new RuntimeException();
        }
      }
    }
  }
}
