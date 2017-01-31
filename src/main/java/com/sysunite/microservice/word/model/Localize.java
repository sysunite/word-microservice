
package com.sysunite.microservice.word.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localize {

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


    @Override
    public String toString() {
        return "Localize{" +
                "telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                '}';
    }
}
