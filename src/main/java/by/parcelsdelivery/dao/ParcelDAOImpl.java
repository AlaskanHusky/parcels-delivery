package by.parcelsdelivery.dao;

import by.parcelsdelivery.model.Parcel;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParcelDAOImpl implements ParcelDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ParcelDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addParcel(Parcel prcl) {
        sessionFactory.getCurrentSession().saveOrUpdate(prcl);
    }
    @SuppressWarnings("unchecked")
    public List<Parcel> getAllParcels() {
        return sessionFactory.getCurrentSession().createQuery("from Parcel").list();
    }


    @Override
    public void deleteParcel(int parcelId) {
        Parcel parcel = sessionFactory.getCurrentSession().load(Parcel.class, parcelId);
        if (null != parcel) {
            this.sessionFactory.getCurrentSession().delete(parcel);
        }
    }

    public Parcel getParcel(int parcelId) {
        return sessionFactory.getCurrentSession().get(Parcel.class, parcelId);
    }

    @Override
    public Parcel updateParcel(Parcel prcl) {
        sessionFactory.getCurrentSession().update(prcl);
        return prcl;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
