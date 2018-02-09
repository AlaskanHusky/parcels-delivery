package by.parcelsdelivery.dao;

import java.util.List;

import by.parcelsdelivery.entity.PointEntity;

public interface PointDAO {

    public int createPoint(PointEntity point);
    public PointEntity updatePoint(PointEntity point);
    public void deletePoint(int pointId);
    public List<PointEntity> getAllPoints();
    public PointEntity getPoint(int pointId);

}
