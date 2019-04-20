package cn.sjlm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.sjlm.**"})
@MapperScan(basePackages = {"cn.sjlm.dao.**"})
public class SjlmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SjlmApiApplication.class, args);
    }
}
