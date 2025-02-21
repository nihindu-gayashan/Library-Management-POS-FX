package edu.icet.repository.custom.impl;

import edu.icet.entity.AdminEntity;
import edu.icet.repository.custom.AdminDao;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Optional<AdminEntity> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public String signIn(String username) {
        try (
                Session session = HibernateUtil.getSession()) {
            String hql = "SELECT e.password FROM AdminEntity e WHERE e.username = :username";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("username", username);
            String password=query.uniqueResult();
            return password; // No need for transaction or manual closing
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
