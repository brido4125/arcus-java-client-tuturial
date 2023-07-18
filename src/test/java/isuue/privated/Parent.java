package isuue.privated;

public class Parent {
  private final String name;
  private final int age;

  public Parent(Parent copy) {
    this.name = copy.name;
    this.age = copy.age;
  }
}
