package com.epam.campus.db;

import java.util.*;

public class EmployeeRepository {
    Scanner scanner =new Scanner(System.in);
    private final Map<String, Employee> employeeDatabase = new HashMap<>();
    private final Map<String, Double> salaryMap = new HashMap<>();

    public EmployeeRepository() {
        salaryMap.put("Manager", 70000.0);
        salaryMap.put("Developer", 50000.0);
        salaryMap.put("Tester", 40000.0);
        salaryMap.put("HR", 45000.0);
    }

    public void createEmployee(Employee employee) {
        employeeDatabase.put(employee.getEmployeeId(), employee);
    }

    public Employee getEmployee(String employeeId) {
        return employeeDatabase.get(employeeId);
    }

    public boolean updateEmployee(String employeeId, Employee updatedEmployee) {
        if (employeeDatabase.containsKey(employeeId)) {
            employeeDatabase.put(employeeId, updatedEmployee);
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(String employeeId) {
        return employeeDatabase.remove(employeeId) != null;
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        List<Employee> departmentEmployees = new ArrayList<>();
        for (Employee emp : employeeDatabase.values()) {
            if (emp.getDepartment().equalsIgnoreCase(department)) {
                departmentEmployees.add(emp);
            }
        }
        return departmentEmployees;
    }

    public Map<String, Employee> getAllEmployees() {
        return employeeDatabase;
    }

    public double getSalaryByDesignation(String designation) {
        if (!salaryMap.containsKey(designation)) {
            System.out.print("Salary for " + designation + " is not defined. Enter salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();
            salaryMap.put(designation, salary);
        }
        return salaryMap.get(designation);
    }

    public boolean employeeExists(String employeeId) {
        return employeeDatabase.containsKey(employeeId);
    }
}
