package cn.sjlm.sjlmservice.sjlmserviceImpl;

import cn.sjlm.sjlmservice.HelloService;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0", timeout = 2000)
public class HelloServiceImpl implements HelloService {
    @Override
    public void SayHello() {
        System.out.println("hello");
    }
}
