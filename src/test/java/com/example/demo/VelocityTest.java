package com.example.demo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ssq
 * @create on 2022/08/04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VelocityTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testHelloWorld(){
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        // 获取模板文件
        Template t = ve.getTemplate("hellovelocity.vm");
        // 设置变量
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "Velocity");
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        ctx.put("list", list);
        // 输出
        StringWriter sw = new StringWriter();
        t.merge(ctx,sw);
        System.out.println(sw.toString());
    }
}
