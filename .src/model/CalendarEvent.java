package model;

public class CalendarEvent {
    public String title;
    public String date;
    public String time;
    public String description;

    public CalendarEvent(String title, String date, String time, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
    }
}
