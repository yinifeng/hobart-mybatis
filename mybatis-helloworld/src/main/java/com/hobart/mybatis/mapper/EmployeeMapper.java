package com.hobart.mybatis.mapper;

import com.hobart.mybatis.model.Employee;

public interface EmployeeMapper {
    
    Employee selectEmp(Long id);
}
