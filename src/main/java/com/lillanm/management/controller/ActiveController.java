package com.lillanm.management.controller;


import com.lillanm.management.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/active")
public class ActiveController {

    @Autowired
    private ActiveService activeService;
}
