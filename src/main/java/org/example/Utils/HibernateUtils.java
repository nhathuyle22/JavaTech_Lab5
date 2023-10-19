package org.example.Utils;

import lombok.Getter;
import org.example.Domain.Product;
import org.example.Domain.Users;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    @Getter
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();


        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Users.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }

}