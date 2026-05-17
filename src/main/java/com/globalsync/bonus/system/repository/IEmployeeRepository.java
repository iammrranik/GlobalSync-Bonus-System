package com.globalsync.bonus.system.repository;

import com.globalsync.bonus.system.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository {
    int save(Employee employee);
    Optional<Employee> findById(Integer id);
    List<Employee> findAll();
    List<Employee> findAll(int page, int size);
    int count();
    int update(Employee employee);
    int delete(int id);
}
