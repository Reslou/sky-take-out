package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

/**
 * The interface Employee service.
 */
public interface EmployeeService {

    /**
     * 员工登录
     *
     * @param employeeLoginDTO the employee login dto
     * @return employee
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 员工退出
     *
     * @param employeeDTO the employee dto
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO the employee page query dto
     * @return page result
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     *
     * @param id     the id
     * @param status the status
     */
    void startOrStop(long id, Integer status);

    /**
     * 根据id查询员工
     *
     * @param id the id
     * @return the by id
     */
    Employee getById(long id);

    /**
     * 编辑员工信息
     *
     * @param employeeDTO the employee dto
     */
    void update(EmployeeDTO employeeDTO);
}
