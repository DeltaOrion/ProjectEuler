package me.deltaorion.euler;

public class ProblemNineteen {

    private static int[] months;

    public static void main(String[] args) {
        months = new int[]{
                31,
                28,
                31,
                30,
                31,
                30,
                31,
                31,
                30,
                31,
                30,
                31
        };

        long before = System.nanoTime();
        System.out.println(getSundays());
        long after = System.nanoTime();
        System.out.println("No Optimization: "+(after-before));
        before = System.nanoTime();
        System.out.println(getSundaysOpti());
        after = System.nanoTime();
        System.out.println("Optimization: "+(after-before));


    }

    /*
     * Time Complexity = T(N/28) where n is the amount of days
     */
    private static int getSundaysOpti() {
        int year = 0;
        int day = 0;
        int month = 1;
        int sundays = 0;
        while (true) {
            day += getMonthDays(month,year);
            if(year>100)
                return sundays;

            if(day%7==6) {
                System.out.println(day);
            }

            if(year>=1) {
                if (day % 7 == 6)
                    sundays++;
            }

            month++;
            if(month>12) {
                month = 1;
                year++;
            }
        }

    }

    /**
     * T = N/7 where n is the number of days between 1900 and 2000
     */
    private static int getSundays() {
        int day = 0;
        int month = 1;
        int sunday = 0;
        int year = 0;
        while (true) {
            day = day+7;
            int monthDays = getMonthDays(month,year);
            while(day > monthDays) {
                day = day - monthDays;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                    if(year>100)
                        return sunday;
                }
                monthDays = getMonthDays(month,year);
            }

            if(day==1)
                if(year>=1)
                    sunday++;

        }

    }

    private static int getMonthDays(int month, int year) {
        year = year + 1900;
        if(month==2 && leapYear(year)) {
            return 29;
        }
        return months[month-1];
    }

    private static boolean leapYear(int year) {
        if(year%400 == 0)
            return true;

        if(year%100 == 0)
            return false;

        return year % 4 == 0;
    }
}
