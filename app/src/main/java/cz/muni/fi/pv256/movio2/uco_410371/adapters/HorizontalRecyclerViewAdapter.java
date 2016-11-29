package cz.muni.fi.pv256.movio2.uco_410371.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY = 0, MOVIES = 1, CATEGORY = 2;
    private Context mContext;
    // mItems contains Title (String) and Movies(List<MovieTable>)
    private List<Object> mItems;
    private boolean mTwoPane;

    public HorizontalRecyclerViewAdapter(Context context, boolean twoPane) {
        mContext = context;
        mItems = new ArrayList<>();
        mTwoPane = twoPane;
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
        if (mItems.get(position) instanceof String) {
            return CATEGORY;
        } else if (isListOfMovies(mItems.get(position))) {
            return MOVIES;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MOVIES: {
                View view1 = inflater.inflate(R.layout.view_holder_horizontal_rv, parent, false);
                return new HorizontalRVViewHolder(view1);
            }
            case CATEGORY: {
                View view2 = inflater.inflate(R.layout.view_holder_category, parent, false);
                return new CategoryViewHolder(view2);
            }
            case EMPTY: {
                View view3 = inflater.inflate(R.layout.view_holder_empty, parent, false);
                return new EmptyViewHolder(view3);
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
            case CATEGORY: {
                CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
                configureCategoryViewHolder(position, categoryViewHolder);
                break;
            }
        }
    }

    public void addItems(List<Object> items) {
        int size = 0;
        for (Object obj : items) {
            if (obj instanceof String || isListOfMovies(obj)) {
                mItems.add(obj);
                size++;
            }
        }
        notifyItemInserted(size - 1);
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

    private void configureCategoryViewHolder(final int position, CategoryViewHolder categoryViewHolder) {
        String str = (String) mItems.get(position);
        if (str != null) {
            categoryViewHolder.getCategoryTitle().setText(str);
        }
        // todo
        // Implement soon
        // Show only one category in vertical recyclerView
        categoryViewHolder.getCategoryButton().setVisibility(View.INVISIBLE);
    }

    @SuppressWarnings("unchecked")
    private void configureHorizontalRVViewHolder(HorizontalRVViewHolder viewHolder, int position) {
        RecyclerView horizontalRV = viewHolder.getRecyclerView();
        horizontalRV.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        horizontalRV.setHasFixedSize(true);

        List<Movie> movies = new ArrayList<>();
        if (isListOfMovies(mItems.get(position))) {
            movies = (List) mItems.get(position);
        }


        MovieHORZRecyclerViewAdapter movieHORZRecyclerViewAdapter =
                new MovieHORZRecyclerViewAdapter(mContext, movies, mTwoPane);
        horizontalRV.setAdapter(movieHORZRecyclerViewAdapter);

//        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
//        java.lang.IllegalStateException: An instance of OnFlingListener already set.
//        snapHelperStart.attachToRecyclerView(horizontalRV);
//        horizontalRV.setOnFlingListener(snapHelperStart);
    }

    public static class HorizontalRVViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;

        public HorizontalRVViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_recycler_view);
        }

        public RecyclerView getRecyclerView() {
            return mRecyclerView;
        }

        public void setRecyclerView(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
        }

    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryTitle;
        private Button mCategoryButton;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mCategoryTitle = (TextView) itemView.findViewById(R.id.category_title);
            mCategoryButton = (Button) itemView.findViewById(R.id.category_button);
        }

        public TextView getCategoryTitle() {
            return mCategoryTitle;
        }

        public void setCategoryTitle(TextView categoryTitle) {
            mCategoryTitle = categoryTitle;
        }

        public Button getCategoryButton() {
            return mCategoryButton;
        }

        public void setCategoryButton(Button categoryButton) {
            mCategoryButton = categoryButton;
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
