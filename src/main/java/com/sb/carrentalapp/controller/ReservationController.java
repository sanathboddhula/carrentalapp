package com.sb.carrentalapp.controller;

import com.sb.carrentalapp.model.Reservation;
import com.sb.carrentalapp.model.User;
import com.sb.carrentalapp.repository.ReservationRepository;
import com.sb.carrentalapp.util.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;

    //reserve
    //return
    //cancel


    @GetMapping("/fetch/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = new ArrayList<>();

            reservations.addAll(reservationRepository.findAll());

            if (reservations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reserveVehicle")
    public ResponseEntity<Reservation> reserveVehicle(@RequestBody Reservation reservation) {
        try {
            Reservation reservationSaved = reservationRepository.save(reservation);
            return new ResponseEntity<>(reservationSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/returnVehicle")
    public ResponseEntity<Reservation> returnVehicle(@RequestBody Long reservationId) {
        try {
            Optional<Reservation> reservation = reservationRepository.findById(reservationId);
            Reservation reservation1 = reservation.get();
            reservation1.setStatus(ReservationStatus.RETURNED.name());
            Reservation reservationSaved = reservationRepository.save(reservation1);
            return new ResponseEntity<>(reservationSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
