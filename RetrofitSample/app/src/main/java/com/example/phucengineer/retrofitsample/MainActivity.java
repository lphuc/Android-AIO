package com.example.phucengineer.retrofitsample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.phucengineer.retrofitsample.pojo.UserListResponse;
import com.example.phucengineer.retrofitsample.pojo.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rcvMain;
    MainAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    List<UserModel> personalList = new ArrayList<>();

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fetchData();
    }

    private void initView() {
        swipeRefreshLayout = findViewById(R.id.main_swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        rcvMain = findViewById(R.id.recycler_view_main);
        rcvMain.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, 1, false);
        rcvMain.setLayoutManager(layoutManager);
        rcvMain.setItemAnimator(new DefaultItemAnimator());
        rcvMain.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new MainAdapter(this, personalList);
        rcvMain.setAdapter(adapter);
        rcvMain.setNestedScrollingEnabled(false);
    }

    private void fetchData() {
        // instantiate  APIClient
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserListResponse> call = apiInterface.getListUser(String.valueOf(2));
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                Toast.makeText(getApplicationContext(), "Fetch new data successfully!", Toast.LENGTH_SHORT).show();
                personalList.addAll(response.body().getListUser());
                swipeRefreshLayout.setRefreshing(false);
                adapter.setData(personalList);

            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    /**
     * get called when user swipe down the view
     */
    @Override
    public void onRefresh() {
        fetchData();
    }
}
