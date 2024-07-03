package com.example.ZooFileHandlin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

public interface FileRepository extends JpaRepository<FileStorer,Integer> {

    FileStorer findByFilename(String filename);
}
