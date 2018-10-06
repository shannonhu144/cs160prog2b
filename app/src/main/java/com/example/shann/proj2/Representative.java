package com.example.shann.proj2;

import java.io.Serializable;
import java.util.ArrayList;

public class Representative implements Serializable {
    private String email;
    private String name;
    private String party;
    private String picture;
    private String website;
    private String id;
    private ArrayList<String> committees;
    private ArrayList<Bill> bills;
    public Representative() {
        committees = new ArrayList<>();
        bills = new ArrayList<>();
    }

    public String getWebsite() {
        return website;
    }

    public String getPicture() {
        return picture;
    }

    public String getParty() {
        return party;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        System.out.println(email);
        if (email != null && !email.equals("null")) {
            this.email = email;
        } else {
            this.email = "";
        }
        System.out.println(this.email);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setWebsite(String website) {
        if (website != null && !website.equals("null")) {
            this.website = website;
        } else {
            this.website = "";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Representative rep = (Representative) object;
            if (rep.name.equals(name)) {
                result = true;
            }
        }
        return result;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public ArrayList<String> getCommittees() {
        return committees;
    }

    public void addBill(Bill b) {
        bills.add(b);
    }

    public void addCommittee(String s) {
        committees.add(s);
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public void setCommittees(ArrayList<String> committees) {
        this.committees = committees;
    }
    @Override
    public String toString() {
        return "Name: " + name + " Email: " + email + " Website: " + website + " Picture: " + picture + " ID: " + id;
    }
}
