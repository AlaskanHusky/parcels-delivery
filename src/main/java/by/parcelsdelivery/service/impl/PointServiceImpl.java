package by.parcelsdelivery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.parcelsdelivery.dao.PointDAO;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

@Service
@Transactional
public class PointServiceImpl implements PointService
{
	private PointDAO pointDAO;

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
		pointDAO.create(point);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(PointEntity point)
	{
		pointDAO.update(point);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(int pointId)
	{
		pointDAO.delete(pointId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<PointEntity> getAll()
	{
		return pointDAO.getAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PointEntity getById(int pointId)
	{
		return pointDAO.getById(pointId);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PointEntity getByName(String name)
	{
		return pointDAO.getByName(name);
	}

	public void setPointDAO(PointDAO pointDAO)
	{
		this.pointDAO = pointDAO;
	}
}
