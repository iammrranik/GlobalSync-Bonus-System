package com.globalsync.bonus.system.service;

import com.globalsync.bonus.system.domain.Employee;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    int addEmployee(Employee employee);
    Optional<Employee> getEmployeeById(Integer id);
    List<Employee> getAllEmployees();
    List<Employee> getAllEmployees(int page, int size);
    int getEmployeeCount();
    int updateEmployee(Employee employee);
    int removeEmployee(int id);
}