package cn.yihua.cloud.demo.user.controller;

import cn.yihua.cloud.demo.user.pojo.User;
import cn.yihua.cloud.demo.user.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private ActiveService activeService;

    @PostMapping("registryUser")
    public String registryUser(@RequestBody User user){
        return activeService.activeFirstRegistry(user.getId());
    }
}
