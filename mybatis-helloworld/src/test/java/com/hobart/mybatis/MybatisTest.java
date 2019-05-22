package com.hobart.mybatis;

import com.hobart.mybatis.mapper.EmployeeMapper;
import com.hobart.mybatis.model.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    
    private static SqlSessionFactory sqlSessionFactory;


    /**
     *  1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 		1）、根据全局配置文件得到SqlSessionFactory；
     * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     * @throws IOException
     */
    @BeforeClass
    public static void setUp() throws IOException {
        //当前classpath下
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 非mapper接口
     * 
     * namespace.id
     */
    @Test
    public void test001(){
        //获取sqlSession实例，能执行已经映射的sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee emp = sqlSession.
                    selectOne("com.hobart.mybatis.mapper.EmployeeMapper.selectEmp", 1);
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * mapper接口
     * <mappers>
            <mapper resource="EmployeeMapper.xml" />
     </mappers>
     */
    @Test
    public void test002(){
        //获取sqlSession实例，能执行已经映射的sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = employeeMapper.selectEmp(1L);
            System.out.println(employeeMapper+"<=====>"+employeeMapper.getClass());
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }
    }
    
    @AfterClass
    public static void cleanUp(){
        
    }
}
