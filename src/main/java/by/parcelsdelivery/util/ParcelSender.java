package by.parcelsdelivery.util;

import by.parcelsdelivery.entity.ParcelEntity;
import by.parcelsdelivery.service.ParcelService;
import by.parcelsdelivery.service.PointService;

public class ParcelSender {

    private HttpRequester httpRequester;
    private ParcelService parcelService;
    private PointService pointService;
    private PointUtil pointUtil;

    public ParcelSender() {
        System.out.println("ParcelSender Constructor");
    }

    public void handleParcel(ParcelEntity parcelEntity) {
        if (pointUtil.isLast(parcelEntity.getPath())) {
            updateStatusCallback(parcelEntity);
        } else {
            sendParcel(parcelEntity);
        }
    }

    public void sendParcel(ParcelEntity parcelEntity) {
        String parcelPath = parcelEntity.getPath();
        String nextPointName = pointUtil.getNextPointName(parcelPath);
        String nextPointAddress = pointService.getPointByName(nextPointName).getUri();

        httpRequester.repeatRequest(nextPointAddress);
        parcelEntity.setStatus("On Next Point");
        parcelService.updateParcel(parcelEntity);
        httpRequester.doPost(nextPointAddress + "/parcels/receive", parcelEntity);
    }

    public void updateStatusCallback(String uuid) {
        updateStatusCallback(parcelService.getParcelByUUID(UUIDUtil.getUUID(uuid)));
    }

    private void updateStatusCallback(ParcelEntity parcelEntity) {
        parcelEntity.setStatus("Delivered");
        parcelService.updateParcel(parcelEntity);

        String parcelPath = parcelEntity.getPath();

        if (pointUtil.getPointIndex(parcelPath) == 0) {
            return;
        }

        String previousPointName = pointUtil.getPreviousPointName(parcelPath);
        String previousPointAddress = pointService.getPointByName(previousPointName).getUri();

        httpRequester.repeatRequest(previousPointAddress);
        httpRequester.doPost(previousPointAddress +  "/parcels/delivered", parcelEntity.getUuid());
    }

    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    public void setPointService(PointService pointService) {
        this.pointService = pointService;
    }

    public void setHttpRequester(HttpRequester httpRequester) {
        this.httpRequester = httpRequester;
    }

    public void setPointUtil(PointUtil pointUtil) {
        this.pointUtil = pointUtil;
    }
}
