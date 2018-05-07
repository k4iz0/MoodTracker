package ltd.kaizo.moodtracker.controller.Adapter;

/**
 * The type Date manager.
 */
public class DateManager {
    /**
     * The Day.
     */
    int day;
    /**
     * The Month.
     */
    int month;
    /**
     * The Year.
     */
    int year;


    /**
     * Instantiates a new Date manager.
     * extract day, month and year from a simpleDateFormat dd-MM-YY
     *
     * @param date the date
     */
    public DateManager(String date) {
        String[] str = date.split("-");
        day = Integer.parseInt(str[0]);
        month = Integer.parseInt(str[1]);
        year = Integer.parseInt(str[2]);
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }
}
