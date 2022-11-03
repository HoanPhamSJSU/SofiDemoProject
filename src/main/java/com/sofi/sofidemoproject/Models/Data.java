package com.sofi.sofidemoproject.Models;

public class Data {
    private Gif[] data;

    public Data() {}

    public Data(Gif[] data) {
        this.data = data;
    }

    //getter and setter

    public Gif[] getData() {
        return data;
    }

    public void setData(Gif[] data) {
        this.data = data;
    }
}
