package com.chidra.presence.web.rest;

import com.chidra.presence.service.GenerateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PresenceController {

    @Autowired
    private GenerateReportService service;

    @GetMapping("presence")
    public String generatePresence() {
        try{
            service.generateReport();
        }catch (Exception e){
            e.printStackTrace();
            return  "ooops Error: "+ e.getMessage();
        }

        return "Success";

    }
}
