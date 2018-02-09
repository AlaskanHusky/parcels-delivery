package by.parcelsdelivery.service;

import by.parcelsdelivery.entity.PointEntity;

import java.util.List;

public interface PointService {

    public int createPoint(PointEntity point);
    public PointEntity updatePoint(PointEntity point);
    public void deletePoint(int pointId);
    public List<PointEntity> getAllPoints();
    public PointEntity getPoint(int pointId);

}
