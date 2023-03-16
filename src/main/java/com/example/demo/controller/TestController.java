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

    @RequestMapping("/test")
    public String test()
    {
        return "hello world!";
    }

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

    @RequestMapping(value = "/createStudent", method = RequestMethod.GET)
    public void createStudent(@RequestParam("count") String count)
    {
        testService.createStudent(count);
    }

    @RequestMapping(value = "/exportStudent", method = RequestMethod.GET)
    public void exportStudent(@RequestParam("count") String count)
    {
        testService.exportStudent(count);
    }

    @PostMapping("/getGoods")
    public String getGoods(@RequestBody String name)
    {
        return testService.getGoods(name);
    }
}
