
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInvoiceEmitter {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("bankData")
    @Expose
    private BankData bankData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public BankData getBankData() {
        return bankData;
    }

    public void setBankData(BankData bankData) {
        this.bankData = bankData;
    }

    @Override
    public String toString() {
        return "DataInvoiceEmitter{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", bankData=" + bankData +
                '}';
    }
}
