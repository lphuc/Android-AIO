package com.example.phucengineer.retrofitsample;

import android.content.Context;

import com.example.phucengineer.retrofitsample.pojo.UserListResponse;

import retrofit2.Call;

/**
 * Builder Pattern for building request object
 * All parameters used in request body will be added to this instance
 *
 * @author phuc
 */
public class UserRequest {
    private String page;
    private String name, job;

    /**
     * The constructor is private, so it can't be accessed from other class
     * we must use the Builder to create new object of UserRequest class
     *
     * @param builder
     */
    private UserRequest(final Builder builder) {
        page = builder.page;
        name = builder.name;
        job = builder.job;
    }

    public static class Builder {
        private String page, name, job;

        public Builder setPage(final String page) {
            this.page = page;
            return this;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setJob(final String job) {
            this.job = job;
            return this;
        }

        /**
         * Make parameters optional or required here
         *
         * @return
         */
        public UserRequest create() {
            UserRequest object = new UserRequest(this);
            boolean isNumber = true;
            try {
                int number = Integer.parseInt(object.page);
            } catch (NumberFormatException e) {
                isNumber = false;
            }
            if (object.page == null || object.page.isEmpty() || !isNumber) {
                throw new IllegalArgumentException("page must be an integer");
            }
            return object;
        }

    }

    public void getListUser(Context context, APIClient.OnResponse<UserListResponse> callback) {
        APIClient<UserListResponse> client = new APIClient<>();
        UserApi userApi = client.getRetrofit().create(UserApi.class);
        Call<UserListResponse> call = userApi.getListUser(String.valueOf(page));
        client.makeRequest(call, callback);
    }

}
