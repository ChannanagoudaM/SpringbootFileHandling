package com.example.ZooFileHandlin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ZooService {

    @Autowired
    private  ZooRepo zooRepo;

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
}
