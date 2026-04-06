package main.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationFastApiResponse {
    private boolean isCleaned;
    private String reason;
}
