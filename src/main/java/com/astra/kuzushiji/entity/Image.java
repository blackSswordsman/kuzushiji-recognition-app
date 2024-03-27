package com.astra.kuzushiji.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="image")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private Timestamp timestamp;

    @Column
    private String name;

    @Column
    @Lob
    private byte[] image;

}
