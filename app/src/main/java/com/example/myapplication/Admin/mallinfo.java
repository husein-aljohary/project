package com.example.myapplication.Admin;

public class mallinfo {
    private String city;
    private  String mall;
    private String parksnumber;
    ////
    private Parkingspace space;



    public mallinfo( ) {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getParksnumber() {
        return parksnumber;
    }

    public void setParksnumber(String parksnumber) {
        this.parksnumber = parksnumber;
    }

    /////////////////////////////
    public Parkingspace getSpace() {
        return space;
    }
    public void setSpace(Parkingspace space) {
        this.space = space;
    }


}
