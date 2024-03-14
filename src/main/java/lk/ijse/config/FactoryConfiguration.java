package lk.ijse.config;

import lk.ijse.entity.Branches;
import lk.ijse.entity.Member;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class)
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Branches.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getFactoryConfiguration() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
