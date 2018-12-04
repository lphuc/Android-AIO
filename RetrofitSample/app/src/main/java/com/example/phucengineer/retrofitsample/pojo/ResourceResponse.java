package com.example.phucengineer.retrofitsample.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Created by Phuc Engineer on 10/1/2018.
 */

/**
 * @SerializedName annotation is used to specify the name of the field that’s in the JSON Response.
 */
public class ResourceResponse {
    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("total")
    private Integer total;

    @SerializedName("total_pages")
    private Integer totalPage;

    @SerializedName("data")
    private List<Resource> list;


    private class Resource {
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        @SerializedName("color")
        private String color;

        @SerializedName("pantone_value")
        private String pantoneValue;

    }

}
