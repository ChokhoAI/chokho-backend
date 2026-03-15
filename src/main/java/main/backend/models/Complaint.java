package main.backend.models;

import jakarta.persistence.*;
import lombok.*;
import main.backend.enums.ComplaintStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="route_id")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

//    @Column(columnDefinition = "jsonb")
//    private String gemini_analysis;

    @Column(nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location;

    @Column(nullable = false)
    private boolean trashDetected;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.PENDING;

    @Column
    private String trashType;

    @Column
    private String volumeEstimate;

    @Column
    private boolean isIndoor;

    @Column
    private String locationContext;

    @Column(columnDefinition = "TEXT")
    private String rejectionReason;

    @Column
    private double severityScore;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
