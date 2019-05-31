package com.minsk.bh.dao;

import com.minsk.bh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User login(User user) {
        Session session = this.sessionFactory.openSession();
        User result = null;
        Transaction tx = session.beginTransaction();
        List<User> users = session.createQuery("from User where login = :login")
                .setParameter("login", user.getLogin())
                .list();
        if (users != null && users.size() > 0) {
            result = users.get(0);
        }
        tx.commit();
        session.close();
        return result;
    }

    @Override
    public void registration(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }


    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        List<User> userList = session.createQuery("from User").list();
        session.close();
        return userList;
    }
}
