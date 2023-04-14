package brido.arcus.java.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloArcusTest {

    private HelloArcus helloArcus;

    @BeforeEach
    public void set() {
        helloArcus = new HelloArcus("127.0.0.1:2191", "brido");
    }

    @Test
    public void store() {
        boolean b = helloArcus.sayHello();
        System.out.println("b = " + b);
    }

    @Test
    void listenHello() {
        //given
        //when
        String s = helloArcus.listenHello();
        //then
        System.out.println("s = " + s);
    }
}