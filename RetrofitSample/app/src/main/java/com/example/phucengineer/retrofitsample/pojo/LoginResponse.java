package com.example.phucengineer.retrofitsample.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author phuc
 */
public class LoginResponse implements Serializable {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
