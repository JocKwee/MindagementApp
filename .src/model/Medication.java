package model;

import database.Database;

public class Medication {
    public String name;
    public String type;
    public String dosage;
    public String frequency;
    public String prescribingDoctor;
    public Medication(String name, String type, String dosage, String frequency, String prescribingDoctor) {
        this.name = name;
        this.type = type;
        this.dosage = dosage;
        this.frequency = frequency;
        this.prescribingDoctor = prescribingDoctor;
    }
}
