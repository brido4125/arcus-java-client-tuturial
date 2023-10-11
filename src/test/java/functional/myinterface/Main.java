package functional.myinterface;

public class Main {
    public static void main(String[] args) {
        // java로 함수형 프로그래밍 -> 순수 함수 사용
        // 외부의 Local var 참조 X
        // 같은 인자가 주어지면 항상 같은 결과 리턴
        MyInterface myInterface = number -> number + 100;
    }
}
