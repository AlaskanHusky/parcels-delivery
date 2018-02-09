package by.parcelsdelivery.dao.impl;

import by.parcelsdelivery.dao.PointDAO;
import by.parcelsdelivery.entity.PointEntity;
import by.parcelsdelivery.util.HibernateUtil;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointDAOImpl implements PointDAO {

    private HibernateUtil hibernateUtil;

    public PointDAOImpl() {
        System.out.println("PointDAOImpl");
    }

    @Override
    public int createPoint(PointEntity point) {
        return (int) hibernateUtil.create(point);
    }

    @Override
    public PointEntity updatePoint(PointEntity point) {
        return hibernateUtil.update(point);
    }

    @Override
    public void deletePoint(int pointId) {
        PointEntity point = new PointEntity();
        point.setId(pointId);
        hibernateUtil.delete(point);
    }

    @Override
    public List<PointEntity> getAllPoints() {
        return hibernateUtil.fetchAll(PointEntity.class);
    }

    @Override
    public PointEntity getPoint(int pointId) {
        return hibernateUtil.fetchById(pointId, PointEntity.class);
    }

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }
}
