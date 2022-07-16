package com.sb.carrentalapp.repository;

import com.sb.carrentalapp.model.User;
import com.sb.carrentalapp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
