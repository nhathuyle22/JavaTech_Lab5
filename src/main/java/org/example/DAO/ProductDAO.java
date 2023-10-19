package org.example.DAO;

import org.hibernate.Session;
import org.example.Domain.Product;
import org.example.Repository.Repository;
import org.example.Utils.HibernateUtils;

import javax.persistence.Query;
import java.util.List;

public class ProductDAO implements Repository<Product,Integer> {
    private static final double PRICE_LIMIT = 50000000;
    private static final double DEFAULT_PRICE = 15000000;
    private static final Session session;
    static {
        session = HibernateUtils.getFactory().openSession();
    }
    @Override
    public Product add(Product e) {
        try {
            session.beginTransaction();
            session.save(e);
            System.out.println("Product " + e.getName() + " added");
            session.getTransaction().commit();
            return e;
        } catch (Exception item) {
            item.printStackTrace();
        }
        return null;
    }

    @Override
    public Product get(Integer id) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Product where id = :id");
            query.setParameter("id", id);
            Product manufacture = (Product) query.getSingleResult();
            session.getTransaction().commit();
            return manufacture;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Product");
            List<Product> manufactures = query.getResultList();
            session.getTransaction().commit();
            return manufactures;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(Product e) {
        try {
            session.beginTransaction();
            session.delete(e);
            session.getTransaction().commit();
            System.out.println("Product '" + e.getName() + "' was deleted");
            return true;
        } catch (Exception item) {
            item.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public boolean update(Product item) {
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
