package cz.muni.fi.pv256.movio2.uco_410371.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio2.uco_410371.R;

public class ImageUtils {

    public static void loadBackdropImage(Context context, String backdropPath, ImageView imageView) {
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w300" + backdropPath)
                .placeholder(R.drawable.placeholder_poster)
                .into(imageView);
    }

    public static void loadPosterImage(Context context, String posterPath, ImageView imageView) {
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500" + posterPath)
                .placeholder(R.drawable.placeholder_poster)
                .into(imageView);
    }
}
