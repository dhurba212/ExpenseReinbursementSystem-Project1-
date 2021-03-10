package dev.ghimire.dao;

import dev.ghimire.daos.EmployeeDAO;
import dev.ghimire.daos.EmployeeDaoHibernateImpl;
import dev.ghimire.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    EmployeeDAO employeeDAO = new EmployeeDaoHibernateImpl();
    private static Employee testEmployee;
    @Test
    @Order(1)
    void create_employee_test_1()
    {
        Employee e1 = new Employee(0,"Sergio","Busquet","sergio","busquet");
        Employee registeredEmployee = employeeDAO.createEmployee(e1);

        testEmployee=registeredEmployee;
        Assertions.assertNotNull(registeredEmployee);
        Assertions.assertEquals(e1.getFirstName(),registeredEmployee.getFirstName());

    }
    @Test
    @Order(2)
    void get_employee_by_id_test_1()
    {
        int id = testEmployee.getEmployeeId();
        Employee employee = employeeDAO.getEmployeeById(id);
        System.out.println(employee);
        Assertions.assertEquals(testEmployee.getFirstName(),employee.getFirstName());
    }

    @Test
    @Order(3)
    void get_employee_by_id_test_2()
    {
        Employee employee = employeeDAO.getEmployeeById(1);
        System.out.println(employee);
        Assertions.assertEquals(1,employee.getEmployeeId());
    }
}
