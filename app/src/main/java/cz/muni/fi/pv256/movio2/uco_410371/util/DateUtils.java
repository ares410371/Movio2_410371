package cz.muni.fi.pv256.movio2.uco_410371.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

    public static String getFormattedDay(int days) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, days);
        return dateFormat.format(today.getTime());
    }
}
