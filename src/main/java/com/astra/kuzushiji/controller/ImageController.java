package com.astra.kuzushiji.controller;

import com.astra.kuzushiji.repo.ImageRecord;
import com.astra.kuzushiji.repo.ImageRepo;
import com.astra.kuzushiji.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ImageController {

    private final ImageService imageService;
    private final ImageRepo imageRepo;

    @PostMapping(value = "/process_image", consumes = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE},produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource processImage(@RequestParam("image") MultipartFile image) throws IOException {
        return imageService.processImage(image.getResource());
    }
    @GetMapping(value="/image/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImage(@PathVariable("id") Long id) throws IOException {
        return imageService.getImageById(id);
    }
    @GetMapping("/user_images")
    public List<ImageRecord> getUserImages(){
        return imageService.getUserImages();
    }

}
