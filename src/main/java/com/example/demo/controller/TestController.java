package com.example.demo.controller;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/getMsg")
    public String getMsg()
    {
        return testService.getStringThing();
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public String getStudent(@RequestParam("name") String name)
    {
        return testService.getStudent(name);
    }

    @PostMapping("/getGoods")
    public String getGoods(@RequestBody String name)
    {
        return testService.getGoods(name);
    }
}
