package by.parcelsdelivery.dao.impl;

import by.parcelsdelivery.dao.ParcelDAO;
import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.util.HibernateUtil;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParcelDAOImpl implements ParcelDAO {

    private HibernateUtil hibernateUtil;

    public ParcelDAOImpl() {
        System.out.println("ParcelDAOImpl Constructor");
    }

    @Override
    public int createParcel(ParcelEntity parcel) {
        return (int) hibernateUtil.create(parcel);
    }

    @Override
    public ParcelEntity updateParcel(ParcelEntity parcel) {
        return hibernateUtil.update(parcel);
    }

    @Override
    public void deleteParcel(int parcelId) {
        ParcelEntity parcel = new ParcelEntity();
        parcel.setId(parcelId);
        hibernateUtil.delete(parcel);
    }

    @Override
    public List<ParcelEntity> getAllParcels() {
        return hibernateUtil.fetchAll(ParcelEntity.class);
    }

    @Override
    public ParcelEntity getParcel(int parcelId) {
        return hibernateUtil.fetchById(parcelId, ParcelEntity.class);
    }

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }
}
