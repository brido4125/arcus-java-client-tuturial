package date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BeforeMain {
    public static void main(String[] args) {
        // java8 이전에 사용되는 객체들
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        long time = date.getTime();
        System.out.println("time = " + time); // epoch time = unix time = 을 리턴해줌..

        date.setTime(1697946191801L);//date의 시간은 mutable 하다 -> multi Thead에서 사용하기 어렵다
    }
}
