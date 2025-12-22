package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String fullName;   
    public String email;
    public String birthday;
    public String gender;
    public String passwordHash;  

    public List<Doctor> doctors = new ArrayList<>();
    public List<Medication> medications = new ArrayList<>();
    public List<MedicalHistory> medicalHistory = new ArrayList<>();
    public List<Reminder> reminders = new ArrayList<>();
    public List<CalendarEvent> calendarEvents = new ArrayList<>();
}
