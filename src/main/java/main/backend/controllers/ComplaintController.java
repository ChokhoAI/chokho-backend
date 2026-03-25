package main.backend.controllers;

import main.backend.dto.ComplaintResponse;
import main.backend.security.CustomUserDetails;
import main.backend.services.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService){
        this.complaintService = complaintService;
    }

    @PostMapping("/complaint")
    public ResponseEntity<String> complaint(
            @RequestParam MultipartFile image
            ) throws  Exception{
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                                                                                .getAuthentication()
                                                                                .getPrincipal();
        return ResponseEntity.ok(complaintService.registerComplaint(image,userDetails));
    }

    @GetMapping("/complaints")
    public List<ComplaintResponse> complaints(){
        return complaintService.findAllComplaints();
    }
}
