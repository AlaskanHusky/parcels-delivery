package by.parcelsdelivery.service;

import java.util.List;

import by.parcelsdelivery.entity.PointEntity;

public interface PointService
{
	void createPoint(PointEntity point);

	void updatePoint(PointEntity point);

	void deletePoint(int pointId);

	List<PointEntity> getAllPoints();

	PointEntity getPoint(int pointId);

	PointEntity getPointByName(String name);

	List getPointsNames();
}
