package org.example.DAO;

import org.apache.catalina.User;
import org.example.Domain.Product;
import org.example.Domain.Users;
import org.example.Repository.Repository;
import org.example.Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
//import javax.persistence.Query;
import java.util.List;

public class UsersDAO implements Repository<Users,Integer> {
    private static final int PRICE_LIMIT = 50000000;
    private static final int DEFAULT_PRICE = 15000000;
    private static final Session session;
    static {
        session = HibernateUtils.getFactory().openSession();
    }
    @Override
    public Users add(Users e) {
        try {
            session.beginTransaction();
            session.save(e);
            System.out.println("Phone " + e.getFullName() + " added");
            session.getTransaction().commit();
            return e;
        } catch (Exception item) {
            item.printStackTrace();
        }
        return null;
    }

    @Override
    public Users get(Integer id) {
        return null;
    }

    @Override
    public List<Users> getAll() {
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Users ");
            List<Users> manufactures = query.getResultList();
            session.getTransaction().commit();
            return manufactures;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(Users e) {
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public boolean update(Users e) {
        return false;
    }
    public Users checkUsers(String username, String password) {
        try {
            session.beginTransaction();
            Query<Users> query = session.createQuery("from Users where fullName = :username and password = :password", Users.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            Users user = query.uniqueResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Users register(int id ,String fullName, String email, String password) {
        try {
            session.beginTransaction();

            // Kiểm tra xem username đã tồn tại hay chưa
            Query<Long> countQuery = session.createQuery("select count(*) from Users where fullName = :username", Long.class);
            countQuery.setParameter("username", fullName);
            long count = countQuery.uniqueResult();

            if (count > 0) {
                System.out.println("Username " + fullName + " already exists. Registration failed.");
                return null;
            }

            Users user = new Users(id,fullName,email, password);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User " + fullName + " registered successfully");
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
