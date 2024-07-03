package com.example.ZooFileHandlin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ZooService {

    @Autowired
    private  ZooRepo zooRepo;

    @Autowired
    private FileRepository fileRepository;

    public ByteArrayInputStream getAll()
    {
        List<Zoo>list=zooRepo.findAll();

        return Helper.dateToExcel(list);
    }

    public ByteArrayInputStream getAllPdf()
    {
        List<Zoo>list=zooRepo.findAll();

        return Helper.dataToPdf(list);
    }

    public String savePdfFile(MultipartFile file) throws IOException {

        if(file.isEmpty())
        {
            return "File can not be empty / please upload file";
        }

        String content = Base64.getEncoder().encodeToString(file.getBytes());
        FileStorer fileStorer = FileStorer.builder()
                .filename(file.getOriginalFilename())
                .content(content)
                .build();

        FileStorer save = fileRepository.save(fileStorer);
        if(save.getId()!=null)
        {
            return "success";
        }
        return "failed";
    }

    public byte[] convertToPdf(String filename)
    {
        FileStorer file = fileRepository.findByFilename(filename);
        String base64String = file.getContent();
        return Base64.getDecoder().decode(base64String);

    }


}
