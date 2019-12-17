package cn.yihua.cloud.demo.activity.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ActivityController {

    @PostMapping("firstLoginActivity")
    public String firstLogin(@RequestBody Long id){
        return "success,userId : "+id +" get the tickets";
    }



    @PostMapping("firstLoginActivityTimeOut")
    public String firstLoginTimeout(@RequestBody Long id) throws InterruptedException{
        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(5)+1);
        return "success,userId : "+id +" get the tickets";
    }


    @PostMapping("firstLoginActivityError")
    public String firstLoginError(@RequestBody Long id) throws InterruptedException{
        throw new RuntimeException("运行异常");
      // return "用户"+id+",你好，现在服务正常了！";
    }



}
