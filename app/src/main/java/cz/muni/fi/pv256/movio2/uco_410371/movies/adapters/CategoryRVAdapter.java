package cz.muni.fi.pv256.movio2.uco_410371.movies.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

public class CategoryRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY = 0, MOVIES = 1;
    private Context mContext;
    private List<List<Movie>> mItems;
    private List<String> mCategories;
    private boolean mTwoPane;

    public CategoryRVAdapter(Context context, boolean twoPane) {
        mContext = context;
        mItems = new ArrayList<>();
        mTwoPane = twoPane;
        mCategories = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return (mItems.size() > 0) ? mItems.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() == 0) {
            return EMPTY;
        }
        if (isListOfMovies(mItems.get(position))) {
            return MOVIES;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MOVIES: {
                View view = inflater.inflate(R.layout.view_holder_recycler_view, parent, false);
                return new HorizontalRVViewHolder(view);
            }
            case EMPTY: {
                View view = inflater.inflate(R.layout.view_holder_empty, parent, false);
                return new EmptyViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MOVIES: {
                HorizontalRVViewHolder horizontalRVViewHolder = (HorizontalRVViewHolder) holder;
                configureHorizontalRVViewHolder(horizontalRVViewHolder, position);
                break;
            }
        }
    }

    public void addItems(String type, List<Movie> movies) {
        mCategories.add(type);
        mItems.add(movies);
        notifyItemInserted(1);
    }

    private boolean isListOfMovies(Object obj) {
        if (obj instanceof List) {
            for (Object o : (List) obj) {
                if (!(o instanceof Movie)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void configureHorizontalRVViewHolder(HorizontalRVViewHolder viewHolder, int position) {
        RecyclerView horizontalRV = viewHolder.getRecyclerView();
        horizontalRV.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        horizontalRV.setHasFixedSize(true);

        List<Movie> movies = new ArrayList<>();
        if (isListOfMovies(mItems.get(position))) {
            movies = mItems.get(position);
        }

        String category = String.valueOf(mCategories.get(position));
        viewHolder.getCategoryTitle().setText(category);

        MoviesHorizontalRVAdapter moviesHorizontalRVAdapter =
                new MoviesHorizontalRVAdapter(mContext, movies, mTwoPane);
        horizontalRV.setAdapter(moviesHorizontalRVAdapter);
    }

    public static class HorizontalRVViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mCategoryTitle;

        public HorizontalRVViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_recycler_view);
            mCategoryTitle = (TextView) itemView.findViewById(R.id.category_title_tv);
        }

        public RecyclerView getRecyclerView() {
            return mRecyclerView;
        }

        public TextView getCategoryTitle() {
            return mCategoryTitle;
        }
    }


    public static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
