package com.example.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author ssq
 */
@TableName("student_info")
public class Student implements Serializable {
    private static final long serialVersionUID = -3954607291890783350L;
    @TableField(value = "stu_name")
    @ExcelProperty("姓名")
    private String stuName;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("评论")
    private String commets;
    @ExcelProperty("序号")
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCommets() {
        return commets;
    }

    public void setCommets(String commets) {
        this.commets = commets;
    }
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
