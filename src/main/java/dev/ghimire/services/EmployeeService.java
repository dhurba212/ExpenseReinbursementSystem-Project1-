package dev.ghimire.services;

import dev.ghimire.entities.Employee;

public interface EmployeeService {
    Employee getEmployeeById(int id);
    Employee getEmployeeByUsernamePassword(String username,String password);


}
