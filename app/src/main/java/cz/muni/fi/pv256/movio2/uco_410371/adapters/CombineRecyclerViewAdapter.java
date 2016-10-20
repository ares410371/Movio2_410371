package cz.muni.fi.pv256.movio2.uco_410371.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;
import cz.muni.fi.pv256.movio2.uco_410371.network.Singleton;

/**
 * Created by Benjamin Varga on 20.10.2016.
 */

public class CombineRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Object> mItems;
    private boolean mTwoPane;

    private final int MOVIE = 1, CATEGORY = 2;

    public CombineRecyclerViewAdapter(Context context, List<Object> items, boolean twoPane) {
        mContext = context;
        mItems = items;
        mTwoPane = twoPane;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof String) {
            return CATEGORY;
        } else if (mItems.get(position) instanceof List) {
            return MOVIE;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case MOVIE: {
                View view1 = inflater.inflate(R.layout.view_holder_horizontal, parent, false);
                return new HorizontalRVViewHolder(view1);
            }
            case CATEGORY: {
                View view2 = inflater.inflate(R.layout.view_holder_category, parent, false);
                return new CategoryViewHolder(view2);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case MOVIE: {
                HorizontalRVViewHolder horizontalRVViewHolder = (HorizontalRVViewHolder) holder;
                configureHorizontalRVViewHolder(horizontalRVViewHolder, position);
                break;
            }
            case CATEGORY: {
                CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
                configureCategoryViewHolder(position, categoryViewHolder);
            }
        }
    }

    private void configureCategoryViewHolder(int position, CategoryViewHolder categoryViewHolder) {
        String str = (String) mItems.get(position);
        if (str != null) {
            categoryViewHolder.getItemCategory().setText(str);
        }
    }

    private void configureHorizontalRVViewHolder(HorizontalRVViewHolder horizontalRVViewHolder, int position) {
        RecyclerView horizontalRV = horizontalRVViewHolder.getRecyclerView();
        horizontalRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        horizontalRV.setHasFixedSize(true);

        List<Movie> movies = (List<Movie>) mItems.get(position);

        MovieRecyclerViewAdapter movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(movies, mContext, mTwoPane);
        horizontalRV.setAdapter(movieRecyclerViewAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(horizontalRV);
    }

    public static class HorizontalRVViewHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryTitle;
        private Button mCategoryButton;
        private RecyclerView mRecyclerView;

        public HorizontalRVViewHolder(View itemView) {
            super(itemView);
            mCategoryTitle = (TextView)itemView.findViewById(R.id.category_title);
            mCategoryButton = (Button)itemView.findViewById(R.id.category_button);
            mRecyclerView = (RecyclerView)itemView.findViewById(R.id.horizontal_recycler_view);
        }

        public RecyclerView getRecyclerView() {
            return mRecyclerView;
        }

        public void setRecyclerView(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
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

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            mItemCategory = (TextView)itemView.findViewById(R.id.category_title);
        }

        public TextView getItemCategory() {
            return mItemCategory;
        }

        public void setItemCategory(TextView itemCategory) {
            mItemCategory = itemCategory;
        }
    }

    public void addItems(List<Object> items) {
        mItems.addAll(items);
        notifyItemInserted(items.size()-1);
    }
}
