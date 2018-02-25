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
        System.out.println("PointDAOImpl Constructor");
    }

    @Override
    public void createPoint(PointEntity point) {
        hibernateUtil.create(point);
    }

    @Override
    public void updatePoint(PointEntity point) {
        hibernateUtil.update(point);
    }

    @Override
    public void deletePoint(int pointId) {
        hibernateUtil.delete(pointId, PointEntity.class);
    }

    @Override
    public List<PointEntity> getAllPoints() {
        return hibernateUtil.fetchAll(PointEntity.class);
    }

    @Override
    public PointEntity getPoint(int pointId) {
        return hibernateUtil.fetchById(pointId, PointEntity.class);
    }

    @Override
    public PointEntity getPointByName(String name) {
        return  hibernateUtil.fetchByAttribute("name", name, PointEntity.class);
    }

    @Override
    public List getPointsNames(){
        return hibernateUtil.fetchValuesByAttribute("name", PointEntity.class);
    }

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }
}
