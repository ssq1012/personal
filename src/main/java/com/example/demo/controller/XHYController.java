package com.example.demo.controller;

import com.example.demo.service.XHYService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ssq
 * @create on 2023/02/23
 */
@RequestMapping("/xhy")
@RestController
public class XHYController {

    @Autowired
    XHYService xhyService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String queryXHY(@RequestParam("param") String param){
        if (StringUtils.isNotBlank(param)){
            return xhyService.queryXHY(param);
        }
        return "";
    }
}
