package by.parcelsdelivery.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import by.parcelsdelivery.dao.ParcelDAO;
import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.util.HibernateUtil;

@Repository
public class ParcelDAOImpl implements ParcelDAO
{
	private HibernateUtil hibernateUtil;

	public ParcelDAOImpl()
	{
		System.out.println("ParcelDAOImpl Constructor");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void save(ParcelEntity parcel)
	{
		hibernateUtil.create(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(ParcelEntity parcel)
	{
		hibernateUtil.update(parcel);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(int id)
	{
		hibernateUtil.delete(id, ParcelEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<ParcelEntity> getAll()
	{
		return hibernateUtil.fetchAll(ParcelEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ParcelEntity getById(int parcelId)
	{
		return hibernateUtil.fetchById(parcelId, ParcelEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ParcelEntity getByUUID(String uuid)
	{
		return hibernateUtil.fetchByAttribute("uuid", uuid, ParcelEntity.class);
	}

	public void setHibernateUtil(HibernateUtil hibernateUtil)
	{
		this.hibernateUtil = hibernateUtil;
	}
}
