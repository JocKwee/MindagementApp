package model;

import java.util.ArrayList; 
import java.util.List;

public class User {
    public String email;
    public String fullName;
    public int age;
    public String gender;
    
    public List<Doctor> doctors = new ArrayList<>();
    public List<Medication> medications = new ArrayList<>();
    public List<MedicalHistory> medicalHistories = new ArrayList<>();
    public List<Reminder> reminders = new ArrayList<>();
    public List<CalendarEvent> calendarEvents = new ArrayList<>();

    public User(String fullName) {
        this.fullName = fullName;
    }

    public User(String fullName, String email, int age, String gender) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}
