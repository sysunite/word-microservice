
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankData {

    @SerializedName("IBAN")
    @Expose
    private String iBAN;
    @SerializedName("BIC")
    @Expose
    private String bIC;
    @SerializedName("KvK")
    @Expose
    private String kvK;
    @SerializedName("BTW")
    @Expose
    private String bTW;

    public String getIBAN() {
        return iBAN;
    }

    public void setIBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getBIC() {
        return bIC;
    }

    public void setBIC(String bIC) {
        this.bIC = bIC;
    }

    public String getKvK() {
        return kvK;
    }

    public void setKvK(String kvK) {
        this.kvK = kvK;
    }

    public String getBTW() {
        return bTW;
    }

    public void setBTW(String bTW) {
        this.bTW = bTW;
    }

    @Override
    public String toString() {
        return "BankData{" +
                "iBAN='" + iBAN + '\'' +
                ", bIC='" + bIC + '\'' +
                ", kvK='" + kvK + '\'' +
                ", bTW='" + bTW + '\'' +
                '}';
    }
}
