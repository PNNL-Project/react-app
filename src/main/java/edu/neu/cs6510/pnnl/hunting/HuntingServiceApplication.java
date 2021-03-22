package edu.neu.cs6510.pnnl.hunting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("edu.neu.cs6510.pnnl.hunting.mapper")
public class HuntingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuntingServiceApplication.class, args);
    }

}
