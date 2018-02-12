package by.parcelsdelivery.dao;

import java.util.List;

import by.parcelsdelivery.entity.PointEntity;

public interface PointDAO {

    void createPoint(PointEntity point);
    void updatePoint(PointEntity point);
    void deletePoint(int pointId);
    List<PointEntity> getAllPoints();
    PointEntity getPoint(int pointId);

}
