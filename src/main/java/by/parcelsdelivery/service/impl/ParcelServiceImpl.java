package by.parcelsdelivery.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.parcelsdelivery.dao.ParcelDao;
import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService
{
	private ParcelDao parcelDao;

	public ParcelServiceImpl()
	{
		System.out.println("ParcelServiceImpl Constructor");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void create(ParcelEntity parcel)
	{
		parcel.setUuid(UUID.randomUUID().toString());
		parcelDao.save(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void save(ParcelEntity parcel)
	{
		parcel.setStatus(ParcelEntity.STATUS_TRANSIT);
		parcelDao.save(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(ParcelEntity parcel)
	{
		parcelDao.update(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(int parcelId)
	{
		parcelDao.delete(parcelId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<ParcelEntity> getAll()
	{
		return parcelDao.getAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ParcelEntity getById(int parcelId)
	{
		return parcelDao.getById(parcelId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ParcelEntity getByUUID(String uuid)
	{
		return parcelDao.getByUUID(uuid);
	}

	public void setParcelDao(ParcelDao parcelDao)
	{
		this.parcelDao = parcelDao;
	}
}
