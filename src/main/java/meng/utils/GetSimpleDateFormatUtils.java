package meng.utils;

import java.text.SimpleDateFormat;

public class GetSimpleDateFormatUtils {
    private static final ThreadLocal<SimpleDateFormat> SDF = new ThreadLocal();

    public static SimpleDateFormat getDateFormat() {

        SimpleDateFormat sdf = SDF.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            SDF.set(sdf);
        }

        return sdf;

    }

}
