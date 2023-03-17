package cool.leeson.library.util;

import java.util.Calendar;
import java.util.Date;


public class Test {
    public static void main(String[] args) {
        System.out.println(new Date().getDate());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(c.getTime().getDate());
    }
}
