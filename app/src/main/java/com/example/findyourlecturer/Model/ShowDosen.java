package com.example.findyourlecturer.Model;


public class ShowDosen {

    private String longitude;
    private String latidude;
    private String status;
    private String ssid;


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatidude() {
        return latidude;
    }

    public void setLatidude(String latidude) {
        this.latidude = latidude;
    }


    public ShowDosen() {
    }

    public ShowDosen(String longitude, String latidude, String status, String ssid) {
        this.longitude = longitude;
        this.latidude = latidude;
        this.status = status;
        this.ssid = ssid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
}
