package com.sb.carrentalapp.repository;

import com.sb.carrentalapp.model.Reservation;
import com.sb.carrentalapp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "Select * from Reservations r where vehicle_id = :vehicleId and (:fromDate <= :toDate) and (:toDate < from_date or :fromDate > to_date) ",
            nativeQuery = true)
    public List<Reservation> findByVehicleIdAndDateRange(Long vehicleId, Date fromDate, Date toDate);

}
