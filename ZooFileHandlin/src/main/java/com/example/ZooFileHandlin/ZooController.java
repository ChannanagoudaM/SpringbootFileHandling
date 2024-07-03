package com.example.ZooFileHandlin;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/zoo/api")
public class ZooController {

    @Autowired
    ZooService service;

    @GetMapping("/excel")
    public ResponseEntity<Resource> excelFormat() throws Exception
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
    public ResponseEntity<Resource> pdfFormat() throws Exception
    {
        String filename="Zoo.pdf";
        ByteArrayInputStream bis=service.getAllPdf();
        InputStreamResource resource=new InputStreamResource(bis);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.APPLICATION_PDF).body(resource);
    }



    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdfDocument(@RequestParam("file") MultipartFile file) throws IOException {
        String s = service.savePdfFile(file);
        return ResponseEntity.ok().body(s);
    }


    @GetMapping("/retrieve/{filename}")
    public ResponseEntity<byte[]> makePdfFromDataBase(@PathVariable String filename)
    {
        byte[] bytes = service.convertToPdf(filename);

        return ResponseEntity.ok().contentType(MediaType.valueOf("application/pdf")).body(bytes);
    }




}
