package net.javaguides.springbootbackend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // create emplyoee rest api
    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    // get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // get employee by id rest api
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
       return employeeService.findEmployeeById(id);
    }


    @DeleteMapping
    public int deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }


    // update employee rest api
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        return employeeService.updateEmployeeById(id, employee);


        
    }

    
}
