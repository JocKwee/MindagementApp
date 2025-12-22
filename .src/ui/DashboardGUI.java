package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardGUI extends JFrame {

    private User user;
    private JPanel mainPanel;

    public DashboardGUI(User user) {
        this.user = user;
        setTitle("Dashboard - " + user.fullName);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + user.fullName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 5, 5));

        JButton btnAccount = new JButton("Account Info");
        JButton btnMedications = new JButton("Medications");
        JButton btnMedicalHistory = new JButton("Medical History");
        JButton btnDoctors = new JButton("Doctors");
        JButton btnQnA = new JButton("Q&A / Requests");
        JButton btnCalendar = new JButton("Calendar");
        JButton btnReminders = new JButton("Reminders");
        JButton btnLogout = new JButton("Logout");

        buttonPanel.add(btnAccount);
        buttonPanel.add(btnMedications);
        buttonPanel.add(btnMedicalHistory);
        buttonPanel.add(btnDoctors);
        buttonPanel.add(btnQnA);
        buttonPanel.add(btnCalendar);
        buttonPanel.add(btnReminders);
        buttonPanel.add(btnLogout);

        add(buttonPanel, BorderLayout.WEST);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        btnAccount.addActionListener(e -> showAccountInfo());
        btnMedications.addActionListener(e -> showMedications());
        btnMedicalHistory.addActionListener(e -> showMedicalHistory());
        btnDoctors.addActionListener(e -> showDoctors());
        btnQnA.addActionListener(e -> showQnA());
        btnCalendar.addActionListener(e -> showCalendar());
        btnReminders.addActionListener(e -> showReminders());
        btnLogout.addActionListener(e -> {
            dispose(); 
        });

        setVisible(true);
    }

    private void showAccountInfo() {
        mainPanel.removeAll();
        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.append("Full Name: " + user.fullName + "\n");
        info.append("Email: " + user.email + "\n");
        info.append("Age: " + user.age + "\n");
        info.append("Gender: " + user.gender + "\n");
        mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showMedications() {
        mainPanel.removeAll();
        JTextArea info = new JTextArea();
        info.setEditable(false);
        List<Medication> meds = user.medications;
        if (meds == null || meds.isEmpty()) {
            info.append("No medications recorded.\n");
        } else {
            for (Medication med : meds) {
                info.append(med.name + " - " + med.dosage + " - " + med.frequency + "\n");
            }
        }
        mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

private void showMedicalHistory() {
    mainPanel.removeAll();
    JTextArea info = new JTextArea();
    info.setEditable(false);
    List<MedicalHistory> history = user.medicalHistory;
    if (history == null || history.isEmpty()) {
        info.append("No medical history recorded.\n");
    } else {
        for (MedicalHistory mh : history) {
            info.append("Condition: " + mh.condition + "\n");
            info.append("Genetic: " + (mh.genetic ? "Yes" : "No") + "\n");
            info.append("Diagnosis Date: " + mh.diagnosisDate + "\n");
            info.append("Treatment: " + mh.treatment + "\n");
            info.append("Notes: " + mh.notes + "\n");
            info.append("---------------------\n");
        }
    }
    mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
    mainPanel.revalidate();
    mainPanel.repaint();
}


private void showDoctors() {
    mainPanel.removeAll();
    JTextArea info = new JTextArea();
    info.setEditable(false);
    List<Doctor> doctors = user.doctors;
    if (doctors == null || doctors.isEmpty()) {
        info.append("No doctors added yet.\n");
    } else {
        for (Doctor d : doctors) {
            info.append("Name: " + d.name + "\n");
            info.append("Specialty: " + d.specialty + "\n");
            info.append("Contact: " + d.contactInfo + "\n");
            info.append("---------------------\n");
        }
    }
    mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
    mainPanel.revalidate();
    mainPanel.repaint();
}

private void showQnA() {
    mainPanel.removeAll();
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1, 5, 5));

    JButton openFormBtn = new JButton("Submit Q&A / Requests");

    openFormBtn.addActionListener(e -> {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(
                "https://docs.google.com/forms/d/e/1FAIpQLSfD_oeOQ0KFKUSl3MhmD7lzxCNxfofoMc7YDliDH8JSJFirdg/viewform?usp=publish-editor"
            ));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to open the link.");
            ex.printStackTrace();
        }
    });

    panel.add(openFormBtn);
    mainPanel.add(panel, BorderLayout.CENTER);
    mainPanel.revalidate();
    mainPanel.repaint();
}

private void showCalendar() {
    mainPanel.removeAll();
    JTextArea info = new JTextArea();
    info.setEditable(false);
    List<CalendarEvent> events = user.calendarEvents;
    if (events == null || events.isEmpty()) {
        info.append("No calendar events.\n");
    } else {
        for (CalendarEvent e : events) {
            info.append("Date: " + e.date + "\n");
            info.append("Time: " + e.time + "\n");
            info.append("Title: " + e.title + "\n");
            info.append("Description: " + e.description + "\n");
            info.append("---------------------\n");
        }
    }
    mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
    mainPanel.revalidate();
    mainPanel.repaint();
}

    private void showReminders() {
        mainPanel.removeAll();
        JTextArea info = new JTextArea();
        info.setEditable(false);
        List<Reminder> reminders = user.reminders;
        if (reminders == null || reminders.isEmpty()) {
            info.append("No reminders set.\n");
        } else {
            for (Reminder r : reminders) {
                info.append(r.text + " - " + r.dateTime + "\n");
            }
        }
        mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
