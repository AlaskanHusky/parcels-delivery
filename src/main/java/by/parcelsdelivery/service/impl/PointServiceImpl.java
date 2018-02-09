package by.parcelsdelivery.service.impl;

import by.parcelsdelivery.dao.PointDAO;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.service.PointService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PointServiceImpl implements PointService {

    private PointDAO pointDAO;

    public PointServiceImpl() {
        System.out.println("PointServiceImpl Constructor");
    }

    @Override
    public int createPoint(PointEntity point) {
        return pointDAO.createPoint(point);
    }

    @Override
    public PointEntity updatePoint(PointEntity point) {
        return pointDAO.updatePoint(point);
    }

    @Override
    public void deletePoint(int pointId) {
        pointDAO.deletePoint(pointId);
    }

    @Override
    public List<PointEntity> getAllPoints() {
        return pointDAO.getAllPoints();
    }

    @Override
    public PointEntity getPoint(int pointId) {
        return pointDAO.getPoint(pointId);
    }

    public void setPointDAO(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

}
