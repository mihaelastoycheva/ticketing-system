package bg.tu_sofia.fdiba.ticketing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DemoTest {

    @Test
    public void testAddition(){
        assertEquals(4, 2 + 2);
    }
}
