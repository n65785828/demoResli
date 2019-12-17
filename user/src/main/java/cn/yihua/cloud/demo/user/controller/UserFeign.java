package cn.yihua.cloud.demo.user.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "activity")
public interface UserFeign {
    @PostMapping("firstLoginActivity")
    String firstLogin(@RequestBody Long id);

    @PostMapping("firstLoginActivityTimeOut")
    String firstLoginTimeout(@RequestBody Long id);

    @PostMapping("firstLoginActivityError")
    String firstLoginError(@RequestBody Long id) ;
}
