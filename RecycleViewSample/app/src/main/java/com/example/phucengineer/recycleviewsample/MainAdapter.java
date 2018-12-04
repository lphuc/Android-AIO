package com.example.phucengineer.recycleviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Phuc Engineer on 9/8/2018.
 * the Adapter should be only in charged of connecting views and dataset
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> listData;
    private Context mContext;
    public static final int MOVIE = 0;
    public static final int COLOR = 1;
    public static final int PROGRESS_BAR = 2;
    private boolean isAddEnable = true;

    // use for load more
    private int visibleItemLimit = 5;
    private int lastVisibleItemPos, totalItemCount;
    private boolean loading = false;
    private OnLoadMoreListener loadMoreListener;


    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public MainAdapter(Context context, final List<Object> listData, RecyclerView recyclerView) {
        this.listData = listData;
        this.mContext = context;

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
                    totalItemCount = getItemCount();
                    lastVisibleItemPos = linearLayoutManager.findLastVisibleItemPosition();

                    // end has reached
                    if (!loading && totalItemCount <= (lastVisibleItemPos + visibleItemLimit)) {
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }

        ((MainActivity) mContext).setNumberChangeListener(new OnNumberChangeListener() {
            @Override
            public void onNumberChange(int numRow) {
                if (numRow >= listData.size()) {
                    isAddEnable = true;
                } else {
                    isAddEnable = false;
                }
            }
        });

    }

    /**
     * add only 1 item to current list per call
     *
     * @param item an item to be added
     */
    public void addNewItem(Object item) {
        if (isAddEnable) {
            listData.add(item);
            notifyDataSetChanged();
        } else {
            Toast.makeText(mContext, "full of row!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * reset current data list and add a new complete list
     *
     * @param objectList
     */
    public void setNewDataset(List<Object> objectList) {
        listData.clear();
        listData.addAll(objectList);
        notifyDataSetChanged();
    }

    /**
     * add some items to current list
     *
     * @param objectList -> some new items to be added
     */
    public void addMoreItemsToList(List<Object> objectList) {
        listData.addAll(objectList);
        notifyDataSetChanged();

    }

    /**
     * This is where the ViewHolder(s) are created. Since the framework handles the initialization and recycling
     * we only need to use the view type passed in parameter to inflate our View
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view reference
        if (viewType == MOVIE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_main, parent, false);
            return new MovieViewHolder(view);
        } else if (viewType == COLOR) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_color, parent, false);
            return new ButtonViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar, parent, false);
            return new ProgressBarViewHodler(view);
        }
    }

    /**
     * This is where the data is bound to each ViewHolder. This method is called at least once and will be
     * called each time the adapter is notified that the data set has changed
     *
     * @param holder   The ViewHolder
     * @param position The position in our collection of data
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case MOVIE:
                MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
                movieViewHolder.bindData((MovieModel) listData.get(position));
                movieViewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.btn_remove) {
                            Toast.makeText(mContext, "removed item " + position, Toast.LENGTH_SHORT).show();
                            listData.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                });
                break;

            case COLOR:
                final ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
                buttonViewHolder.enableButton();
                buttonViewHolder.setColor((ColorModel) listData.get(position));
                buttonViewHolder.tvAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listData.remove(position);
                        notifyDataSetChanged();
                    }
                });
                break;


            case PROGRESS_BAR:
                final ProgressBarViewHodler progressBarViewHodler = (ProgressBarViewHodler) holder;
                progressBarViewHodler.progressBar.setIndeterminate(true);
        }

    }


    /**
     * Gets the size of the collection of items in our list
     *
     * @return An Integer representing the size of the collection that will be displayed
     */
    @Override
    public int getItemCount() {
        return listData.size();
    }

    /**
     * Gets the item view type. We can return the static constant that the Android framework
     * creates for us.
     *
     * @param position The position in the collection
     * @return The item layout id
     */
    @Override
    public int getItemViewType(int position) {
        if (listData.get(position) instanceof MovieModel) {
            return MOVIE;
        } else if (listData.get(position) instanceof ColorModel) {
            return COLOR;
        } else {
            return PROGRESS_BAR;
        }
    }


    /**
     * View holder for progress bar item
     */
    public static class ProgressBarViewHodler extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public ProgressBarViewHodler(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }

    public void setLoadMore(boolean isLoading) {
        loading = isLoading;
    }
}
