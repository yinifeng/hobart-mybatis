package com.hobart.mp.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hobart.mp.mapper.EmployeeMapper;
import com.hobart.mp.model.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class MpTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    
    private EmployeeMapper mapper=context.getBean(EmployeeMapper.class);

    /**
     * 条件构造器 删除操作
     */
    @Test
    public void testEntityWrapperDelete(){
        Integer result = this.mapper.delete(new EntityWrapper<Employee>()
                .eq("last_name", "sina")
                .eq("age", 29));
        System.out.println(result);
    }
    
    /**
     * 条件构造器 修改操作
     */
    @Test
    public void testEntityWrapperUpdate(){
        Employee employee=new Employee();
        employee.setEmail("888@qq.com");
        employee.setCreateTime(new Date());

        Integer result = this.mapper.update(employee,
                new EntityWrapper<Employee>()
                        .eq("last_name", "jack")
                        .eq("age", 26));
        System.out.println(result);
    }

    /**
     * 条件构造器 查询操作
     * 
     * Condition 用法类似
     */
    @Test
    public void testEntityWrapperSelect(){
        //都使用数据库表名字段
        //分页查询 年龄在22-28之间 性别为1 姓名为marry
        List<Employee> employees = this.mapper.selectPage(new Page<Employee>(1, 2),
                new EntityWrapper<Employee>()
                        .between("age", 22, 28)
                        .eq("gender", '0')
                        .eq("last_name", "marry")
        );
        employees.forEach(e->{
            System.out.println(e);
        });
        
        //*******************
        List<Employee> employees1 = this.mapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", "1")
                .like("last_name", "a")
                //.or()   //sql:(gender = ? AND last_name LIKE ? OR email LIKE ?)
                .orNew()    //sql:(gender = ? AND last_name LIKE ?) OR (email LIKE ?) 
                .like("email", "qq")
        );
        employees1.forEach(e-> System.out.println(e));
        
        //**************排序
        List<Employee> employees2 = this.mapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", "1")
                .orderBy("age")
                //.orderDesc(Arrays.asList("age"))
                .last("desc limit 1,2")

        );
        employees2.forEach(e-> System.out.println(e));
    }
    
    /**
     * 删除操作
     */
    @Test
    public void testCommonDelete(){
        //按主键删除
        //Integer result = this.mapper.deleteById(10L);
        //System.out.println(result);
        
        //根据条件删除 封装到map
        //Map<String,Object> map=new HashMap<>();
        //数据库表的列名
        //map.put("last_name", "MP003");
        //map.put("email", "mp3@baomidou.com");
        //Integer result = this.mapper.deleteByMap(map);
        //System.out.println(result);
        
        //批量根据主键删除
        Integer result = this.mapper.deleteBatchIds(Arrays.asList(7L, 8L));
        System.out.println(result);

    }

    /**
     * 查询
     */
    @Test
    public void testCommonSelect(){
        //通过id查询
        //Employee employee = mapper.selectById(5L);
        //System.out.println(employee);
        
        //通过多个列查询 id+lastName
        //Employee employee = new Employee();
        //employee.setId(5L);
        //employee.setLastName("alen");
        //Employee employee1 = mapper.selectOne(employee);
        //System.out.println(employee1);
        
        //批量查询
        //List<Employee> employees = mapper.selectBatchIds(Arrays.asList(11L, 12L, 13L));
        //employees.forEach(e->{
        //    System.out.println(e);
        //});
        
        //通过map封装查询条件
        //Map<String,Object> map =new HashMap<>();
        //map.put("last_name", "tom");
        //map.put("gender", '1');
        ////数据库表的字段
        //List<Employee> employees = this.mapper.selectByMap(map);
        //employees.forEach(e->{
        //    System.out.println(e);
        //});
        
        //分页查询 内存分页 并不是物理分页
        List<Employee> employees = this.mapper.selectPage(new Page<>(2, 3), null);
        employees.forEach(e->{
            System.out.println(e);
        });
    }
    
    /**
     * 通用的更新操作
     */
    @Test
    public void testCommonUpdate(){
        //初始化修改对象
        Employee employee=new Employee();
        employee.setId(10L);
        employee.setLastName("MP004");
        employee.setCreateTime(new Date());
        //只更新不为空的字段
        //Integer result = mapper.updateById(employee);
        //更新所有的列不管有没有值
        Integer result = mapper.updateAllColumnById(employee);
        System.out.println("===>"+result);
    }
    
    @Test
    public void testCommonInsert(){
        Employee employee = new Employee();
        employee.setLastName("MP003");
        employee.setAge(30);
        employee.setEmail("mp3@baomidou.com");
        employee.setGender('1');
        employee.setSalary(BigDecimal.valueOf(8000.0));
        employee.setCreateTime(new Date());
        //为空的字段不显示
        int i = mapper.insert(employee);
        //插入所有字段
        //mapper.insertAllColumn(employee);
        System.out.println("======>"+i);
        //直接可以获取到主键值
        System.out.println(employee);
    }
    
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println("=====>"+dataSource);
        System.out.println("======>"+dataSource.getConnection());
    }
}
