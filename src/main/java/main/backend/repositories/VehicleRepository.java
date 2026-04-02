package main.backend.repositories;

import main.backend.enums.VehicleStatus;
import main.backend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByVehicleStatus(VehicleStatus vehicleStatus);
}
