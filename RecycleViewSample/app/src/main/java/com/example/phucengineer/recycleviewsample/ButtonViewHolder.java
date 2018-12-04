package com.example.phucengineer.recycleviewsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/*
 * Created by Phuc Engineer on 9/9/2018.
 */
public class ButtonViewHolder extends RecyclerView.ViewHolder {
    TextView tvAdd;

    public ButtonViewHolder(View itemView) {
        super(itemView);
        tvAdd = itemView.findViewById(R.id.tv_color);

    }

    public void enableButton() {
        tvAdd.setVisibility(View.VISIBLE);
    }

    public void disableButton() {
        tvAdd.setVisibility(View.GONE);
    }

    public void setColor(ColorModel item) {
        tvAdd.setBackgroundColor(item.getColor());
    }

}
