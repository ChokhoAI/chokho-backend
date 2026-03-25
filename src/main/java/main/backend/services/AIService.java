package main.backend.services;

import main.backend.dto.AIResponse;
import main.backend.enums.TrashType;
import org.springframework.stereotype.Service;

import main.backend.enums.VolumeEstimate;

@Service
public class AIService {
    public AIResponse fastApiService(){
        return new AIResponse(
                true,
                false,
                false,
                TrashType.PLASTIC,
                VolumeEstimate.MEDIUM,
                "near har ki podi",
                6.7
        );
    }
}
