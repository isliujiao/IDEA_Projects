package org.example.luckydraw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.*.mapper")
public class LuckyDrawSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyDrawSystemApplication.class, args);
    }

}
