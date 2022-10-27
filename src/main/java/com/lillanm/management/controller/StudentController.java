package com.lillanm.management.controller;


import com.lillanm.management.common.R;
import com.lillanm.management.entity.Student;
import com.lillanm.management.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public R<String> test(@RequestBody Student student){
        log.info(student.toString());
        studentService.save(student);
        return R.success("测试成功");
    }

}
