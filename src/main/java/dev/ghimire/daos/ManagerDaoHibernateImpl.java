package dev.ghimire.daos;

import dev.ghimire.entities.Manager;
import dev.ghimire.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.Set;

public class ManagerDaoHibernateImpl implements ManagerDAO {
    Logger logger = Logger.getLogger(ManagerDaoHibernateImpl.class);
    @Override
    public Manager getManagerById(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Manager manager = session.get(Manager.class,id);
        session.close();

        return manager;
    }

    @Override
    public Set<Manager> getAllManager() {
        try
        {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            Criteria criteria = session.createCriteria(Manager.class);
            Set<Manager> managers = new HashSet<>(criteria.list());
            session.close();
            logger.info("was able to get all managers");
            return managers;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            logger.error("Wasn't able to get all the managers");
            Set<Manager> allManager = new HashSet<>();
            return allManager;
        }

    }
}
