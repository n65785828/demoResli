package cn.yihua.cloud.demo.user.service;


import cn.yihua.cloud.demo.user.controller.UserFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActiveService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeign userFeign;

    private static final String BACKEND = "backendA";

    public String failure(Long id,Throwable throwable){
        return "调用服务出错时，则执行该方法 错误信息:"+throwable.getMessage();
    }

    @CircuitBreaker(name = BACKEND,fallbackMethod = "failure")
//    @HystrixCommand(commandKey="registryUser",fallbackMethod = "fallback")
    public String activeFirstRegistry(Long id){
        return restTemplate.postForObject("http://activity/firstLoginActivityTimeOut", id, String.class);
    }

    @CircuitBreaker(name = "backendY",fallbackMethod = "failure")
//    @HystrixCommand(commandKey="registryUser1",fallbackMethod = "fallback")
    public String activeFirstRegistryError(Long id){
        return restTemplate.postForObject("http://activity/firstLoginActivityError", id, String.class);
    }

    @CircuitBreaker(name = BACKEND,fallbackMethod = "failure")
    //@HystrixCommand(commandKey="registryUser2",fallbackMethod = "fallback")
    public String activeFirstRegistryByFeign(Long id){
        return userFeign.firstLoginError(id);
    }

    public String fallback(Long id){
        return "调用出错,启用备用方法:"+id;
    }
}
