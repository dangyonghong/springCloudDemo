package com.cloud.learn.controller;

import com.cloud.learn.VO.Person;
import com.cloud.learn.remote.PersonRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试feign调用",tags = "测试feign调用")
public class ProducerController {

    @Autowired
    private PersonRemoteService personRemoteService;

    @RequestMapping(value = "/testFeign",method = RequestMethod.GET)
    @ApiOperation(tags = "测试feign调用",value = "testFeign")
    public Person get(@RequestParam("age")  int age){
        Person provide = personRemoteService.provide(age);
        return provide;
    }
}
