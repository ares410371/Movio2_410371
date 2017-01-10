package cz.muni.fi.pv256.movio2.uco_410371.movies.adapters;

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

import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.moviedetail.MovieDetailActivity;
import cz.muni.fi.pv256.movio2.uco_410371.moviedetail.MovieDetailFragment;
import cz.muni.fi.pv256.movio2.uco_410371.R;
import cz.muni.fi.pv256.movio2.uco_410371.ItemClickListener;
import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;
import cz.muni.fi.pv256.movio2.uco_410371.util.ActivityUtils;
import cz.muni.fi.pv256.movio2.uco_410371.util.ImageUtils;

public class MoviesHorizontalRVAdapter extends RecyclerView.Adapter<MoviesHorizontalRVAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private Context mContext;
    private boolean mTwoPane;

    public MoviesHorizontalRVAdapter(Context context, List<Movie> movies, boolean twoPane) {
        mMovies = movies;
        mContext = context;
        mTwoPane = twoPane;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_movie, parent, false);
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
            ImageUtils.loadPosterImage(mContext, movie.getPosterPath(), movieViewHolder.getItemPoster());
            movieViewHolder.getItemTitle().setText(movie.getTitle());
        }
    }

    private void selectDetail(Movie movie) {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailFragment.ARG_MOVIE, movie);
            args.putBoolean(MovieDetailFragment.ARG_SCREEN_TYPE, true);
            MovieDetailFragment fragment = MovieDetailFragment.newInstance();
            fragment.setArguments(args);
            ActivityUtils.replaceFragmentInActivity(((AppCompatActivity)mContext).getSupportFragmentManager(),
                    fragment, R.id.movie_detail_container);
        } else {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_MOVIE, movie);
            intent.putExtra(MovieDetailFragment.ARG_SCREEN_TYPE, false);
            mContext.startActivity(intent);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private ImageView mItemPoster;
        private TextView mItemTitle;

        private ItemClickListener mItemClickListener;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemPoster = (ImageView)itemView.findViewById(R.id.image_item_movie_poster);
            mItemTitle = (TextView)itemView.findViewById(R.id.text_movie_title);

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
