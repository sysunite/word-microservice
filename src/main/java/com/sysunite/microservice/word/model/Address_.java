
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address_ {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;
    @SerializedName("zipCode")
    @Expose
    private String zipCode;
    @SerializedName("city")
    @Expose
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address_{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
