package main.backend.services;

import main.backend.dto.response.AIResponse;
import main.backend.enums.TrashType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import main.backend.enums.VolumeEstimate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AIService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.fastapi.analyzeUrl}")
    private String url;

    public AIResponse fastApiService(MultipartFile image) throws  Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        try {
            return restTemplate.postForObject(url, requestEntity, AIResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("AI service parse failed", e);
        }
    }
}
