package main.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.backend.enums.TrashType;
import main.backend.enums.VolumeEstimate;

@Data
@AllArgsConstructor
public class AIResponse {
    private boolean trashDetected;

    private boolean isFake;

    private boolean isIndoor;

    private TrashType trashType;

    private VolumeEstimate volumeEstimate;

    private String aiAnalysis;

    private double severityScore;
}