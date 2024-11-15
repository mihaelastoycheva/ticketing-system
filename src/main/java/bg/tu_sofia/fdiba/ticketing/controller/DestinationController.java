package bg.tu_sofia.fdiba.ticketing.controller;

import org.springframework.web.bind.annotation.*;
import bg.tu_sofia.fdiba.ticketing.service.DestinationService;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/startpoint")
    public List<String> getStartingPoint(){
        return destinationService.getStartingPoint();
    }

    @GetMapping("/endpoint")
    public List<String> getEndingPoint(final @RequestParam String city) {
        return destinationService.getEndingPoint(city);
    }
}
