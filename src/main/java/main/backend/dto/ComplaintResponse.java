package main.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.backend.enums.ComplaintStatus;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
public class ComplaintResponse {
    private Point point;
    private String imageUrl;
    private ComplaintStatus complaintStatus;
    private double severityScore;
}
