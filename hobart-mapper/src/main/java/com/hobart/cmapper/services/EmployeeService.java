package com.hobart.cmapper.services;

import com.hobart.cmapper.mapper.EmployeeMapper;
import com.hobart.cmapper.model.Employee;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getOne(Employee emp) {
        return this.employeeMapper.selectOne(emp);
    }

    public Employee getEmployeeById(Long id) {
        return this.employeeMapper.selectByPrimaryKey(id);
    }

    public boolean isExist(Long id) {
        return this.employeeMapper.existsWithPrimaryKey(id);
    }

    public void saveEmployee(Employee employee) {
        this.employeeMapper.insert(employee);
    }

    public void saveEmployeeSelective(Employee employee) {
        this.employeeMapper.insertSelective(employee);
    }

    public void updateEmployeeSelective(Employee employee) {
        this.employeeMapper.updateByPrimaryKeySelective(employee);
    }
    
    
    public int removeEmployee(Employee employee) {
        return this.employeeMapper.delete(employee);
    }

    public int removeEmployeeByKey(Long id) {
        return this.employeeMapper.deleteByPrimaryKey(id);
    }

    public List<Employee> getEmployeeByExample(Example example) {
        return this.employeeMapper.selectByExample(example);
    }

    public List<Employee> getEmpListByRowBounds(RowBounds rowBounds) {
        //第一个是查询条件的封装
        return this.employeeMapper.selectByRowBounds(null, rowBounds);
    }
}
