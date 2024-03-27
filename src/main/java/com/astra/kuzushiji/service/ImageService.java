package com.astra.kuzushiji.service;

import com.astra.kuzushiji.entity.Image;
import com.astra.kuzushiji.repo.ImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepo imageRepo;
    private final TensorFlowServerClient tensorFlowServerClient;

    public void saveImage(Resource imageFile) throws IOException {
        Image savedImage = new Image();
        savedImage.setName(imageFile.getFilename());
        savedImage.setImage(imageFile.getContentAsByteArray());
        imageRepo.save(savedImage);
    }
    public File getImageById (Long id) throws IOException {
        var tempFile = File.createTempFile("tmp", "dbImage");
        StreamUtils.copy(imageRepo.findById(id).get().getImage(), new FileOutputStream(tempFile));
        return tempFile;
    }
    public List<Image> getAllImages (){
        return (List<Image>) imageRepo.findAll();
    }

    public Resource processImage(Resource image){
        File file = tensorFlowServerClient.processImage(image);
        return new FileSystemResource(file);


    }

}
