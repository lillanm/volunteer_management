package com.lillanm.management.controller;


import com.lillanm.management.service.StudentActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentActive")
public class StudentActiveController {

    @Autowired
    private StudentActiveService studentActiveService;
}
