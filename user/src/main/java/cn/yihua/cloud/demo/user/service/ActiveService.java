package cn.yihua.cloud.demo.user.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActiveService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String BACKEND = "backendA";
    @CircuitBreaker(name = BACKEND)
    public String activeFirstRegistry(Long id){
        return restTemplate.postForObject("http://activity/firstLoginActivityTimeOut", id, String.class);
    }

    public String activeFirstRegistryError(Long id){
        return restTemplate.postForObject("http://activity/firstLoginActivityError", id, String.class);
    }

    public String errorMethod(Long id){
        return "can't get tickets,please wait a minute";
    }
}
