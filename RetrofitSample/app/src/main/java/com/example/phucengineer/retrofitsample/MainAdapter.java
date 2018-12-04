package com.example.phucengineer.retrofitsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phucengineer.retrofitsample.pojo.UserModel;

import java.util.List;

/*
 * Created by lhphuc on 10/2/2018.
 */
public class MainAdapter extends RecyclerView.Adapter {
    private List<UserModel> listData;
    private Context mContext;

    MainAdapter(Context context, List<UserModel> listData) {
        this.listData = listData;
        mContext = context;
    }

    public void setData(List<UserModel> listData) {
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        Glide.with(mContext).load(listData.get(position).getAvatar()).into(userViewHolder.imgAvatar);
        userViewHolder.tvFirstName.setText(listData.get(position).getFirst_name());
        userViewHolder.tvLastName.setText(listData.get(position).getLast_name());
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName, tvLastName;
        ImageView imgAvatar;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
            imgAvatar = itemView.findViewById(R.id.avatar);
        }
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
