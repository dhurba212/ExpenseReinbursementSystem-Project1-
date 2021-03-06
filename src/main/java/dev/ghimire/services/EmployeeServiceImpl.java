package dev.ghimire.services;

import dev.ghimire.daos.EmployeeDAO;
import dev.ghimire.entities.Employee;

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
}
