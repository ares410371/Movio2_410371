package cz.muni.fi.pv256.movio2.uco_410371.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.MovieDetailActivity;
import cz.muni.fi.pv256.movio2.uco_410371.MovieDetailFragment;
import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.model.Movie;

/**
 * Movie RecyclerView Adapter
 * Created by Benjamin Varga on 12.10.2016.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = MovieRecyclerViewAdapter.class.getName();

    private List<Object> mItems;
    private Context mContext;
    private boolean mTwoPane;

    private final int CATEGORY = 0, MOVIE = 1;

    public MovieRecyclerViewAdapter(Context context, List<Object> items, boolean twoPane) {
        mContext = context;
        mItems = items;
        mTwoPane = twoPane;
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof String) {
            return CATEGORY;
        } else if (mItems.get(position) instanceof Movie) {
            return MOVIE;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case CATEGORY :
                View view1 = inflater.inflate(R.layout.view_holder_category, parent, false);
                viewHolder = new CategoryViewHolder(view1);
                break;
            case MOVIE :
                View view2 = inflater.inflate(R.layout.view_holder_movie, parent, false);
                viewHolder = new MovieViewHolder(view2);
                break;
            default:
                View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(view);
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case CATEGORY :
                CategoryViewHolder categoryViewHolder = (CategoryViewHolder)viewHolder;
                configureCategoryViewHolder(categoryViewHolder, position);
                break;
            case MOVIE :
                MovieViewHolder movieViewHolder = (MovieViewHolder)viewHolder;
                configureMovieViewHolder(movieViewHolder, position);
                break;
            default:
                break;
        }
    }

    private void configureMovieViewHolder(MovieViewHolder movieViewHolder, int position) {
        final Movie movie = (Movie) mItems.get(position);

        if (movie != null) {

            movieViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {
                        Toast.makeText(mContext, "Long click on " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    } else {
                        selectDetail(movie.getMovieId()-1);
                    }
                }
            });

            switch (position) {
                case 1 :
                    movieViewHolder.getItemPoster().setImageResource(R.drawable.dummyback1);
                    movieViewHolder.getItemTitle().setText(movie.getTitle());
                    movieViewHolder.getItemRating().setText((new DecimalFormat("#.##")).format(movie.getPopularity()));
                    break;
                case 2 :
                    movieViewHolder.getItemPoster().setImageResource(R.drawable.dummyback2);
                    movieViewHolder.getItemTitle().setText(movie.getTitle());
                    movieViewHolder.getItemRating().setText((new DecimalFormat("#.##")).format(movie.getPopularity()));
                    break;
                case 4 :
                    movieViewHolder.getItemPoster().setImageResource(R.drawable.dummyback3);
                    movieViewHolder.getItemTitle().setText(movie.getTitle());
                    movieViewHolder.getItemRating().setText((new DecimalFormat("#.##")).format(movie.getPopularity()));
                    break;
                case 5 :
                    movieViewHolder.getItemPoster().setImageResource(R.drawable.dummyback4);
                    movieViewHolder.getItemTitle().setText(movie.getTitle());
                    movieViewHolder.getItemRating().setText((new DecimalFormat("#.##")).format(movie.getPopularity()));
                    break;
                default:
            }
        }
    }

    private void configureCategoryViewHolder(CategoryViewHolder categoryViewHolder, int position) {
        String str = (String) mItems.get(position);
        if (str != null) {
            categoryViewHolder.getItemCategory().setText(str);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private ImageView mItemPoster;
        private TextView mItemTitle;
        private TextView mItemRating;

        private ItemClickListener mItemClickListener;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemPoster = (ImageView)itemView.findViewById(R.id.image_item_movie_poster);
            mItemTitle = (TextView)itemView.findViewById(R.id.text_movie_title);
            mItemRating = (TextView)itemView.findViewById(R.id.text_movie_rating);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public ImageView getItemPoster() {
            return mItemPoster;
        }

        public void setItemPoster(ImageView itemPoster) {
            mItemPoster = itemPoster;
        }

        public TextView getItemTitle() {
            return mItemTitle;
        }

        public void setItemTitle(TextView itemTitle) {
            mItemTitle = itemTitle;
        }

        public TextView getItemRating() {
            return mItemRating;
        }

        public void setItemRating(TextView itemRating) {
            mItemRating = itemRating;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            mItemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onClick(view, getLayoutPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            mItemClickListener.onClick(view, getLayoutPosition(), true);
            return true;
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            mItemCategory = (TextView)itemView.findViewById(R.id.text_category);
        }

        public TextView getItemCategory() {
            return mItemCategory;
        }

        public void setItemCategory(TextView itemCategory) {
            mItemCategory = itemCategory;
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    private void selectDetail(int id) {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putInt(MovieDetailFragment.ARG_MOVIE_ID, id);
            args.putBoolean(MovieDetailFragment.ARG_SCREEN_TYPE, true);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_MOVIE_ID, id);
            intent.putExtra(MovieDetailFragment.ARG_SCREEN_TYPE, false);
            mContext.startActivity(intent);
        }
    }
}
