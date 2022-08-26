package com.example.demo;

import org.apache.http.client.utils.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamTest {
    final static ThreadPoolExecutor POOR = new ThreadPoolExecutor(10,20,300L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),new ThreadPoolExecutor.DiscardOldestPolicy());
    static List<String> stringList = Arrays.asList("aa","bb","cc","dd");

    @Test
    public void contextLoads() {

    }


    @Test
    public void testJoiner(){
        String collect = stringList.stream().collect(Collectors.joining("","(",")"));
        System.out.println(collect);
    }

    @Test
    public void testDate(){
        LocalDate preDate = LocalDate.of(1996, 10, 5);
        LocalDate subDate = LocalDate.now();
        // 调用Period对象的between方法计算两个日期之间的间隔
        Period period = Period.between(preDate, subDate);
        // 调用Period的getYears方法，获得这段时间的年数，并且将结果进行输出
        System.out.printf("过去了%d年\n",period.getYears());
        // 调用Period的toTotalMonths方法，获取此期间的总月数，此值为间隔年数*12
        System.out.printf("过去了%d月\n",period.toTotalMonths());
        //toEpochDay将此日期转换为大纪元日,Epoch Day count是第0天为1970-01-01 的简单递增计数。
        long days = subDate.toEpochDay() - preDate.toEpochDay();
        System.out.printf("过去了%d日\n",days);
    }

    @Test
    public void testDate2(){
         List<String> list = new ArrayList<>();
         list.add("1");
         list.add("2");
        String remove = list.remove(0);
        System.out.println(remove);
    }


    @Test
    public void CompletableFuture(){
        Long aLong = 1L;
        Integer integer = Optional.ofNullable(aLong).orElse(0L).intValue();
        System.out.println(integer.intValue());

        LocalDate now = LocalDate.now();
        LocalDateTime localDateTime = now.atTime(8, 0);
        System.out.println(localDateTime);
        Date from = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(from);
    }


}
