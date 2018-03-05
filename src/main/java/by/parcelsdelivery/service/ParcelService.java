package by.parcelsdelivery.service;

import java.util.List;

import by.parcelsdelivery.entity.ParcelEntity;

public interface ParcelService
{
	void createParcel(ParcelEntity parcel);

	void updateParcel(ParcelEntity parcel);

	void deleteParcel(int parcelId);

	List<ParcelEntity> getAllParcels();

	ParcelEntity getParcel(int parcelId);

	ParcelEntity getParcelByUUID(String uuid);
}
