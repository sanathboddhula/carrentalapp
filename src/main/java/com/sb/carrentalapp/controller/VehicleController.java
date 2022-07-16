package com.sb.carrentalapp.controller;

import com.sb.carrentalapp.model.Reservation;
import com.sb.carrentalapp.model.Vehicle;
import com.sb.carrentalapp.repository.ReservationRepository;
import com.sb.carrentalapp.repository.VehicleRepository;
import com.sb.carrentalapp.util.DateRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class VehicleController {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/fetch/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = new ArrayList<>();

            vehicles.addAll(vehicleRepository.findAll());

            if (vehicles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/vehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle vehicleSaved = vehicleRepository
                    .save(vehicle);
            return new ResponseEntity<>(vehicleSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove/vehicle")
    public ResponseEntity<Boolean> removeVehicle(@RequestBody Vehicle vehicle) {
        try {
            vehicleRepository.delete(vehicle);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/fetch/daterange")
    public ResponseEntity<List<Vehicle>> findByDateRange(@RequestBody DateRange dateRange) {
        try {

            List<Vehicle> allVehicles = vehicleRepository.findAll();
            List<Vehicle> allAvailableVehicles = new ArrayList<>();
            for (Vehicle vehicle : allVehicles) {
                log.info(vehicle.toString());
                List<Reservation> listOfReservationsForDateRange = reservationRepository.findByVehicleIdAndDateRange(vehicle.getId(), dateRange.getFromDate(), dateRange.getToDate());
                //checking if vehicle is reserved for given date
                if (listOfReservationsForDateRange == null || listOfReservationsForDateRange.isEmpty()) {
                    allAvailableVehicles.add(vehicle);
                }
            }
            return new ResponseEntity<List<Vehicle>>(allAvailableVehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
