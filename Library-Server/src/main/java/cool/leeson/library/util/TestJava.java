package cool.leeson.library.util;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class TestJava {
    public static void main(String[] args) {
        System.out.println(new Date().getDate());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(c.getTime().getDate());
    }

    @Test
    public void timeTest() {
        // Date
        Date date = new Date();
        Date today = new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
        System.out.println(today + "" + today.getHours());
        // LocalTime
        System.out.println(LocalTime.of(0, 0, 0, 0));


    }
}
