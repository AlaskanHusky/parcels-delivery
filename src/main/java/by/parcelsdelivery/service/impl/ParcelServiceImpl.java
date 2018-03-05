package by.parcelsdelivery.service.impl;

import by.parcelsdelivery.dao.ParcelDAO;
import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService
{
	private ParcelDAO parcelDAO;

	public ParcelServiceImpl()
	{
		System.out.println("ParcelServiceImpl Constructor");
	}

	@Override
	public void createParcel(ParcelEntity parcel)
	{
		parcelDAO.createParcel(parcel);
	}

	@Override
	public void updateParcel(ParcelEntity parcel)
	{
		parcelDAO.updateParcel(parcel);
	}

	@Override
	public void deleteParcel(int parcelId)
	{
		parcelDAO.deleteParcel(parcelId);
	}

	@Override
	public List<ParcelEntity> getAllParcels()
	{
		return parcelDAO.getAllParcels();
	}

	@Override
	public ParcelEntity getParcel(int parcelId)
	{
		return parcelDAO.getParcel(parcelId);
	}

	@Override
	public ParcelEntity getParcelByUUID(String uuid)
	{
		return parcelDAO.getParcelByUUID(uuid);
	}

	public void setParcelDAO(ParcelDAO parcelDAO)
	{
		this.parcelDAO = parcelDAO;
	}
}
