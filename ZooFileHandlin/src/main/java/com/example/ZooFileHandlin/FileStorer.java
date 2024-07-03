package com.example.ZooFileHandlin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileStorer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String filename;
    @Lob
    private String content;

}
