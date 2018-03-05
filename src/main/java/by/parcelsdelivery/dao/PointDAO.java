package by.parcelsdelivery.dao;

import java.util.List;

import by.parcelsdelivery.entity.PointEntity;

public interface PointDAO
{
	/**
	 *
	 * @param point
	 */
	void create(PointEntity point);

	/**
	 *
	 * @param point
	 */
	void update(PointEntity point);

	/**
	 *
	 * @param pointId
	 */
	void delete(int pointId);

	/**
	 *
	 * @return
	 */
	List<PointEntity> getAll();

	/**
	 *
	 * @param pointId
	 * @return
	 */
	PointEntity getById(int pointId);

	/**
	 *
	 * @param name
	 * @return
	 */
	PointEntity getByName(String name);

	/**
	 *
	 * @return
	 */
	List getNames();
}
