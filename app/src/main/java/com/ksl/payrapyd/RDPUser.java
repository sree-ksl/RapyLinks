package com.ksl.payrapyd;

import com.rapydsdk.ppackages.RPDUser;

import org.json.JSONObject;

public class RDPUser {

    String id;
    JSONObject data;
    String status;
    static String firstName;
    static String lastName;
    static String contactNum;
    static String contactEmail;

    public RDPUser(String id, JSONObject data, String status) {
        this.id = id;
        this.data = data;
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public JSONObject getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getContactNum() {
        return contactNum;
    }

    public static String getContactEmail() {
        return contactEmail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void setFirstName(String firstName) {
        RDPUser.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        RDPUser.lastName = lastName;
    }

    public static void setContactNum(String contactNum) {
        RDPUser.contactNum = contactNum;
    }

    public static void setContactEmail(String contactEmail) {
        RDPUser.contactEmail = contactEmail;
    }
}
