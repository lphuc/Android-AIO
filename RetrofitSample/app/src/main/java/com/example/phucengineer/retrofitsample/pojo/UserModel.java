package com.example.phucengineer.retrofitsample.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * <p>
 *   Serializable is just a maker interface (has no variable or method)
 *  It is used to mark java classes so that objects of these classes can get certain capability
 *  if a parent class has implemented Serializable interface then child class doesn't need to implement. but vice versa is not true
 *  Only non-static data members are saved via Serialization process
 *  Constructor of object will not be called when an object is deserialized
 * </p>
 * @author phuc
 */
public class UserModel implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("avatar")
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
