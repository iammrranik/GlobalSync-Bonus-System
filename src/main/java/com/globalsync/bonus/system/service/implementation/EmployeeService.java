package com.globalsync.bonus.system.service.implementation;

import com.globalsync.bonus.system.domain.Employee;
import com.globalsync.bonus.system.repository.IEmployeeRepository;
import com.globalsync.bonus.system.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployees(int page, int size) {
        return employeeRepository.findAll(page, size);
    }

    @Override
    public int getEmployeeCount() {
        return employeeRepository.count();
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public int removeEmployee(int id) {
        return employeeRepository.delete(id);
    }
}

