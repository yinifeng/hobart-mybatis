package com.hobart.cmapper.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 使用的数据类型都是包装类型
 * 通用mapper
 * 考虑到基本数据类型在java类中都有默认值，会导致mybatis在执行相关操作很难
 * 判断空
 * 建议都使用包装类型
 * 
 * @Table 默认是类名首字母小写 作为表名
 * 
 */
@Table(name = "t_hb_employee")
public class Employee {

    /**
     * @Id 注解
     * 通用Mapper 在执行xxxByPrimaryKey(key)方法时，有两种情况。
     * 情况1：没有使用@Id 注解明确指定主键字段
     * 
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * 保存时主键自增 自动写回到实体中的属性
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@KeySql
    private Long id;

    /**
     * @Column 注解
     * 作用：建立实体类字段和数据库表字段之间的对应关系。
     * 默认规则：
     * 实体类字段：驼峰式命名
     * 数据库表字段：使用“_”区分各个单词
     * 用法：在@Column 注解的name 属性中指定目标字段的字段名
     */
    @Column(name = "last_name")
    private String lastName;
    
    private Integer age;
    
    private Character gender;
    
    private String email;
    
    private BigDecimal salary;
    
    private Date createTime;
    
    /**
     * 非数据库表中字段
     * @Transient
     */
    @Transient
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
