package brido.arcus.java.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloArcusTest {

    private HelloArcus helloArcus;

    @BeforeEach
    public void set() {
        helloArcus = new HelloArcus("127.0.0.1:2191", "test");
    }

    @Test
    void listenHello() {
        //given
        helloArcus.sayHello();
        //when
        String s = helloArcus.listenHello();
        //then
        Assertions.assertSame(s,"Hello Brido");
    }
}