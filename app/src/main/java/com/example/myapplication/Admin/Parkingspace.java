package com.example.myapplication.Admin;

public class Parkingspace {

    public Parkingspace() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public Parkingspace(String status) {
        this.status = status;
    }

    private String id;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Parkingspace(String id, String status) {
        this.id = id;
        this.status = status;
    }

    private String status;
}
