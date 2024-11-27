package bg.tu_sofia.fdiba.ticketing.model;

import bg.tu_sofia.fdiba.ticketing.enumeration.Day;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
@Builder
public class Ticket extends BaseEntity {

    @JsonIgnore
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uid;

    @Column(nullable = false, length = 50)
    private String startPoint;

    @Column(nullable = false, length = 50)
    private String endPoint;

    @Column(nullable = false, length = 50)
    private Integer price;

    private Double endPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String tripTicketType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day day;

    @Column(nullable = false, length = 30)
    private Integer time;
}
