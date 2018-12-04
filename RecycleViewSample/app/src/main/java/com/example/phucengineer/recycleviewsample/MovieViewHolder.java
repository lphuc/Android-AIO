package com.example.phucengineer.recycleviewsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 * Created by Phuc Engineer on 9/8/2018.
 */

/**
 * MainAdapter extends this class to reduce findViewById() method call
 * provide a reference to the views for each data item
 * complex data item may need more than one view per item
 * provide access to all the views for a data item in a view holder
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    Button btnRemove;
    private EditText edtAmount;

    /**
     * The ViewHolder that will be used to display the data in each item shown
     * in the RecyclerView
     *
     * @param itemView The layout view group used to display the data
     */
    public MovieViewHolder(View itemView) {
        super(itemView);
        btnRemove = itemView.findViewById(R.id.btn_remove);
        edtAmount = itemView.findViewById(R.id.edt_amount);
    }

    /**
     * Method to bind data to view holder
     *
     * @param movieItem: the view model that contains the data
     */
    public void bindData(MovieModel movieItem) {
        edtAmount.setText(movieItem.getUserRating());
    }
}
