package isuue.visiblility;

public class Worker extends Thread {
  public boolean isStop = false;

  @Override
  public void run() {
    while (!isStop) {
      // doSomething()을 호출할 경우
      // while문 검사를 위해 isStop 변수를 Main Mamory에서 읽어온다.
    }
  }

  void doSomething() {
    try {
      Thread.sleep(50);
      System.out.println("doSomething");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
