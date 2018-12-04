package com.example.phucengineer.retrofitsample;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by Phuc Engineer on 10/1/2018.
 */

/**
 * Retrofit is a type-safe REST client for Android and Java which aim to consume RESTful web service
 * Retrofit2 by default leverages OkHttp as the networking layer and is built on top of it
 * Retrofit automatically serialises the JSON response using a POJO(Plain Old Java Object)
 * To serialise JSON we need a converter to convert it into Gson first by using converter-gson
 */

class APIClient {
    private static Retrofit retrofit = null;
    private static final int CONNECTION_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;

    /**
     * get called everytime setting up a Retrofit interface
     *
     * @return
     */
    static Retrofit getClient() {

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
        return retrofit;
    }
}
