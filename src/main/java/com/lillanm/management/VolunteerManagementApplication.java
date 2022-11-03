package com.lillanm.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan//扫描过滤器
@EnableTransactionManagement
public class VolunteerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolunteerManagementApplication.class, args);
    }

}
