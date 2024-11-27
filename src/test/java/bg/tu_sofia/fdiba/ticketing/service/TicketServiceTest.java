package bg.tu_sofia.fdiba.ticketing.service;

import bg.tu_sofia.fdiba.ticketing.dao.TicketRepository;
import bg.tu_sofia.fdiba.ticketing.enumeration.Day;
import bg.tu_sofia.fdiba.ticketing.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private List<Ticket> tickets;

    @BeforeEach
    public void setUp() {
        tickets = new ArrayList<>();

        tickets.add(Ticket.builder()
                .uid(UUID.randomUUID())
                .startPoint("Sofia")
                .endPoint("Plovdiv")
                .day(Day.MONDAY)
                .price(150)
                .time(12)
                .tripTicketType("ONEWAY")
                .quantity(50)
                .build());
    }

    @Test
    void whenEnterCorrectDataShouldReturnListOfTickets() {
// Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Reference ticket data from the pre-initialized list
        Ticket ticket = tickets.get(0);
        String tripTicketType = ticket.getTripTicketType();
        String startPoint = ticket.getStartPoint();
        String endPoint = ticket.getEndPoint();

        List<Ticket> mockTickets = List.of(ticket);

        // Mock the repository to return the initialized tickets
        Mockito.when(ticketRepository.findAll(Mockito.any(Specification.class))).thenReturn(tickets);

        // Act
        ResponseEntity<Object> response = ticketService.findTickets(tripTicketType, "SENIOR", startPoint, endPoint);

        // Assert
        Mockito.verify(ticketRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify the returned tickets
        List<Ticket> responseTickets = (List<Ticket>) response.getBody();
        assert responseTickets != null;
        Assertions.assertEquals(1, responseTickets.size());
        Assertions.assertEquals(tripTicketType, responseTickets.get(0).getTripTicketType());
        Assertions.assertEquals(startPoint, responseTickets.get(0).getStartPoint());
        Assertions.assertEquals(endPoint, responseTickets.get(0).getEndPoint());
    }

    @Test
    public void whenEnterIncorrectDataShouldReturnErrorMessage() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        String tripTicketType = "ONEWAY";
        String cardType = "SENIOR";
        String startPoint = "Sofiaa"; // incorrect startPoint
        String endPoint = "Plovdiv";

        // Simulate an empty result from the repository
        List<Ticket> emptyTickets = List.of();
        Mockito.when(ticketRepository.findAll(Mockito.any(Specification.class))).thenReturn(emptyTickets);

        // Act
        ResponseEntity<Object> response = ticketService.findTickets(tripTicketType, cardType, startPoint, endPoint);

        // Assert
        Mockito.verify(ticketRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Tickets not found", response.getBody());
    }

    @Test
    public void whenEndPointDoesNotHaveTicketsShouldReturnErrorMessage() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initialize mocks

        String tripTicketType = "ONEWAY";
        String cardType = "SENIOR";
        String startPoint = "Sofia";
        String endPoint = "Tokio"; // No tickets available for this city

        // Mock repository to return no matching tickets for the specified criteria
        Mockito.when(ticketRepository.findAll(Mockito.any(Specification.class))).thenReturn(List.of());

        // Act
        ResponseEntity<Object> response = ticketService.findTickets(tripTicketType, cardType, startPoint, endPoint);

        // Assert
        Mockito.verify(ticketRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Tickets not found", response.getBody());
    }



}
