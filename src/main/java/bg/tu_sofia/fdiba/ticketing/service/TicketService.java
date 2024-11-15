package bg.tu_sofia.fdiba.ticketing.service;

import bg.tu_sofia.fdiba.ticketing.dao.TicketRepository;
import bg.tu_sofia.fdiba.ticketing.dao.TicketSpecification;
import bg.tu_sofia.fdiba.ticketing.model.Ticket;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public ResponseEntity<String> createTicket(final Ticket ticket) {
        Ticket buildTicket = Ticket.builder()
                .uid(UUID.randomUUID())
                .startPoint(ticket.getStartPoint())
                .endPoint(ticket.getEndPoint())
                .day(ticket.getDay())
                .price(ticket.getPrice())
                .time(ticket.getTime())
                .tripTicketType(ticket.getTripTicketType())
                .quantity(ticket.getQuantity())
                .build();

        this.ticketRepository.save(buildTicket);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Ticket> getAllTickets() {
        return this.ticketRepository.findAll();
    }

    public List<Ticket> findTickets(String tripTicketType, String cardType, String startPoint, String endPoint) {
        Specification<Ticket> specification = Specification
                .where(tripTicketType != null ? TicketSpecification.hasTripTicketType(tripTicketType) : null)
                .and(startPoint != null ? TicketSpecification.hasStartPoint(startPoint) : null)
                .and(endPoint != null ? TicketSpecification.hasEndPoint(endPoint) : null);

        return ticketRepository.findAll(specification).stream()
                .peek((ticket -> ticket.setEndPrice(calcPromotionPrice(cardType, ticket))))
                .collect(Collectors.toList());
    }

    private Double calcPromotionPrice(final String cardType, final Ticket ticket) {
        double discount = 0;

        switch (cardType) {
            case "STANDARD": break;
            case "STUDENT": discount += 5; break;
            case "SENIOR": discount += 34; break;
            default: break;
        }

        if ((ticket.getTime() >= 10 && ticket.getTime() <= 16) || (ticket.getTime() >= 19)) {
            discount += 5;
        }

        discount = (discount * 0.01);
        double result = Math.round(ticket.getPrice() - (ticket.getPrice() * discount));
        return result;

    }
}