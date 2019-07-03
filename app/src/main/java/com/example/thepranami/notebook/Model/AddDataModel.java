package com.example.thepranami.notebook.Model;

public class AddDataModel {
    private int donerId;
    private String amount;
    private String name;
    private String address;
    private String mobile;
    private String other;

    public AddDataModel(int donerId, String amount, String name, String address, String mobile, String other) {
        this.donerId = donerId;
        this.amount = amount;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.other = other;
    }

    public int getDonerId() {
        return donerId;
    }

    public void setDonerId(int donerId) {
        this.donerId = donerId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
