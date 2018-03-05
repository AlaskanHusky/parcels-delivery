package by.parcelsdelivery.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.parcelsdelivery.dao.ParcelDAO;
import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService
{
	private ParcelDAO parcelDAO;

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
		parcelDAO.save(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void save(ParcelEntity parcel)
	{
		parcel.setStatus(ParcelEntity.STATUS_TRANSIT);
		parcelDAO.save(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(ParcelEntity parcel)
	{
		parcelDAO.update(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(int parcelId)
	{
		parcelDAO.delete(parcelId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<ParcelEntity> getAll()
	{
		return parcelDAO.getAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ParcelEntity getById(int parcelId)
	{
		return parcelDAO.getById(parcelId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ParcelEntity getByUUID(String uuid)
	{
		return parcelDAO.getByUUID(uuid);
	}

	public void setParcelDAO(ParcelDAO parcelDAO)
	{
		this.parcelDAO = parcelDAO;
	}
}
