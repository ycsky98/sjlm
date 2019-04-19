package cn.sjlm.sjlmdao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.sjlm.sjlmdao.HelloDao;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SjlmDaoApplicationTests {

    @Autowired
    private HelloDao helloDao;

    @Test
    public void contextLoads(){
        System.out.println(helloDao.select());
    }

}
