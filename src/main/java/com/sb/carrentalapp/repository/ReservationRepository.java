package com.sb.carrentalapp.repository;

import com.sb.carrentalapp.model.Reservation;
import com.sb.carrentalapp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "Select * from Reservations r where vehicle_id = :vehicleId and (cast(:fromDate AS TIMESTAMP) <= cast(:toDate AS TIMESTAMP)) and (cast(:toDate AS TIMESTAMP) < cast(from_date AS TIMESTAMP) or cast(:fromDate AS TIMESTAMP) > cast(to_date AS TIMESTAMP)) ",
            nativeQuery = true)
    //cast(:dateFrom AS timestamp)
    public List<Reservation> findByVehicleIdAndDateRange(@Param("vehicleId") Long vehicleId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
