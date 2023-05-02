package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.service.ExtractCSVService;
import com.example.multisimulationprofilinganalysisbackend.utils.csv.exceptions.DelimiterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController

public class FileUploadController {
    @Autowired
    private HttpServletRequest request;
    ExtractCSVService extractCSVService=new ExtractCSVService();
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
        return "file uploaded to "+filePath;
    }
}
