package by.parcelsdelivery.service;

import by.parcelsdelivery.entity.ParcelEntity;

import java.util.List;

public interface ParcelService {

    public int createParcel(ParcelEntity parcel);
    public ParcelEntity updateParcel(ParcelEntity parcel);
    public void deleteParcel(int parcelId);
    public List<ParcelEntity> getAllParcels();
    public ParcelEntity getParcel(int parcelId);

}
