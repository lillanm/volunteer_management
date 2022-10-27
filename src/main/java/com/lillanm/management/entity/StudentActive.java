package com.lillanm.management.entity;


import lombok.Data;

@Data
public class StudentActive {

    private Long id;

    private Long studentId;

    private Long activeId;

    private Integer volunteerTime;

    private Integer isPass;

    private Long passUser;
}
