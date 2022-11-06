package com.lillanm.management.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {

    private Long id;

    private String stuNumber;

    private String password;

    private String className;

    private String phone;

    private String sex;

    private String email;

    private Long volunteerTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
