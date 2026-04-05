package main.backend.controllers;

import main.backend.models.Complaint;
import main.backend.models.Route;
import main.backend.models.User;
import main.backend.models.Vehicle;
import main.backend.repositories.ComplaintRepository;
import main.backend.repositories.RouteRepository;
import main.backend.repositories.VehicleRepository;
import main.backend.security.CustomUserDetails;
import main.backend.services.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService){
      this.workerService = workerService;
    }

    @GetMapping("/my-route")
    public ResponseEntity<?> myRoute(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                                                                            .getContext()
                                                                            .getAuthentication()
                                                                            .getPrincipal();
        return ResponseEntity.ok(workerService.getRoute(userDetails.getUser()));
    }
}
