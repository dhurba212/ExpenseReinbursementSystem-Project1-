package dev.ghimire.daos;

import dev.ghimire.entities.Employee;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);
    Employee getEmployeeById(int id);
}
