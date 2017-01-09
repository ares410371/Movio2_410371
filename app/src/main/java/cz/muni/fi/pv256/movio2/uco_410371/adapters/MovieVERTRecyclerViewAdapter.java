package cz.muni.fi.pv256.movio2.uco_410371.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.MovieDetailActivity;
import cz.muni.fi.pv256.movio2.uco_410371.MovieDetailFragment;
import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.ItemClickListener;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

/**
 * Created by Benjamin Varga on 22.11.2016.
 */

public class MovieVERTRecyclerViewAdapter extends RecyclerView.Adapter<MovieVERTRecyclerViewAdapter.MovieViewHolder>{

    private List<Movie> mMovies;
    private Context mContext;
    private boolean mTwoPane;

    public MovieVERTRecyclerViewAdapter(Context context, List<Movie> movies, boolean twoPane) {
        mMovies = movies;
        mContext = context;
        mTwoPane = twoPane;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public MovieVERTRecyclerViewAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_vert_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        configureMovieViewHolder(holder, position);
    }

    private void configureMovieViewHolder(MovieViewHolder movieViewHolder, int position) {
        final Movie movie = mMovies.get(position);

        if (movie != null) {
            movieViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {
                        Toast.makeText(mContext, "Long click on " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    } else {
                        selectDetail(movie);
                    }
                }
            });

            Picasso.with(mContext)
            .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                    .placeholder(R.drawable.placeholder_poster)
                    .into(movieViewHolder.getItemPoster());

            movieViewHolder.getItemTitle().setText(movie.getTitle());
            movieViewHolder.getItemRating().setText(String.format("%.2f", movie.getPopularity()));
        }
    }

    private void selectDetail(Movie movie) {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailFragment.ARG_MOVIE, movie);
            args.putBoolean(MovieDetailFragment.ARG_SCREEN_TYPE, true);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_MOVIE, movie);
            intent.putExtra(MovieDetailFragment.ARG_SCREEN_TYPE, false);
            mContext.startActivity(intent);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        private ImageView mItemPoster;
        private TextView mItemTitle;
        private TextView mItemRating;
        private ItemClickListener mItemClickListener;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemPoster = (ImageView)itemView.findViewById(R.id.image_item_movie_vert_poster);
            mItemTitle = (TextView)itemView.findViewById(R.id.text_movie_vert_title);
            mItemRating = (TextView)itemView.findViewById(R.id.text_movie_vert_rating);

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
}