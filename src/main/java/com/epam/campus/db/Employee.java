package com.epam.campus.db;

public class Employee {
    private final String employeeId;
    private final String employeeName;
    private final String designation;
    private final String department;
    private final double baseSalary;

    public Employee(String employeeId, String employeeName, String designation, String department, double baseSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
        this.department = department;
        this.baseSalary = baseSalary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return "Employee{ ID='" + employeeId + "', Name='" + employeeName +
                "', Designation='" + designation + "', Department='" + department +
                "', Base Salary=" + baseSalary + " }";
    }
}
