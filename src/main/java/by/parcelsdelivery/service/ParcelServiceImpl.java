package by.parcelsdelivery.service;

import by.parcelsdelivery.model.Parcel;
import by.parcelsdelivery.dao.ParcelDAO;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelDAO prclDAO;

    @Override
    public void addParcel(Parcel prcl) {
        prclDAO.addParcel(prcl);
    }

    @Override
    public List<Parcel> getAllParcels() {
        return prclDAO.getAllParcels();
    }

    @Override
    public void deleteParcel(int parcelId) {
        prclDAO.deleteParcel(parcelId);
    }

    public Parcel getParcel(int parcelID) {
        return prclDAO.getParcel(parcelID);
    }

    public Parcel updateParcel(Parcel prcl) {
        // TODO Auto-generated method stub
        return prclDAO.updateParcel(prcl);
    }

}
