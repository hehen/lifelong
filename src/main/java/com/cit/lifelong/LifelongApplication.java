package com.cit.lifelong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.cit.lifelong.mapper") //不加这句扫描不到mapper
public class LifelongApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifelongApplication.class, args);
    }

}
