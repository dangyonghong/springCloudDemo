package com.cloud.learn.remote;

import com.cloud.learn.VO.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("provide")
public interface PersonRemoteService {

    @RequestMapping(value = "/provide/provide" ,method = RequestMethod.GET)
    public Person provide(@RequestParam("age") Integer age);

}
