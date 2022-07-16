package com.sb.carrentalapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Vehicle vehicle;


}
