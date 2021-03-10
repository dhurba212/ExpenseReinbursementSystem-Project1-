package dev.ghimire.services;

import dev.ghimire.daos.EmployeeDAO;
import dev.ghimire.entities.Employee;

import java.util.Set;

public class EmployeeServiceImpl implements EmployeeService{

    private static EmployeeDAO employeeDao;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO)
    {
        this.employeeDao = employeeDAO;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeDao.getEmployeeById(id);
        return employee;
    }

    @Override
    public Employee getEmployeeByUsernamePassword(String username, String password) {
        Set<Employee> employees = employeeDao.getAllEmployee();
        Employee employee = null;
        for(Employee e:employees)
        {
            if(e.getUsername().equals(username) && e.getPassword().equals(password))
            {
                employee = e;
            }
        }
        return employee;
    }
}
