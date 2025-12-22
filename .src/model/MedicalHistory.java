package model;

import database.Database;

public class MedicalHistory {
    public String condition;
    public boolean genetic;
    public String diagnosisDate;
    public String treatment;
    public String notes;
    public MedicalHistory(String condition, String diagnosisDate, String treatment, String notes) {
        this.condition = condition;
        this.genetic = false; // idk
        this.diagnosisDate = diagnosisDate;
        this.treatment = treatment;
        this.notes = notes;
    }
}
