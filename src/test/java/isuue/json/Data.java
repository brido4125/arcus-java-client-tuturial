package isuue.json;


public class Data {
  private int age;
  private Type type;
  private String name;

  public Data(int age, Type type, String name) {
    this.age = age;
    this.type = type;
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public Type getType() {
    return type;
  }

  public String getName() {
    return name;
  }
}
