package com.lillanm.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lillanm.management.entity.StudentActive;
import com.lillanm.management.mapper.StudentActiveMapper;
import com.lillanm.management.service.StudentActiveService;
import org.springframework.stereotype.Service;

@Service
public class StudentActiveServiceImpl extends ServiceImpl<StudentActiveMapper, StudentActive> implements StudentActiveService {
}
