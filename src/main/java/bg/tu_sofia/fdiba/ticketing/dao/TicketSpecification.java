package bg.tu_sofia.fdiba.ticketing.dao;

import bg.tu_sofia.fdiba.ticketing.model.Ticket;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecification {

    public static Specification<Ticket> hasTripTicketType(String tripTicketType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tripTicketType"), tripTicketType);
    }

    public static Specification<Ticket> hasStartPoint(String startPoint) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("startPoint"), startPoint);
    }

    public static Specification<Ticket> hasEndPoint(String endPoint) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("endPoint"), endPoint);
    }
}
