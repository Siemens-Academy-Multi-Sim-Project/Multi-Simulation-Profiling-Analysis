package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.service.ExtractCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController

public class FileUploadController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private final ExtractCSVService extractCSVService;
    public FileUploadController(ExtractCSVService extractCSVService) {
        this.extractCSVService = extractCSVService;
    }
    @PostMapping("/UploadCSV")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

        String uploadsDir = "/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
        if(! new File(realPathtoUploads).exists())
        {
            new File(realPathtoUploads).mkdir();
        }
        String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + orgName;
        File dest = new File(filePath);
        file.transferTo(dest);

       extractCSVService.extractfile(file,filePath);
         dest.delete();
        return "file Parsed succefully ";
    }
}
