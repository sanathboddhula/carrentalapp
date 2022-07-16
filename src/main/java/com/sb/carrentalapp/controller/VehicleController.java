package com.sb.carrentalapp.controller;

import com.sb.carrentalapp.model.User;
import com.sb.carrentalapp.model.Vehicle;
import com.sb.carrentalapp.repository.UserRepository;
import com.sb.carrentalapp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    VehicleRepository vehicleRepository;

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
}
