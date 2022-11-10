package net.javaguides.springbootbackend.dao;

import java.util.List;
import java.util.UUID;

import net.javaguides.springbootbackend.model.Employee;

public interface EmployeeDao {
    
    int insertEmployee(UUID id, Employee employee);

    default int insertEmployee(Employee employee) {
        UUID id = UUID.randomUUID();
        return insertEmployee(id, employee);
    }

    List<Employee> selectAllEmployees();

    int deleteAllEmployees();

    Employee getEmployeeById(String id);

    Employee updateEmployeeById(String id, Employee employee);

    int deleteEmployeeById(String id);
    
}
