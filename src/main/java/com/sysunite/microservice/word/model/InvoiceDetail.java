
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceDetail {

    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("amount")
    @Expose
    private Double amount;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvoiceDetail{" +
                "detail='" + detail + '\'' +
                ", amount=" + amount +
                '}';
    }
}
