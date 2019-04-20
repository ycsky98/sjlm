package cn.sjlm.dao;

import cn.sjlm.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SjlmDaoApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads(){
        User user= userDao.selectUserById(1);
        System.out.println(user);
    }

}
