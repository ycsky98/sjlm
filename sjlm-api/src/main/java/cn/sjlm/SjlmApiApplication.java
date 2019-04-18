package cn.sjlm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.sjlm.sjlmapi","cn.sjlm.sjlmdao","cn.sjlm.config","cn.sjlm.sjlmpojo","cn.sjlm.sjlmservice"})
@MapperScan(basePackages = {"cn.sjlm.sjlmdao.**"})
public class SjlmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SjlmApiApplication.class, args);
    }
}
