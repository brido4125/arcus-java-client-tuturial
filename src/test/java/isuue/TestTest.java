package isuue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}