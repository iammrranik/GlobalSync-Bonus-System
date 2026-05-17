package com.globalsync.bonus.system.api;

import com.globalsync.bonus.system.domain.Employee;
import com.globalsync.bonus.system.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApi {

    private final IEmployeeService employeeService;

    public EmployeeApi(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        int result = employeeService.addEmployee(employee);
        if (result > 0) {
            return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create employee", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return new ResponseEntity<>(employeeService.getAllEmployees(page, size), HttpStatus.OK);
        }
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEmployeeCount() {
        return new ResponseEntity<>(employeeService.getEmployeeCount(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        int result = employeeService.updateEmployee(employee);
        if (result > 0) {
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update employee", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        int result = employeeService.removeEmployee(id);
        if (result > 0) {
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee not found or failed to delete", HttpStatus.NOT_FOUND);
    }
}