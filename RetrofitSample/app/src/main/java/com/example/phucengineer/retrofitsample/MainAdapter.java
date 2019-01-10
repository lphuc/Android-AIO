package com.example.phucengineer.retrofitsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phucengineer.retrofitsample.pojo.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author phuc
 */
public class MainAdapter extends RecyclerView.Adapter {
    private List<UserModel> listUser = new ArrayList<>();
    private Context mContext;
    private static final int ITEM_USER = 0;
    private static final int ITEM_LOADING = 1;

    // use for load more
    private int lastVisibleItemPos, totalItemCount;
    private boolean isLoading = false;
    private OnLoadMoreListener loadMoreListener;
    public int currentPage = 0;
    public int totalPage;

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }


    MainAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    // detect scroll down
                    if (dy > 0) {
                        totalItemCount = getItemCount();
                        lastVisibleItemPos = linearLayoutManager.findLastVisibleItemPosition();

                        // end has reached
                        if (!isLoading && currentPage <= totalPage  && lastVisibleItemPos >= totalItemCount - 1) {
                            isLoading = true;
                            if (loadMoreListener != null) {
                                loadMoreListener.onLoadMore();
                            }
                        }
                    }

                }
            });

        }

    }

    public void setData(List<UserModel> listData) {
        listUser.addAll(listData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_USER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_user, parent, false);
            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar, parent, false);
            return new LoadingMoreHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ITEM_USER:
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                Glide.with(mContext).load(listUser.get(position).getAvatar()).into(userViewHolder.imgAvatar);
                userViewHolder.tvFirstName.setText(listUser.get(position).getFirst_name());
                userViewHolder.tvLastName.setText(listUser.get(position).getLast_name());
                break;
            case ITEM_LOADING:
                LoadingMoreHolder moreHolder = (LoadingMoreHolder) holder;
                moreHolder.progressBar.setIndeterminate(true);
                break;
        }
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName, tvLastName;
        ImageView imgAvatar;

        UserViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
            imgAvatar = itemView.findViewById(R.id.avatar);
        }
    }

    public static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingMoreHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }


    @Override
    public int getItemCount() {
        return listUser.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listUser.get(position) != null) {
            return ITEM_USER;
        } else {
            return ITEM_LOADING;
        }
    }

    public void setLoadMore(boolean isLoading) {
        this.isLoading = isLoading;
    }
}
