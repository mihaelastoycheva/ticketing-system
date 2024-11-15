package bg.tu_sofia.fdiba.ticketing.controller;

import bg.tu_sofia.fdiba.ticketing.model.Ticket;
import bg.tu_sofia.fdiba.ticketing.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createTicket(final @RequestBody Ticket ticket) {
        return this.ticketService.createTicket(ticket);
    }

    @GetMapping(value = "/getAll")
    public List<Ticket> getAllTickets() {
        return this.ticketService.getAllTickets();
    }

    @GetMapping("/getTicket")
    public List<Ticket> getTickets(
            @RequestParam(required = false) String tripTicketType,
            @RequestParam(required = false) String startPoint,
            @RequestParam(required = false) String endPoint,
            @RequestParam(required = false) String cardType) {
        return ticketService.findTickets(tripTicketType, cardType ,startPoint, endPoint);
    }
}
