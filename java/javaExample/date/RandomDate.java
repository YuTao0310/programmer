package date;
import java.util.Date;

public class RandomDate {
    static Date getRandomDate(int start, int end) {
        long startSec = (calculateYearDay(start) * 24 - 8) * 3600;
        long endSec = (calculateYearDay(end) * 24 - 8) * 3600;
        long randomMs = (long)(Math.floor(Math.random() * (endSec - startSec) + startSec)) * 1000;
        return (new Date(randomMs));
    }
    static Date getFirstDate(int year) {
        long yearMs = (calculateYearDay(year) * 24 - 8) * 3600 * 1000;
        return (new Date(yearMs));
    }
    static Date getLastDate(int year) {
        long yearMs = ((calculateYearDay(year + 1) * 24 - 8) * 3600 - 1)* 1000;
        return (new Date(yearMs));
    }
    private static long calculateYearDay(int year) {
        long sum = 0L;
        if (year >= 1970) {
            for (int each = 1970; each < year; each++) {
                if (isLeapYear(each)) {
                    sum += 366;
                } else {
                    sum += 365;
                }
            }
        } else {
            for (int each = 1969; each >= year; each--) {
                if (each == 1582) { //注意1582年只有355天
                    sum -= 355;
                } else if (isLeapYear(each)) {
                    sum -= 366;
                } else {
                    sum -= 365;
                }
            }
        }
        return sum;
    }
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static void main(String[] args) {
        int year = 1582;
        int start = year;
        int end = year + 1;
        Date d1 = getFirstDate(year);
        Date d2 = getLastDate(year);
        Date d3 = getRandomDate(start, end);
        System.out.println(year + "年的第一天：" + d1);
        System.out.println(year + "年的最后一天：" + d2);
        System.out.println(year + "年的随机一天：" + d3);
    }
}
