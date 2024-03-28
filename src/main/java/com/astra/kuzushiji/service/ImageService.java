package com.astra.kuzushiji.service;

import com.astra.kuzushiji.entity.Image;
import com.astra.kuzushiji.exception.ImageAccessException;
import com.astra.kuzushiji.exception.ImageNotFoundException;
import com.astra.kuzushiji.exception.UserEmailException;
import com.astra.kuzushiji.repo.ImageRecord;
import com.astra.kuzushiji.repo.ImageRepo;
import com.astra.kuzushiji.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepo imageRepo;
    private final TensorFlowServerClient tensorFlowServerClient;

    private void saveImage(byte[] imageData)  {
        Image savedImage = new Image();
        savedImage.setImage(imageData);
        savedImage.setEmail(UserUtils.getCurrentUserEmail().orElseThrow(UserEmailException::new));
        imageRepo.save(savedImage);
    }
    public Resource getImageById (Long id) throws IOException {
        Image image = imageRepo.findById(id).orElseThrow(ImageNotFoundException::new);
        if(!Objects.equals(UserUtils.getCurrentUserEmail().orElseThrow(UserEmailException::new),image.getEmail())){
            throw new ImageAccessException();
        }
        return new ByteArrayResource(image.getImage());
    }
    public List<Image> getAllImages (){
        return (List<Image>) imageRepo.findAll();
    }

    public Resource processImage(Resource image){
        var file = tensorFlowServerClient.processImage(image);
        saveImage(file);
        return new ByteArrayResource(file);
    }

    public List<ImageRecord> getUserImages() {
        return imageRepo.getUserImages(UserUtils.getCurrentUserEmail().orElseThrow(UserEmailException::new));
    }
}
