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

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rcvMain;
    MainAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    List<UserModel> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
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

        adapter = new MainAdapter(this, rcvMain);
        rcvMain.setAdapter(adapter);
        rcvMain.setNestedScrollingEnabled(false);

        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                // add null, so the adapter will check view type and show progress bar at bottom
                userList.add(null);
                adapter.notifyDataSetChanged();

                adapter.currentPage++;
                loadMoreData(adapter.currentPage);

            }
        });
    }

    public void initData() {
        new UserRequest.Builder()
                .setPage("0")
                .create()
                .getListUser(this, new APIClient.OnResponse<UserListResponse>() {
                    @Override
                    public void onRequestComplete(Response<UserListResponse> response) {
                        swipeRefreshLayout.setRefreshing(false);
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Fetch new data successfully!", Toast.LENGTH_SHORT).show();

                            userList.clear();
                            for (UserModel model : response.body().getListUser()) {
                                userList.add(model);
                            }

                            adapter.totalPage = response.body().getTotalPages();
                            adapter.setData(userList);

                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onRequestFailed(Throwable error) {
                        Toast.makeText(getApplicationContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

    }

    public void loadMoreData(int page) {
        new UserRequest.Builder()
                .setPage(String.valueOf(page))
                .create()
                .getListUser(this, new APIClient.OnResponse<UserListResponse>() {
                    @Override
                    public void onRequestComplete(Response<UserListResponse> response) {
                        // remove item progress bar after load more process is done!
                        userList.remove(userList.size() - 1);

                        swipeRefreshLayout.setRefreshing(false);
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Fetch more data successfully!", Toast.LENGTH_SHORT).show();
                            userList.addAll(response.body().getListUser());
                            adapter.setData(userList);


                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to fetch more data!", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setLoadMore(false);
                    }

                    @Override
                    public void onRequestFailed(Throwable error) {
                        Toast.makeText(getApplicationContext(), "Failed to fetch more data!", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

    }

    /**
     * get called when user swipe down the view
     */
    @Override
    public void onRefresh() {
        initData();
        adapter.currentPage = 0;
    }
}
