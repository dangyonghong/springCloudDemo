package com.cloud.learn.controller;

import com.cloud.learn.VO.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @RequestMapping(value = "/provide" ,method = RequestMethod.GET)
    public Person provide(@RequestParam("age") Integer age){
        return new Person("name",age);
    }

}
