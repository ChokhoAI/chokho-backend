package main.backend.repositories;

import main.backend.enums.ComplaintStatus;
import main.backend.enums.RouteStatus;
import main.backend.models.Complaint;
import main.backend.models.Route;
import main.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    List<Complaint> findAllByRouteOrderBySequenceNoAsc(Route route);

    int countByUser(User user);

    int countByUserAndComplaintStatus(User user, ComplaintStatus status);

    List<Complaint> findAllByUser(User user);

    List<Complaint> findAllByUserOrderByCreatedAtDesc(User user);

    List<Complaint> findAllByUserAndComplaintStatus(User user, ComplaintStatus complaintStatus);
}
