package net.javaguides.springbootbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.javaguides.springbootbackend.dao.EmployeeDao;
import net.javaguides.springbootbackend.model.Employee;

@Service
public class EmployeeService {
    
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(@Qualifier("sqlite") EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int addEmployee(Employee employee){
        return employeeDao.insertEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.selectAllEmployees();
    }

    public int deleteAllEmployees(){
        return employeeDao.deleteAllEmployees();
    }

    public Employee findEmployeeById(String id) {
        return employeeDao.getEmployeeById(id);
    }

    public Employee updateEmployeeById(String id, Employee employee) {
        return employeeDao.updateEmployeeById(id, employee);
    }

    public int deleteEmployeeById(String id) {
        return employeeDao.deleteEmployeeById(id);
    }
}
