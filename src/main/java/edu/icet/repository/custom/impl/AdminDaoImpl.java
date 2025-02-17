package edu.icet.repository.custom.impl;

import edu.icet.entity.AdminEntity;
import edu.icet.repository.custom.AdminDao;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;

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
}
