package com.jackzhao.www.bottomactionbar.models;

public class Company {

    public int CID;
    public String CName;
    public String EName;
    public String PhoneNumber;
    public String StreetName;
    public String CityName;
    public double QueryDistance;
    public String latitude;
    public String longitude;
    public int ReviewCount;
    public double ReviewAVG;
    public String ImageURL;
    public String ZipCode;
    public String State;
    public String OpenHour;
    public String Tags;
    public String listID;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public double getQueryDistance() {
        return QueryDistance;
    }

    public void setQueryDistance(double queryDistance) {
        QueryDistance = queryDistance;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(int reviewCount) {
        ReviewCount = reviewCount;
    }

    public double getReviewAVG() {
        return ReviewAVG;
    }

    public void setReviewAVG(double reviewAVG) {
        ReviewAVG = reviewAVG;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getOpenHour() {
        return OpenHour;
    }

    public void setOpenHour(String openHour) {
        OpenHour = openHour;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }
}
