package by.parcelsdelivery.service;

import by.parcelsdelivery.entity.ParcelEntity;

import java.util.List;

public interface ParcelService {

    void createParcel(ParcelEntity parcel);
    void updateParcel(ParcelEntity parcel);
    void deleteParcel(int parcelId);
    List<ParcelEntity> getAllParcels();
    ParcelEntity getParcel(int parcelId);

}
