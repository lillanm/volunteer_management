package com.lillanm.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lillanm.management.entity.Student;
import com.lillanm.management.mapper.StudentMapper;
import com.lillanm.management.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
