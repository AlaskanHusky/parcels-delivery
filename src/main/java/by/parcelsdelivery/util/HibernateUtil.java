package by.parcelsdelivery.util;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateUtil {

    private SessionFactory sessionFactory;

    public HibernateUtil() {
        System.out.println("HibernateUtil");
    }

    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM " + entityClass.getName()).list();
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
