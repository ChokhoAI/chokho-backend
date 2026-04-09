package main.backend.repositories;

import main.backend.enums.ComplaintStatus;
import main.backend.models.Complaint;
import main.backend.models.Route;
import main.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    List<Complaint> findAllByRouteOrderBySequenceNoAsc(Route route);

    @Query("SELECT c FROM Complaint c ORDER BY c.createdAt DESC")
    List<Complaint> findAllOrderByCreatedAtDesc();

    int countByUser(User user);

    int countByUserAndComplaintStatus(User user, ComplaintStatus complaintStatus);

    int countByRouteAndComplaintStatus(Route route, ComplaintStatus complaintStatus);

    int countByComplaintStatus(ComplaintStatus complaintStatus);

    List<Complaint> findAllByUser(User user);

    List<Complaint> findAllByUserOrderByCreatedAtDesc(User user);
}
