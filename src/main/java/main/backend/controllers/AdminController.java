package main.backend.controllers;

import main.backend.dto.response.AdminComplaintResponse;
import main.backend.dto.response.AdminDashboardResponse;
import main.backend.dto.response.AdminVehicleResponse;
import main.backend.dto.response.AdminWorkerResponse;
import main.backend.security.CustomUserDetails;
import main.backend.services.AdminService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public AdminDashboardResponse dashboard(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminService.getDashboard(userDetails.getUser());
    }

    @GetMapping("/complaints")
    public List<AdminComplaintResponse> complaints(){
        return adminService.getComplaints();
    }

    @GetMapping("/workers")
    public List<AdminWorkerResponse> workers(){
        return adminService.getWorkers();
    }

    @GetMapping("/vehicles")
    public List<AdminVehicleResponse> vehicles(){
        return adminService.getVehicles();
    }
}
