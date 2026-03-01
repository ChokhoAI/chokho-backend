package main.backend.models;

import jakarta.persistence.*;
import lombok.*;
import main.backend.enums.RouteStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private User worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private double total_distance;

    @Column(nullable = false)
    private int total_complaints;

    @Column
    private int estimated_duration;

    @Column(columnDefinition = "jsonb")
    private String optimization_data;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteStatus routeStatus = RouteStatus.PENDING;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated_at;
}
