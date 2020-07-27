package com.iucse.passnet.recruitment.adapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeRestController {

    @GetMapping(value = "")
    public String getHomePage(){
        return "Hello";
    }
}
