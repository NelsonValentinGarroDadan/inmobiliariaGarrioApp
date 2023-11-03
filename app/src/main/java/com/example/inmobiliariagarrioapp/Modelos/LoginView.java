package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginView implements Serializable {
    @SerializedName("mail")
    private String Mail;
    @SerializedName("password")
    private String Password;

    public LoginView(String mail, String password) {
        Mail = mail;
        Password = password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
