package dev.ghimire.utils;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HibernateTest {

    @Test
    void get_session_factory_test_1()
    {
        SessionFactory sess = HibernateUtil.getSessionFactory();

        Assertions.assertNotNull(sess);
    }
}
