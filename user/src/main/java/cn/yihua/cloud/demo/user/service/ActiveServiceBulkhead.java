package cn.yihua.cloud.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActiveServiceBulkhead {
    @Autowired
    private RestTemplate restTemplate;


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
