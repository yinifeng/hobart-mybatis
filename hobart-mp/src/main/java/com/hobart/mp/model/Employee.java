package com.hobart.mp.model;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 全局配置的表前缀 + 类名
 */
//@TableName(value = "t_hb_employee")
public class Employee {

    /**
     * 配置全局配置
     */
    //@TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("last_name")
    private String lastName;
    
    private Integer age;
    
    private Character gender;
    
    private String email;
    
    private BigDecimal salary;
    @TableField("create_time")
    private Date createTime;

    /**
     * 数据库字段不存在
     * 忽略字段
     */
    @TableField(exist = false)
    private String address;

    public Employee(Long id, String lastName, Integer age, Character gender, String email, BigDecimal salary, Date createTime) {
        this.id = id;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.createTime = createTime;
    }
    
    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", createTime=" + createTime +
                '}';
    }
}
