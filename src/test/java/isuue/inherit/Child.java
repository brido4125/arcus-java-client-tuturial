package isuue.inherit;

public class Child extends Parent {

  @Override
  public void doSomething(String text) {
    System.out.println("doSomething in child" + text);
  }
}
