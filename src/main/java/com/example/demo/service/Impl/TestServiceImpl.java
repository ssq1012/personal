package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.ConsumerGoodsMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.ConsumerGoods;
import com.example.demo.pojo.Student;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TestServiceImpl implements TestService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ConsumerGoodsMapper consumerGoodsMapper;

    @Override
    public String getStringThing() {
        return "测试String类型事件";
    }

    @Override
    public String getStudent(String name) {
        Student selectById = studentMapper.selectById(1L);
        return selectById.toString();
    }

    @Override
    public String getGoods(String brand) {
        JSONObject result = new JSONObject();
        Map map = new HashMap<>();
        map.put("brand",brand);
        List<ConsumerGoods> list = consumerGoodsMapper.selectByMap(map);
        result.put("data",list);
        result.put("code","200");
        return result.toJSONString();
    }
}
