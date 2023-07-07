package isuue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class TestTest {

    Test test;

    @Test
    @DisplayName("String 요소 변환 테스트")
    void convertTest() {
        List<String> data = new ArrayList<>();
        data.add("127.0.0.1:11211-localhost");
        data.add("127.0.0.2:11222-localhost");
        data.add("127.0.0.3:111-localhost");

        List<String> addressListString = CommandChangeList.getAddressListString(data);
        for ( String s: addressListString) {
            System.out.println(s);
        }
    }


    @Test
    void ifTest() {
        String a = null;
        String b = "hi";
        if (!(true || true)) {
            System.out.println("hi1");
        }
        if (!(true || false)) {
            System.out.println("hi2");
        }
        if (!(false || true)) {
            System.out.println("hi3");
        }
        if (!(false || false)) {
            System.out.println("hi4");
        }
    }
}