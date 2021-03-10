package dev.ghimire.daos;

import dev.ghimire.entities.Employee;

import java.util.Set;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);
    Employee getEmployeeById(int id);
    Set<Employee>  getAllEmployee();

}
