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


    @PostMapping("registryUser1")
    public String registryUser1(@RequestBody User user){
        return activeService.activeFirstRegistryError(user.getId());
    }

    @PostMapping("registryUser2")
    public String registryUser2(@RequestBody User user){
        return activeService.activeFirstRegistryByFeign(user.getId());
    }
}
