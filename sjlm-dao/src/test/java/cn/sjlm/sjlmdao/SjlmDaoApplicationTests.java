package cn.sjlm.sjlmdao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SjlmDaoApplicationTests {

    @Autowired
    private HelloDao dao;

    @Test
    public void contextLoads(){
        System.out.println(dao.select());
    }

}
