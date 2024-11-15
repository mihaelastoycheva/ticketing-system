package bg.tu_sofia.fdiba.ticketing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DestinationServiceTest {

    @Autowired
    private DestinationService destinationService;

    @Test
    public void getExistingCityTest() {
        List<String> cities = destinationService.getAllCities();
        assertTrue(cities.contains("Sofia"));
    }

    @Test
    public void getNotExistingCityTest() {
        List<String> cities = destinationService.getAllCities();
        assertFalse(cities.contains("Sofiqw"));
    }
}
