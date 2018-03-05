package by.parcelsdelivery.service.impl;

import by.parcelsdelivery.dao.PointDAO;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PointServiceImpl implements PointService
{
	private PointDAO pointDAO;

	public PointServiceImpl()
	{
		System.out.println("PointServiceImpl Constructor");
	}

	@Override
	public void createPoint(PointEntity point)
	{
		pointDAO.createPoint(point);
	}

	@Override
	public void updatePoint(PointEntity point)
	{
		pointDAO.updatePoint(point);
	}

	@Override
	public void deletePoint(int pointId)
	{
		pointDAO.deletePoint(pointId);
	}

	@Override
	public List<PointEntity> getAllPoints()
	{
		return pointDAO.getAllPoints();
	}

	@Override
	public PointEntity getPoint(int pointId)
	{
		return pointDAO.getPoint(pointId);
	}

	@Override
	public PointEntity getPointByName(String name)
	{
		return pointDAO.getPointByName(name);
	}

	@Override
	public List getPointsNames()
	{
		return pointDAO.getPointsNames();
	}

	public void setPointDAO(PointDAO pointDAO)
	{
		this.pointDAO = pointDAO;
	}
}
