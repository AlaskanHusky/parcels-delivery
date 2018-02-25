package by.parcelsdelivery.util;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUtil {

    private SessionFactory sessionFactory;

    public HibernateUtil() {
        System.out.println("HibernateUtil");
    }

    public <T> void create(final T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public <T> void update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    private <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(int id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM " + entityClass.getName()).list();
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(int id, Class<T> entityClass) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    public <T> T fetchByAttribute(String field, String value, Class<T> entityClass) {
        return (T) sessionFactory.getCurrentSession().createQuery("FROM " + entityClass.getName() + " E " + "WHERE E." + field + " = " + "'" + value + "'").getSingleResult();
    }

    public <T> List fetchValuesByAttribute(String attrName, Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" SELECT " + attrName + " FROM " + entityClass.getName()).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
