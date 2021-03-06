package dev.ghimire.daos;

import dev.ghimire.entities.Employee;
import dev.ghimire.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployeeDaoHibernateImpl implements EmployeeDAO{
    Logger logger = Logger.getLogger(EmployeeDaoHibernateImpl.class);

    @Override
    public Employee createEmployee(Employee employee) {
        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            session.close();
            System.out.println(employee);
            return employee;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            logger.error("Wasn't able to register employee");
            return null;
        }


    }

    @Override
    public Employee getEmployeeById(int id) {
        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            Employee employee = session.get(Employee.class,id);
            session.close();
            return employee;
        }
        catch(HibernateException he)
        {
            he.printStackTrace();
            logger.error("error while retrieving employee with employee id "+id);
            return null;

        }


    }
}
