package bg.tu_sofia.fdiba.ticketing.model;


import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    protected Long id;
}
