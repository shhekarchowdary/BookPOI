package com.arr.bookpoi;

public class Poi {
    private String name;
    private double price;
    private String country;
    private String imgName;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Poi(String name, double price, String country) {
        this.name = name;
        this.price = price;
        this.country = country;
        this.imgName = name.toLowerCase().replace(" ","");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
