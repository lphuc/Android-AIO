package com.example.phucengineer.retrofitsample;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * Retrofit is a type-safe REST client for Android and Java which aim to consume RESTful web service
 * Retrofit2 by default leverages OkHttp as the networking layer and is built on top of it
 * Retrofit automatically serialises the JSON response using a POJO(Plain Old Java Object)
 * To serialise JSON we need a converter to convert it into Gson first by using converter-gson
 * </p>
 *
 * @author lhphuc
 */

class APIClient<GenericResponse> {
    private static Retrofit retrofit = null;
    private static final int CONNECTION_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;

    /**
     * get called every time setting up a Retrofit interface
     *
     * @return
     */
    APIClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * Main Entry function for every HTTP request
     *
     * @param call
     * @param onResponse
     */
    public void makeRequest(Call<GenericResponse> call, final OnResponse<GenericResponse> onResponse) {
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {

                //notify to the activity/fragment that register this callback
                onResponse.onRequestComplete(response);
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                //notify to the activity/fragment that register this callback
                onResponse.onRequestFailed(t);
            }
        });
    }

    /**
     * generic callback for all request
     *
     * @param <T> generic type
     */
    public interface OnResponse<T> {
        void onRequestComplete(Response<T> response);

        void onRequestFailed(Throwable error);
    }
}
