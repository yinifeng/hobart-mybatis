package com.hobart.mp.service.impl;

import com.hobart.mp.beans.Employee;
import com.hobart.mp.mapper.EmployeeMapper;
import com.hobart.mp.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hobart
 * @since 2019-05-21
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
