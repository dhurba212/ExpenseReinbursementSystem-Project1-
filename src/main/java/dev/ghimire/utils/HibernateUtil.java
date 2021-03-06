package dev.ghimire.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sf;
    public static SessionFactory getSessionFactory()
    {
        if(sf==null)
        {
            Configuration config = new Configuration();
            sf = config.configure().buildSessionFactory();

        }
        return sf;
    }



}
