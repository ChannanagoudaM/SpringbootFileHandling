package com.example.ZooFileHandlin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/zoo/api")
public class ZooController {

    @Autowired
    ZooService service;

    @GetMapping("/get")
    public ResponseEntity<Resource> download() throws Exception
    {
            String filename="Zoo.xlsx";
            ByteArrayInputStream byteArrayInputStream =service.getAll();
        InputStreamResource resource=new InputStreamResource(byteArrayInputStream);

        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        File excelFile = new File(desktopPath + File.separator + filename);

       return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(resource);
    }

    @GetMapping("/pdf")
    public ResponseEntity<Resource> downloadpdf() throws Exception
    {
        String filename="Zoo.pdf";

        ByteArrayInputStream bis=service.getAllPdf();
        InputStreamResource resource=new InputStreamResource(bis);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.APPLICATION_PDF).body(resource);        
    }

}
