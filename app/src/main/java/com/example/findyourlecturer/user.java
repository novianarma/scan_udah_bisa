package com.example.findyourlecturer;

public class user {
    private String namalengkap;
    private String email;
    private String password;
    private String noidentitas;
    private String user;
    private String key;
    private String longitude;
    private String latidude;


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


    public user() {
    }

    public user(String namalengkap, String email, String password, String noidentitas, String user, String key, String longitude, String latidude) {
        this.namalengkap = namalengkap;
        this.email = email;
        this.password = password;
        this.noidentitas = noidentitas;
        this.user = user;
        this.key = key;
        this.longitude = longitude;
        this.latidude = latidude;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoidentitas() {
        return noidentitas;
    }

    public String getNamalengkap() {

        return namalengkap;
    }


    public void setNoidentitas(String noidentitas) {
        this.noidentitas = noidentitas;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
