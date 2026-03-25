package main.backend.controllers;

import main.backend.models.GeoLocation;
import main.backend.services.CloudinaryService;
import main.backend.services.ExifService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    private final CloudinaryService cloudinaryService;
    private final ExifService exifService;

    public TestController(CloudinaryService cloudinaryService, ExifService exifService){
        this.cloudinaryService = cloudinaryService;
        this.exifService = exifService;
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(
            @RequestParam("image")MultipartFile image
            ) throws Exception{
        return ResponseEntity.ok(cloudinaryService.uploadImage(image));
    }

    @PostMapping("/metadata")
    public ResponseEntity<GeoLocation> metadata(
            @RequestParam("image")MultipartFile image
    ) throws Exception{
        return ResponseEntity.ok(exifService.getGeoLocation(image));
    }
}
