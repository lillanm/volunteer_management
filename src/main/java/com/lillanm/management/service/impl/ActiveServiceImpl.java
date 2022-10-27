package com.lillanm.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lillanm.management.entity.Active;
import com.lillanm.management.mapper.ActiveMapper;
import com.lillanm.management.service.ActiveService;
import org.springframework.stereotype.Service;


@Service
public class ActiveServiceImpl extends ServiceImpl<ActiveMapper, Active> implements ActiveService {
}
