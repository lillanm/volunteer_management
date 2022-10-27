package com.lillanm.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lillanm.management.entity.Admin;
import com.lillanm.management.mapper.AdminMapper;
import com.lillanm.management.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
