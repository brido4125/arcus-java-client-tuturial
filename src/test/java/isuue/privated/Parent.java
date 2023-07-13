package isuue.privated;

public class Parent {
  private String name;
  private int age;
  private String car;

  public Parent(Parent parent) {
    this.name = parent.name;
    this.age = parent.age;
  }

  private void test(Parent parent) {
    int age1 = parent.age;
  }
}
