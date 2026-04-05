package main.backend.repositories;

import main.backend.enums.RouteStatus;
import main.backend.models.Route;
import main.backend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByVehicleAndRouteStatus(Vehicle vehicle, RouteStatus routeStatus);
}
