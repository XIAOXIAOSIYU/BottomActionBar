package com.jackzhao.www.bottomactionbar.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Company {

    public int BranchID;
    public String Street;
    public String City;
    public String State;
    public String ZipCode;
    public String Phone;
    public String Fax;
    public String OpenHour;
    public Double Latitude;
    public Double Longitude;
    public String EnglishName;
    public String ChineseName;
    public String WebSite;
    public String Email;
    public String AboutUsTitle;
    public String AboutUsDesc;
    public String ListID;
    public String Tags;

    public Company() {

    }

    public Company(JSONObject response) {
        try {
            this.setBranchID(Integer.parseInt(response.getString("BranchID")));
            this.setChineseName(response.getString("ChineseName"));
            this.setEnglishName(response.getString("EnglishName"));
            this.setPhone(response.getString("Phone"));
            this.setStreet(response.getString("Street"));
            this.setCity(response.getString("City"));
            this.setFax(response.getString("Fax"));
            this.setLatitude(Double.parseDouble(response.getString("Latitude")));
            this.setLongitude(Double.parseDouble(response.getString("Longitude")));
            this.setOpenHour(response.getString("OpenHour"));
            this.setWebSite(response.getString("WebSite"));
            this.setEmail(response.getString("Email"));
            this.setZipCode(response.getString("ZipCode"));
            this.setState(response.getString("State"));
            this.setOpenHour(response.getString("OpenHour"));
            this.setTags(response.getString("Tags"));
            this.setListID(response.getString("ListID"));
            this.setAboutUsDesc(response.getString("AboutUsDesc"));
            this.setAboutUsTitle(response.getString("AboutUsTitle"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getOpenHour() {
        return OpenHour;
    }

    public void setOpenHour(String openHour) {
        OpenHour = openHour;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAboutUsTitle() {
        return AboutUsTitle;
    }

    public void setAboutUsTitle(String aboutUsTitle) {
        AboutUsTitle = aboutUsTitle;
    }

    public String getAboutUsDesc() {
        return AboutUsDesc;
    }

    public void setAboutUsDesc(String aboutUsDesc) {
        AboutUsDesc = aboutUsDesc;
    }

    public String getListID() {
        return ListID;
    }

    public void setListID(String listID) {
        ListID = listID;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

}
