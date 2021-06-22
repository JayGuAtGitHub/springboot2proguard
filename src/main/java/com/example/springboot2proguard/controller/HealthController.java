package com.example.springboot2proguard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthController {
    @ResponseBody
    @GetMapping(value = "healthCheck/getServiceStatus")
    public Integer getServiceStatus(){
        Integer result = 0;
        if (LocalDateTime.now().getSecond() * 2 == 0){
            result = 1;
        }else {
            result = 0;
        }
        return result;
    }
}
