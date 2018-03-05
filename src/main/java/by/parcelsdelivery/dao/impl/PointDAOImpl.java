package by.parcelsdelivery.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import by.parcelsdelivery.dao.PointDAO;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.util.HibernateUtil;

@Repository
public class PointDAOImpl implements PointDAO
{
	private HibernateUtil hibernateUtil;

	public PointDAOImpl()
	{
		System.out.println("PointDAOImpl Constructor");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void create(PointEntity point)
	{
		hibernateUtil.create(point);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(PointEntity point)
	{
		hibernateUtil.update(point);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(int pointId)
	{
		hibernateUtil.delete(pointId, PointEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<PointEntity> getAll()
	{
		return hibernateUtil.fetchAll(PointEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PointEntity getById(int pointId)
	{
		return hibernateUtil.fetchById(pointId, PointEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PointEntity getByName(String name)
	{
		return hibernateUtil.fetchByAttribute("name", name, PointEntity.class);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List getNames()
	{
		return hibernateUtil.fetchValuesByAttribute("name", PointEntity.class);
	}

	public void setHibernateUtil(HibernateUtil hibernateUtil)
	{
		this.hibernateUtil = hibernateUtil;
	}
}
