package com.example.phucengineer.retrofitsample;

import com.example.phucengineer.retrofitsample.pojo.CreateUserResponse;
import com.example.phucengineer.retrofitsample.pojo.LoginResponse;
import com.example.phucengineer.retrofitsample.pojo.ResourceResponse;
import com.example.phucengineer.retrofitsample.pojo.UserListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit provide a list of annotations for HTTP methods:
 * <p>
 * Methods: @GET, @POST, @PUT, @DELETE, @PATCH, @HEAD
 *
 * @Body - Send Java object as request Body
 * @Url - use dynamic URLs
 * @Query - add a method parameter with @Query() and a query parameter name describing the type
 * To URL encode a query use the form: Query(value = "auth_token",encoded = true) String auth_token
 * @Field - send data as form-urlencoded. This requires a @FormUrlEncoded annotation attached with the method
 * The @Field requires a mandatory parameter. In cases when @Field is optional, we can use @Query instead and pass a null value
 * The @Field only works with POST method
 */
public interface UserApi {

    /**
     * getListResource is a method name
     * ResourceModel is a POJO model class for response object
     * The getListResource() method in the above code will be called every time while setting up a Retrofit interface.
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String pass);

    @GET("/api/unknown")
    Call<ResourceResponse> getListResource();

    @FormUrlEncoded
    @POST("/api/users")
    Call<CreateUserResponse> createUser(@Body CreateUserResponse user);

    @GET("/api/users?")
    Call<UserListResponse> getListUser(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserListResponse> doCreateUserWithField(@Field("name") String name,
                                                 @Field("job") String job);
}
