package com.epam.campus.service;

import com.epam.campus.db.EmployeeRepository;
import com.epam.campus.db.Employee;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(Employee employee) {
        employeeRepository.createEmployee(employee);
    }

    public Employee readEmployee(String employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }

    public boolean updateEmployee(String employeeId, Employee updatedEmployee) {
        return employeeRepository.updateEmployee(employeeId, updatedEmployee);
    }

    public boolean deleteEmployee(String employeeId) {
        return employeeRepository.deleteEmployee(employeeId);
    }

    public double generatePayrollByDepartment(String department) {
        return employeeRepository.getEmployeesByDepartment(department)
                .stream()
                .mapToDouble(Employee::getBaseSalary)
                .sum();
    }

    public double getSalaryForDesignation(String designation) {
        return employeeRepository.getSalaryByDesignation(designation);
    }

    public Map<String, Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public boolean employeeExists(String employeeId) {
        return employeeRepository.employeeExists(employeeId);
    }
}
