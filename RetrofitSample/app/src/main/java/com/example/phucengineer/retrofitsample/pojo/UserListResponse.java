package com.example.phucengineer.retrofitsample.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/*
 * Created by Phuc Engineer on 10/1/2018.
 */
public class UserListResponse implements Serializable {

    @SerializedName("page")
    private Integer page;
    @SerializedName("per_page")
    private Integer perPage;
    @SerializedName("total")
    private Integer total;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("data")
    private List<UserModel> listData;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalUser() {
        return total;
    }
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<UserModel> getListUser() {
        return listData;
    }

}
