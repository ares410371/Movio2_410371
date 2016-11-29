package cz.muni.fi.pv256.movio2.uco_410371.dummy;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.model.Movie;

/**
 * Dummy data
 * Created by Benjamin Varga on 6.10.2016.
 */

public class DummyContent {

    public static final List<Movie> MOVIES = new ArrayList<>();

    static {
        MOVIES.add(new Movie(1, 69465600000L, "/d4KNaTrltq6bpkFS01pYtyXa09m.jpg", "The Godfather", "/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg", 5.451023f));
        MOVIES.add(new Movie(2, 1216166400000L, "/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg", "The Dark Knight", "/nnMC0BM6XbjIIrT4miYmMtPGcQV.jpg", 8.071095f));
        MOVIES.add(new Movie(3, 773452800000L, "/z4ROnCrL77ZMzT0MsNXY5j25wS2.jpg", "Forrest Gump", "/ctOEhQiFIHWkiaYp7b0ibSTe5IL.jpg", 4.236889f));
        MOVIES.add(new Movie(4, 1473811200000L, "/z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg", "The Magnificent Seven", "/g54J9MnNLe7WJYVIvdWTeTIygAH.jpg", 39.203549f));
    }
}
