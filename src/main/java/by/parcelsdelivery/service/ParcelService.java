package by.parcelsdelivery.service;

import by.parcelsdelivery.model.Parcel;

import java.util.List;

public interface ParcelService {

    public void addParcel(Parcel p);

    public Parcel getParcel(int parcelId);

    public List<Parcel> getAllParcels();

    public void deleteParcel(int parcelId);

    public Parcel updateParcel(Parcel p);

}
