package by.parcelsdelivery.dao;

import java.util.List;

import by.parcelsdelivery.entity.ParcelEntity;

public interface ParcelDAO
{
	void createParcel(ParcelEntity parcel);

	void updateParcel(ParcelEntity parcel);

	void deleteParcel(int parcelId);

	List<ParcelEntity> getAllParcels();

	ParcelEntity getParcel(int parcelId);

	ParcelEntity getParcelByUUID(String uuid);
}
