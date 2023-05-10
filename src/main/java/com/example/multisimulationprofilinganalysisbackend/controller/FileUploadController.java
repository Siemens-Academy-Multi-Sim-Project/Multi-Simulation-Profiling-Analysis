package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.service.ExtractCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file, String ClusterName) throws Exception {

        String uploadsDir = "/";
        String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
        if (!new File(realPathtoUploads).exists()) {
            new File(realPathtoUploads).mkdir();
        }
        String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + orgName;
        File dest = new File(filePath);
        file.transferTo(dest);

        try {
            extractCSVService.extractfile(file, filePath, ClusterName);
            dest.delete();
            HttpHeaders header = new HttpHeaders();
            header.set("Message", "file uploaded successfully");
            return new ResponseEntity<String>(header, org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            dest.delete();
            HttpHeaders header = new HttpHeaders();
            header.set("Message", e.getMessage());
            return new ResponseEntity<String>("Error While parsing File !", org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }
}
