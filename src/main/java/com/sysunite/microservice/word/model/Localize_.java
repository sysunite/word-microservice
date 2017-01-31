
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localize_ {

    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("documentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("date")
    @Expose
    private String date;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Localize_{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
