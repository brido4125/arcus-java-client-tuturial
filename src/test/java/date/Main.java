package date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        //기계용
        Instant instant = Instant.now(); // GMT 기준 value 리턴
        System.out.println("instant = " + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println("zonedDateTime = " + zonedDateTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        Period between = Period.between(LocalDate.now(), LocalDate.of(2024, Month.JULY, 15));
        System.out.println("between = " + between);

        Duration duration = Duration.between(Instant.now(), Instant.ofEpochMilli(Instant.now().toEpochMilli() + 100L));
        System.out.println("duration = " + duration);
    }
}
