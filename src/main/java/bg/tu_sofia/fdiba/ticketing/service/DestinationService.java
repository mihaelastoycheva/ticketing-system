package bg.tu_sofia.fdiba.ticketing.service;

import bg.tu_sofia.fdiba.ticketing.exception.StartingPointNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor

@Service
public class DestinationService {
    public List<String> getAllCities() {
        return Arrays.asList(
                "Sofia",
                "Plovdiv",
                "Varna"
        );
    }

    public List<String> getStartingPoint(){
        return getAllCities();
    }

    public List<String> getEndingPoint(String startingPoint){
       final List<String> cities = new ArrayList<>(getAllCities());  // Copy the original list

        if (!cities.remove(startingPoint)) {  // Attempt to remove, returns false if not found
            throw new StartingPointNotFoundException("Start point not found");
        }

        return cities;
    }

}
