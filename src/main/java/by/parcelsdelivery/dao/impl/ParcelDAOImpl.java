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
    public void createParcel(ParcelEntity parcel) {
        hibernateUtil.create(parcel);
    }

    @Override
    public void updateParcel(ParcelEntity parcel) {
        hibernateUtil.update(parcel);
    }

    @Override
    public void deleteParcel(int id) {
        hibernateUtil.delete(id, ParcelEntity.class);
    }

    @Override
    public List<ParcelEntity> getAllParcels() {
        return hibernateUtil.fetchAll(ParcelEntity.class);
    }

    @Override
    public ParcelEntity getParcel(int parcelId) {
        return hibernateUtil.fetchById(parcelId, ParcelEntity.class);
    }

    @Override
    public ParcelEntity getParcelByUUID(String uuid) {
        return  hibernateUtil.fetchByAttribute("uuid", uuid, ParcelEntity.class);
    }

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }
}
