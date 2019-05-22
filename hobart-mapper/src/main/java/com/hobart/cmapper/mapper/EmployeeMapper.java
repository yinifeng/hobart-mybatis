package com.hobart.cmapper.mapper;

import com.hobart.cmapper.model.Employee;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用mapper可以省略xxMapper.xml
 * 
 * 继承通用mapper提供的核心接口
 * 
 * 泛型类型是实体类
 */
public interface EmployeeMapper extends Mapper<Employee>{
    
}
