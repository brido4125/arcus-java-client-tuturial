package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Collision {
  public static void main(String[] args) {
    // HashMap 생성
    Map<Person, String> map = new HashMap<>();

    // 두 개의 Person 객체 생성
    Person person1 = new Person("John Doe");
    Person person2 = new Person("Jane Doe");

    // HashMap에 추가
    map.put(person1, "first");
    map.put(person2, "second");
    map.put(new Person("test"), "test");

    HashMap<Integer, String> none = new HashMap<>();
    none.put(1, "hi");
    none.put(1, "hi2");
    none.put(2, "hello");

    // 해시 충돌 확인
    System.out.println(map);
    System.out.println(none);

    System.out.println("map.get(person1) = " + map.get(person1));

    none.put(null, "test");
    System.out.println("none = " + none);
  }

  public static class Person {
    private String name;

    public Person(String name) {
      this.name = name;
    }

    @Override
    public int hashCode() {
      // 모든 인스턴스에 대해 동일한 해시 코드를 반환
      return 1;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Person person = (Person) obj;
      return Objects.equals(name, person.name);
    }

    @Override
    public String toString() {
      return "Person{" + "name='" + name + '\'' + '}';
    }
  }
}
