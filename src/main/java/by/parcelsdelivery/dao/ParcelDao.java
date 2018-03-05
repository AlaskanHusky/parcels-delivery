package by.parcelsdelivery.dao;

import java.util.List;

import by.parcelsdelivery.entity.ParcelEntity;

public interface ParcelDao
{
	/**
	 *
	 * @param parcel
	 */
	void save(ParcelEntity parcel);

	/**
	 *
	 * @param parcel
	 */
	void update(ParcelEntity parcel);

	/**
	 *
	 * @param parcelId
	 */
	void delete(int parcelId);

	/**
	 *
	 * @return
	 */
	List<ParcelEntity> getAll();

	/**
	 *
	 * @param parcelId
	 * @return
	 */
	ParcelEntity getById(int parcelId);

	/**
	 *
	 * @param uuid
	 * @return
	 */
	ParcelEntity getByUUID(String uuid);
}
