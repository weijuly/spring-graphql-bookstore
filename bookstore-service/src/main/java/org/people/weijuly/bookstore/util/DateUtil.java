package org.people.weijuly.bookstore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String format(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
