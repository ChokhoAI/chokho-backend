package main.backend.services;

import main.backend.dto.response.CitizenComplaintsResponse;
import main.backend.dto.response.CitizenDashboardResponse;
import main.backend.dto.response.CitizenProfileResponse;
import main.backend.dto.response.ComplaintDetailResponse;
import main.backend.enums.ComplaintStatus;
import main.backend.models.Complaint;
import main.backend.models.User;
import main.backend.models.Vehicle;
import main.backend.repositories.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenService {
    private final AreaService areaService;
    private final ComplaintRepository complaintRepository;

    public CitizenService(AreaService areaService, ComplaintRepository complaintRepository){
        this.complaintRepository = complaintRepository;
        this.areaService = areaService;
    }

    public CitizenDashboardResponse getDashboard(User user){
        int pendingComplaints = complaintRepository.countByUserAndComplaintStatus(user, ComplaintStatus.PENDING);
        int resolvedComplaints = complaintRepository.countByUserAndComplaintStatus(user, ComplaintStatus.CLEANED);
        int totalComplaints = complaintRepository.countByUser(user);

        int complaintResolvedPercentage = totalComplaints == 0 ? 0 : (resolvedComplaints * 100) / totalComplaints;

        List<CitizenComplaintsResponse> recentComplaints = complaintRepository
                .findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .limit(3)
                .map(c -> new CitizenComplaintsResponse(
                        c.getId(),
                        "CMP-" + c.getId(),
                        c.getStatus(),
                        c.getTrashType(),
                        c.getVolumeEstimate(),
                        (int) c.getSeverityScore(),
                        c.getCreatedAt(),
                        areaService.getLocation(c.getLocation().getY(), c.getLocation().getX())
                ))
                .collect(Collectors.toList());

        return new CitizenDashboardResponse(
                complaintResolvedPercentage,
                totalComplaints,
                resolvedComplaints,
                pendingComplaints,
                user.getName(),
                recentComplaints
        );
    }

    public List<CitizenComplaintsResponse> myComplaints(User user){
        List<Complaint> complaints = complaintRepository.findAllByUser(user);

        return complaints.stream().map(
                c -> new CitizenComplaintsResponse(
                        c.getId(),
                        "CMP-" + c.getId(),
                        c.getStatus(),
                        c.getTrashType(),
                        c.getVolumeEstimate(),
                        (int) c.getSeverityScore(),
                        c.getCreatedAt(),
                        areaService.getLocation(c.getLocation().getY(), c.getLocation().getX())
                )
        ).collect(Collectors.toList());
    }

    public CitizenProfileResponse getProfile(User user){
        return new CitizenProfileResponse(
                user.getName(),
                user.getRole(),
                user.getPhone(),
                user.getAddress(),
                "CIT-" + user.getId()
        );
    }

    public ComplaintDetailResponse complaintDetail(Long complaintId){
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow();
        Vehicle vehicle = complaint.getVehicle();

        String workerName = null;
        if (complaint.getVehicle() != null && complaint.getVehicle().getWorker() != null) {
            workerName = complaint.getVehicle().getWorker().getName();
        }

        return new ComplaintDetailResponse(
                "CMP-" + complaint.getId(),
                complaint.getStatus(),
                complaint.getTrashType(),
                complaint.getVolumeEstimate(),
                complaint.getSeverityScore(),
                complaint.getCreatedAt(),
                areaService.getLocation(complaint.getLocation().getY(), complaint.getLocation().getX()),
                complaint.getImageUrl(),
                complaint.getAiAnalysis(),
                workerName
        );
    }
}
