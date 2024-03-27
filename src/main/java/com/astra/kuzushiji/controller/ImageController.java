package com.astra.kuzushiji.controller;

import com.astra.kuzushiji.repo.ImageRepo;
import com.astra.kuzushiji.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ImageController {

    private final ImageService imageService;
    private final ImageRepo imageRepo;

    @PostMapping(value = "/process_image", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource processImage(@RequestParam("image") MultipartFile image) throws IOException {
        Resource processedImage = imageService.processImage(image.getResource());
        imageService.saveImage(processedImage);
        return processedImage;
    }
    @GetMapping(value="/return/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImage(@PathVariable("id") Long id) throws IOException {
        return new FileSystemResource(imageService.getImageById(id));

    }

}
