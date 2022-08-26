package com.example.demo;

import com.example.demo.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ssq
 * @create on 2022/08/04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpelTest {

    @Test
    public void contextLoads() {

    }

    @Test
    public void simpleTest(){
        SpelExpressionParser parser = new SpelExpressionParser();
//        Expression expression = parser.parseExpression("{1,2,3,4}");
//        List nums = (List)expression.getValue();
//        nums.forEach(System.out :: print);

        Expression expression = parser.parseExpression("false or not true");
        System.out.println(expression.getValue(Boolean.class));
    }

    @Test
    public void spelObjectTest(){
        Student st1 = new Student();
        Student st2 = new Student();
        st1.setName("xiaoming");
        st2.setName("xiaohong");
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(st2);
        standardEvaluationContext.setVariable("newName","la");
        parser.parseExpression("Name = #newName").getValue(standardEvaluationContext);
//        parser.parseExpression("name").setValue(standardEvaluationContext,"ll");
        System.out.println(st2.getName());
    }

    @Test
    public void spelListTest(){
        List<Integer> list = Arrays.asList(1, 2, 5, 6, 9, 11, 15, 17, 21, 35);
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(list);
        List<Integer> integerList = (List<Integer>) parser.parseExpression("#root.?[#this>15]").getValue(context);
        integerList.forEach(System.out :: println);
    }

    @Test
    public void spelMapTest(){
        Map<String,Integer> map = new HashMap<>();
        map.put("key1",1);
        map.put("key2",2);
        map.put("key3",3);
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(map);
        context.setVariable("map",map);
        Map<String, Integer> value = (Map<String, Integer>) parser.parseExpression("#map.?[value<2]").getValue(context);
    }
}
