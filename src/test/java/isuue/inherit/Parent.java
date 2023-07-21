package isuue.inherit;

public class Parent {

  public void doSomething() {
    doSomething(" without parameter");
  }

  public void doSomething(String text) {
    System.out.println("doSomething in parent" + text);
  }
}
