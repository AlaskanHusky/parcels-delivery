package by.parcelsdelivery.service;

import by.parcelsdelivery.entity.PointEntity;

import java.util.List;

public interface PointService {

    void createPoint(PointEntity point);
    void updatePoint(PointEntity point);
    void deletePoint(int pointId);
    List<PointEntity> getAllPoints();
    PointEntity getPoint(int pointId);
    List getPointsNames();

}
