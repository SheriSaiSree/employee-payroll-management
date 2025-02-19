package com.epam.campus.console;

import com.epam.campus.config.AppConfig;
import com.epam.campus.db.Employee;
import com.epam.campus.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Scanner;

public class EmployeeConsoleApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(EmployeeConsoleApp.class);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            EmployeeService employeeService = context.getBean(EmployeeService.class);

            while (true) {
                logger.info("1. Create Employee");
                logger.info("2. Update Employee");
                logger.info("3. Delete Employee");
                logger.info("4. Read Employee");
                logger.info("5. Read All Employees");
                logger.info("6. Generate Payroll by Department");
                logger.info("7. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 7) {
                    logger.info("Exiting application.");
                    break;
                }
                switch (choice) {
                    case 1:
                        createEmployee(employeeService);
                        break;
                    case 2:
                        updateEmployee(employeeService);
                        break;
                    case 3:
                        deleteEmployee(employeeService);
                        break;
                    case 4:
                        readEmployee(employeeService);
                        break;
                    case 5:
                        readAllEmployees(employeeService);
                        break;
                    case 6:
                        generatePayroll(employeeService);
                        break;
                    default:
                        logger.warn("Invalid choice, try again.");
                }

            }
        }
    }

    private static void createEmployee(EmployeeService employeeService) {
        String employeeId;
        do {
            System.out.print("Enter Employee ID: ");
            employeeId = sc.nextLine();
            if (employeeService.employeeExists(employeeId)) {
                logger.warn("Employee ID already exists. Please enter a unique ID.");
            }
        } while (employeeService.employeeExists(employeeId));

        System.out.print("Enter Employee Name: ");
        String employeeName = sc.nextLine();
        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();
        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        double baseSalary = employeeService.getSalaryForDesignation(designation);

        Employee employee = new Employee(employeeId, employeeName, designation, department, baseSalary);
        employeeService.createEmployee(employee);
        logger.info("Employee created successfully");
    }

    private static void updateEmployee(EmployeeService employeeService) {
        System.out.print("Enter Employee ID to update: ");
        String employeeId = sc.nextLine();
        Employee existingEmployee = employeeService.readEmployee(employeeId);

        if (existingEmployee == null) {
            logger.warn("Employee not found.");
            return;
        }

        System.out.print("Enter new Designation (or press Enter to keep current: " + existingEmployee.getDesignation() + "): ");
        String designation = sc.nextLine();
        if (designation.isEmpty())
            designation = existingEmployee.getDesignation();

        System.out.print("Enter new Department (or press Enter to keep current: " + existingEmployee.getDepartment() + "): ");
        String department = sc.nextLine();
        if (department.isEmpty())
            department = existingEmployee.getDepartment();

        double baseSalary = employeeService.getSalaryForDesignation(designation);

        Employee updatedEmployee = new Employee(employeeId, existingEmployee.getEmployeeName(), designation, department, baseSalary);
        boolean success = employeeService.updateEmployee(employeeId, updatedEmployee);

        if (success) {
            logger.info("Employee updated successfully.");
        } else {
            logger.warn("Error updating employee.");
        }
    }

    private static void deleteEmployee(EmployeeService employeeService) {
        System.out.print("Enter Employee ID to delete: ");
        String employeeId = sc.nextLine();
        boolean success = employeeService.deleteEmployee(employeeId);

        if (success) {
            logger.info("Employee deleted successfully.");
        } else {
            logger.warn("Employee not found.");
        }
    }

    private static void readEmployee(EmployeeService employeeService) {
        System.out.print("Enter Employee ID to view details: ");
        String employeeId = sc.nextLine();
        Employee employee = employeeService.readEmployee(employeeId);

        if (employee == null) {
            logger.warn("Employee not found.");
        } else {
            logger.info("Employee Details: " + employee);
        }
    }

    private static void readAllEmployees(EmployeeService employeeService) {
        Map<String, Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            logger.warn("No employees found.");
        } else {
            logger.info("All Employees:");
            for (Employee employee : employees.values()) {
                logger.info(employee.toString());
            }
        }
    }

    private static void generatePayroll(EmployeeService employeeService) {
        System.out.print("Enter Department: ");
        String department = sc.nextLine();
        double payroll = employeeService.generatePayrollByDepartment(department);
        logger.info("Total payroll for {}: {}", department, payroll);
    }
}
