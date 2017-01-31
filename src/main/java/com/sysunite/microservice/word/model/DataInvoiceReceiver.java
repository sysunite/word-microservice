
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInvoiceReceiver {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private Address_ address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address_ getAddress() {
        return address;
    }

    public void setAddress(Address_ address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DataInvoiceReceiver{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
