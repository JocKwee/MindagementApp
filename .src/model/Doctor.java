package model;

import database.Database;

public class Doctor {
    public String name;
    public String specialty;
    public String contactInfo;

    public Doctor(String name, String specialty, String contactInfo) {
        this.name = name;
        this.specialty = specialty;
        this.contactInfo = contactInfo;
    }
}
