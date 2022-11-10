package net.javaguides.springbootbackend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;



@Repository("sqlite")
public class EmployeeDataAccessService implements EmployeeDao{


    private Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/java/net/javaguides/springbootbackend/db/employees.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    @Override
    public int insertEmployee(UUID id, Employee employee) {
        String suuid = id.toString();
        String sql = "INSERT INTO employee(employee_id, first_name, last_name, email) VALUES(?,?,?,?)";

        try(Connection connection = this.connect();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, suuid);
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getLastName());
            pstmt.setString(4, employee.getEmailId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("insert employyee fn error: " + e.getMessage());
        }
        
        return 1;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        String query = "SELECT * FROM employee";
        ArrayList<Employee> employeeData = new ArrayList<Employee>();

        try(Connection connection = this.connect()){
            Statement stmt = connection.createStatement(); 
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String employee_id = rs.getString("employee_id");
                UUID employee_id_uuid = UUID.fromString(employee_id);
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");

                employeeData.add(new Employee(employee_id_uuid, first_name, last_name, email));
            }
            } catch (SQLException e) {
                e.getMessage();
            }
        
        return employeeData;
    }

    @Override
    public int deleteAllEmployees() {
        String query = "DELETE FROM employee";

        try(Connection connection = this.connect()) {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);

        } catch(SQLException e) {
            e.getMessage();
        }
        return 1;
    }

    public static Employee findEmployeeById(Collection<Employee> employees, String id) {
        return employees.stream().filter(employee -> id.equals(employee.getId().toString())).findFirst().orElse(null);
    }

    @Override
    public Employee getEmployeeById(String id) {

        List<Employee> employeeData = new ArrayList<Employee>();
        employeeData = this.selectAllEmployees();
        Employee employee = findEmployeeById(employeeData, id);

        return employee;
    }


    @Override
    public Employee updateEmployeeById(String id, Employee employee) {
        String query = "UPDATE employee SET first_name = ?, last_name = ?, email = ? WHERE employee_id = ?";

        try(Connection connection = this.connect();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getEmailId());
            pstmt.setString(4, id);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("update employyee fn error: " + e.getMessage());
        }
        
        return employee;
    }


    @Override
    public int deleteEmployeeById(String id) {
       String query = "DELETE FROM employee WHERE employee_id = ?";
         try(Connection connection = this.connect();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();

        } catch(SQLException e) {
            e.getMessage();
        }

        return 1;
    }

}
