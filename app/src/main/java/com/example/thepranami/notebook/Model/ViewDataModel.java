package com.example.thepranami.notebook.Model;

import java.io.Serializable;

public class ViewDataModel implements Serializable {
    //int srno;
    private String srno, amount, name, address, contact, other;

    public ViewDataModel(String srno, String amount, String name, String address, String contact, String other) {
        this.srno = srno;
        this.amount = amount;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.other = other;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
