package com.example.phucengineer.recycleviewsample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcView;
    private MainAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Object> listData = new ArrayList<>();
    private EditText edtNumItem;
    private Button btnAddMovie, btnAddGreenItem;
    private OnNumberChangeListener listener;
    private Handler handler;

    public void setNumberChangeListener(OnNumberChangeListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddMovie = findViewById(R.id.btn_add_movie);
        btnAddGreenItem = findViewById(R.id.btn_add_green_item);
        btnAddMovie.setOnClickListener(this);
        btnAddGreenItem.setOnClickListener(this);

        edtNumItem = findViewById(R.id.edt_num_item);
        edtNumItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //trigger onNumberChange() from MainAdapter
                if (listener != null && s.length() > 0) {
                    listener.onNumberChange(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        handler = new Handler();

        rcView = findViewById(R.id.rcv_main);
        // improve performance if the content change doesn't affect the size
        rcView.setHasFixedSize(true);

        // use a Linear layout manager with vertical orientation
        layoutManager = new LinearLayoutManager(this, 1, false);
        rcView.setLayoutManager(layoutManager);

        rcView.setItemAnimator(new DefaultItemAnimator());
        // add a line between rows
        rcView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // specify a adapter and assign to the recycle view
        adapter = new MainAdapter(this, getListMovieData(), rcView);

        rcView.setAdapter(adapter);

        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                // add null, so the adapter will check view type and show progress bar at bottom
                //add null , so the adapter will check view_type and show progress bar at bottom
                listData.add(null);
                adapter.notifyDataSetChanged();

                // use handler only for simulate, this process normally replaced with an network API call process (retrofit)
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // remove item progress bar after load more process is done!
                        listData.remove(listData.size() - 1);

                        int start = listData.size();

                        // add more 5 items on each load more
                        for (int i = start + 1; i <= start + 5; i++) {
                            adapter.addNewItem(new ColorModel(R.color.colorAccent));
                        }
                        adapter.notifyDataSetChanged();
                        adapter.setLoadMore(false);
                    }
                }, 2000);

            }
        });


    }

    private List<Object> getListMovieData() {
        listData.add(new MovieModel("movie2", "5.4", "4.3"));
        listData.add(new MovieModel("movie3", "7.8", "6.4"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie3", "7.8", "6.4"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie2", "5.4", "4.3"));
        listData.add(new MovieModel("movie3", "7.8", "6.4"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie2", "5.4", "4.3"));
        listData.add(new MovieModel("movie3", "7.8", "6.4"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie2", "5.4", "4.3"));
        listData.add(new MovieModel("movie3", "7.8", "6.4"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new MovieModel("movie5", "8.5", "7.3"));
        listData.add(new ColorModel(R.color.colorAccent));
        return listData;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_movie:
                adapter.addNewItem(new MovieModel("movie-x", "5.0", "5.0"));
                break;
            case R.id.btn_add_green_item:
                adapter.addNewItem(new ColorModel(getColor(R.color.colorPrimary)));
                break;

        }
    }
}
