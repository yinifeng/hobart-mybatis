package com.hobart.mp.test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class TestMP {
    
    @Test
    public void testGeneator(){
        //1.全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) //是否支持AR模式
              .setAuthor("hobart") //作者
              .setOutputDir("D:\\workspace\\sourceCode\\idea-project\\mybatis\\hobart-mp-geneator\\src\\main\\java") //生成路劲
              .setFileOverride(true) //文件覆盖
              .setIdType(IdType.AUTO) //主键策略
              .setServiceName("%sService") //设置生成的Servcie接口名字的首字母是否为I，默认是I
              .setBaseResultMap(true).setBaseColumnList(true);
        
        //2.数据源配置
        DataSourceConfig dsConfig=new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/hb_mybatis?useUnicode=true&characterEncoding=utf8")
                .setUsername("root")
                .setPassword("123456");
        
        //3.策略配置
        StrategyConfig stConfig=new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写名
                .setDbColumnUnderline(true) //指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略
                .setTablePrefix("t_hb")
                .setInclude("t_hb_employee");  //生成表
        
        //4.包名策略配置
        PackageConfig pkConfig=new PackageConfig();
        pkConfig.setParent("com.hobart.mp")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("beans")
                .setXml("mapper");
        //5.整合配置
        AutoGenerator ag=new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        
        //6.执行
        ag.execute();

    }
}
