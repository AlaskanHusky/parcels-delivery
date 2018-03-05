package by.parcelsdelivery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.parcelsdelivery.dao.PointDao;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

@Service
@Transactional
public class PointServiceImpl implements PointService
{
	private PointDao pointDao;

	public PointServiceImpl()
	{
		System.out.println("PointServiceImpl Constructor");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void save(PointEntity point)
	{
		pointDao.create(point);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(PointEntity point)
	{
		pointDao.update(point);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(int pointId)
	{
		pointDao.delete(pointId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<PointEntity> getAll()
	{
		return pointDao.getAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PointEntity getById(int pointId)
	{
		return pointDao.getById(pointId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PointEntity getByName(String name)
	{
		return pointDao.getByName(name);
	}

	public void setPointDao(PointDao pointDao)
	{
		this.pointDao = pointDao;
	}
}
