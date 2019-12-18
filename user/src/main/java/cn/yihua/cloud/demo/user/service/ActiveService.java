package cn.yihua.cloud.demo.user.service;


import cn.yihua.cloud.demo.user.controller.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ActiveService {

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeign userFeign;

    public String activeFirstRegistry(Long id) {
        return cbFactory.create("activeFirstRegistry").run(() -> restTemplate.postForObject("http://activity/firstLoginActivity", id, String.class), throwable -> "访问服务出错1");
    }


    public String activeFirstRegistryError(Long id) {
        return cbFactory.create("activeFirstRegistryErrorTimeOut").run(() -> restTemplate.postForObject("http://activity/firstLoginActivityTimeOut", id, String.class), throwable -> "访问服务出错2");
    }


    public String activeFirstRegistryByFeign(Long id) {
        return cbFactory.create("activeFirstRegistryByFeign").run(() -> userFeign.firstLoginError(id), throwable -> "访问服务出错3"+throwable.getMessage());
    }

}
