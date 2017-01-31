
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("localize")
    @Expose
    private Localize localize;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Localize getLocalize() {
        return localize;
    }

    public void setLocalize(Localize localize) {
        this.localize = localize;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                ", localize=" + localize +
                '}';
    }
}
