package com.hobart.cmapper.mapper;
import com.hobart.cmapper.model.Employee;
import com.hobart.cmapper.services.EmployeeService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmployeeMapperTest {
    private static Logger logger = LoggerFactory.getLogger(EmployeeMapperTest.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    
    private EmployeeService employeeService=context.getBean(EmployeeService.class);

    @Test
    public void selectAll() {
    }

    /**
     * 根据主键查询
     */
    @Test
    public void testSelectByPrimaryKey() {
        //主键赋值
        Long id = 2L;
        
        //主键查询
       Employee employeeResult= this.employeeService.getEmployeeById(id);
       
       logger.info("{}",employeeResult);
    }

    @Test
    public void selectCount() {
    }

    @Test
    public void select() {
    }

    /**
     * 使用非空的值作为where条件
     * 固定都是=号比较
     */
    @Test
    public void testSelectOne() {
        //1.创建封装查询条件的实体类
        Employee emp = new Employee();
        emp.setLastName("tom");
        emp.setSalary(BigDecimal.valueOf(3000.0));
        
        Employee employee = employeeService.getOne(emp);
        logger.info("{}",employee);
    }

    /**
     * 根据主键删除
     */
    @Test
    public void testDeleteByPrimaryKey() {
        Long id = 4L;
        this.employeeService.removeEmployeeByKey(id);
    }

    /**
     * 这个删除 注意判断实体对象的属性
     * 不然会删除整个表的数据
     */
    @Test
    public void testDelete() {
        Employee employee=null;
        if (employee != null){
            this.employeeService.removeEmployee(employee);
        }
    }

    @Test
    public void testInsert() {
        //构建保存的实体对象
        Employee employee = new Employee(null, "mick", 28,
                '0', "120@qq.com", BigDecimal.valueOf(6000.0), new Date());
        this.employeeService.saveEmployee(employee);
        //获取保存对象后的值
        logger.info("empId={}",employee.getId());
    }

    /**
     * 
     * 非主键字段如果为null 值，则不加入到SQL 语句中。
     * 
     * 空串会加入到sql语句
     */
    @Test
    public void insertSelective() {
        //构建保存的实体对象
        //Employee employee = new Employee(null, "alen", 29,
        //        '1', null, BigDecimal.valueOf(8000.0), new Date());
        Employee employee = new Employee(null, "sina", 29,
                '1', "", BigDecimal.valueOf(8000.0), new Date());
        this.employeeService.saveEmployeeSelective(employee);
        //获取保存对象后的值
        logger.info("empId={}",employee.getId());
        
    }

    /**
     * 主键判断是否存在
     * 
     * SELECT CASE WHEN COUNT(id) > 0 THEN 1 ELSE 0 END AS result FROM t_hb_employee WHERE id = ? 
     */
    @Test
    public void existsWithPrimaryKey() {
        //主键赋值
        Long id = 10L;

        //主键查询
        boolean result= this.employeeService.isExist(id);

        logger.info("{}",result);
    }

    @Test
    public void updateByPrimaryKey() {
    }

    /**
     * 只更新有值的字段
     * 
     * 这个实体对象的某个属性为null，也不会更新数据库的值
     * 
     * 如果是空字符串是会更新的
     * 
     */
    @Test
    public void updateByPrimaryKeySelective() {
        //构建更新的实体对象
        //Employee employee = new Employee(5L, null, null,
        //        null, "119@qq.com", null, null);
        Employee employee = new Employee(3L, null, null,
                null, "", null, null);
        this.employeeService.updateEmployeeSelective(employee);
        
    }

    @Test
    public void deleteByExample() {
    }

    /**
     * QBC查询 
     * 设置复杂的条件查询，拼接sql
     */
    @Test
    public void testSelectByExample() {
        //目标：WHERE (salary > ? AND age < ?) OR (salary < ? AND age > ?)
        //创建 Example对象
        Example example = new Example(Employee.class);
        
        //*******************************************
        //i.设置排序信息
        example.orderBy("salary").asc().orderBy("age").desc();
        
        //ii.设置"去重"
        example.setDistinct(true);
        
        //iii.设置select字段
        example.selectProperties("id","lastName","age","salary");
        
        //*******************************************
        
        //通过Example对象构建 Criteria
        Example.Criteria criteria1 = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();
        
        //在Criteria分别设置查询条件
        //property参数：实体类属性
        //value参数：实体的属性
        criteria1.andGreaterThan("salary", 4000).andLessThan("age", 24);
        criteria2.andLessThan("salary", 5000).andGreaterThan("age", 24);
        
        //OR关系
        example.or(criteria2);
        
        //查询
        List<Employee> employeeList=this.employeeService.getEmployeeByExample(example);
        employeeList.forEach(emp->{
            logger.info("{}",emp);
        });
    }

    @Test
    public void selectCountByExample() {
    }

    @Test
    public void selectOneByExample() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void selectByExampleAndRowBounds() {
    }

    /**
     * 分页查询
     * 只是在内存中分页 没有limit，对于大数据量不推荐
     * 
     */
    @Test
    public void testSelectByRowBounds() {
        int pageNo = 2;//第几页
        int pageSize = 2;//每一页的总记录数
        
        int index = (pageNo - 1) * pageSize;

        RowBounds rowBounds = new RowBounds(index,pageSize);
        List<Employee> employeeList=this.employeeService.getEmpListByRowBounds(rowBounds);
        employeeList.forEach(emp ->{
            logger.info("{}",emp);
        });
    }
}