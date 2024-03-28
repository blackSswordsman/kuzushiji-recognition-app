package com.astra.kuzushiji.repo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageRecord {
    private final LocalDateTime createdAt;
    private final Long id;
}
