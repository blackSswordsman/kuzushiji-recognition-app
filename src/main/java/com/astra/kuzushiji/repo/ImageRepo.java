package com.astra.kuzushiji.repo;

import com.astra.kuzushiji.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    @Query("SELECT new com.astra.kuzushiji.repo.ImageRecord(image.createdAt,image.id) FROM Image image WHERE image.email=:userEmail ORDER BY image.createdAt DESC")
    List<ImageRecord> getUserImages(@Param("userEmail") String userEmail);
}
