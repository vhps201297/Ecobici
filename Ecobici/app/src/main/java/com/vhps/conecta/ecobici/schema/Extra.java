package com.vhps.conecta.ecobici.schema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 *  * Clase Extra que sirve como modelo del Json obtenido.

 */
public class Extra {

    @SerializedName("NearbyStationList")
    @Expose
    private List<Integer> nearbyStationList = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("districtCode")
    @Expose
    private String districtCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("zip")
    @Expose
    private String zip;

    public List<Integer> getNearbyStationList() {
        return nearbyStationList;
    }

    public void setNearbyStationList(List<Integer> nearbyStationList) {
        this.nearbyStationList = nearbyStationList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}