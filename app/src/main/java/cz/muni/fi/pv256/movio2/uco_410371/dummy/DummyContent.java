package cz.muni.fi.pv256.movio2.uco_410371.dummy;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio2.uco_410371.models.Movie;

/**
 * Dummy data
 * Created by Benjamin Varga on 6.10.2016.
 */

public class DummyContent {

    public static final List<Movie> MOVIES = new ArrayList<>();
    public static final List<Object> ITEMS = new ArrayList<>();
    public static final List<Object> EMPTY = new ArrayList<>();

    static {
        MOVIES.add(new Movie(1, 1477395448L, "/xfWac8MTYDxujaxgPVcRD9yZaul.jpg", "Doctor Strange", "/yqyZLEfSiSeqmn5oRahbOUTUHd9.jpg", 0f));
        MOVIES.add(new Movie(2, 1477568248L, "/ueu1Mo2XRZ2C2aBisP30e1d5SmA.jpg", "Rings", "/91WPDonXsxRzi7AcfedKM3p3NFU.jpg", 0f));
        MOVIES.add(new Movie(3, 1473853048L, "/z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg", "The Magnificent Seven", "/T3LrH6bnV74llVbFpQsCBrGaU9.jpg", 4.59f));
        MOVIES.add(new Movie(4, 1461757048L, "/5N20rQURev5CNDcMjHVUZhpoCNC.jpg", "Captain America: Civil War", "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg", 6.78f));

        ITEMS.add("Upcoming Movies");
        ITEMS.add(new Movie(1, 1477395448L, "/xfWac8MTYDxujaxgPVcRD9yZaul.jpg", "Doctor Strange", "/yqyZLEfSiSeqmn5oRahbOUTUHd9.jpg", 0f));
        ITEMS.add(new Movie(2, 1477568248L, "/ueu1Mo2XRZ2C2aBisP30e1d5SmA.jpg", "Rings", "/91WPDonXsxRzi7AcfedKM3p3NFU.jpg", 0f));
        ITEMS.add("Popular Movies");
        ITEMS.add(new Movie(3, 1473853048L, "/z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg", "The Magnificent Seven", "/T3LrH6bnV74llVbFpQsCBrGaU9.jpg", 4.59f));
        ITEMS.add(new Movie(4, 1461757048L, "/5N20rQURev5CNDcMjHVUZhpoCNC.jpg", "Captain America: Civil War", "/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg", 6.78f));
    }
}
