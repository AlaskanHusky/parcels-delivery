package by.parcelsdelivery.dao;

import java.util.List;

import by.parcelsdelivery.entity.ParcelEntity;

public interface ParcelDAO {

    public int createParcel(ParcelEntity parcel);
    public ParcelEntity updateParcel(ParcelEntity parcel);
    public void deleteParcel(int parcelId);
    public List<ParcelEntity> getAllParcels();
    public ParcelEntity getParcel(int parcelId);

}
