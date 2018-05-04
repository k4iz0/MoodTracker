package ltd.kaizo.moodtracker.controller.Adapter;

public class DateManager {
    int day;
    int month;
    int year;


    public DateManager(String date) {
        String[] str = date.split("-");
        day = Integer.parseInt(str[0]);
        month = Integer.parseInt(str[1]);
        year = Integer.parseInt(str[2]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
