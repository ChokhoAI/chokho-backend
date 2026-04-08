package main.backend.services;

import main.backend.dto.response.WorkerComplaintResponse;
import main.backend.enums.RouteStatus;
import main.backend.models.Complaint;
import main.backend.models.Route;
import main.backend.models.User;
import main.backend.models.Vehicle;
import main.backend.repositories.ComplaintRepository;
import main.backend.repositories.RouteRepository;
import main.backend.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService {
    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;
    private final ComplaintRepository complaintRepository;

    public WorkerService(VehicleRepository vehicleRepository, RouteRepository routeRepository, ComplaintRepository complaintRepository){
        this.vehicleRepository = vehicleRepository;
        this.routeRepository = routeRepository;
        this.complaintRepository = complaintRepository;
    }

    public List<WorkerComplaintResponse> getRoute(User worker){
        Vehicle vehicle = vehicleRepository.findByWorker(worker);

        if(vehicle == null) throw new RuntimeException("Vehicle not assigned");

        Route route = routeRepository.findByVehicleAndRouteStatus(vehicle, RouteStatus.PENDING);

        if(route == null) throw new RuntimeException("Route not assigned");

        List<Complaint> complaints = complaintRepository.findAllByRouteOrderBySequenceNoAsc(route);

        List<WorkerComplaintResponse> res =complaints.stream().map(
                 complaint -> new WorkerComplaintResponse(complaint.getId(),
                         complaint.getLocation().getY(),
                         complaint.getLocation().getX(),
                         complaint.getImageUrl(),
                         complaint.getStatus(),
                         complaint.getSequenceNo())
        ).collect(Collectors.toList());

        return res;
    }
}
