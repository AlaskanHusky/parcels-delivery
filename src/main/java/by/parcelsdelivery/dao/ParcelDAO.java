package by.parcelsdelivery.dao;

import by.parcelsdelivery.model.Parcel;

import java.util.List;

public interface ParcelDAO {

    public void addParcel(Parcel p);

    public Parcel getParcel(int parcelId);

    public List<Parcel> getAllParcels();

    public void deleteParcel(int parcelId);

    public Parcel updateParcel(Parcel p);

}
