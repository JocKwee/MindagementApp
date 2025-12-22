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

        // Top welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + user.fullName + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        // Left panel with buttons
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

        // Main panel for content
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Button actions
        btnAccount.addActionListener(e -> showAccountInfo());
        btnMedications.addActionListener(e -> showMedications());
        btnMedicalHistory.addActionListener(e -> showMedicalHistory());
        btnDoctors.addActionListener(e -> showDoctors());
        btnQnA.addActionListener(e -> showQnA());
        btnCalendar.addActionListener(e -> showCalendar());
        btnReminders.addActionListener(e -> showReminders());
        btnLogout.addActionListener(e -> {
            dispose(); // close dashboard
            // optionally return to login page
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
                info.append(mh.condition + " - " + mh.notes + "\n");
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
                info.append(d.name + " - " + d.specialty + "\n");
            }
        }
        mainPanel.add(new JScrollPane(info), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showQnA() {
        mainPanel.removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));

        JButton diseaseBtn = new JButton("Request Disease Info");
        JButton medicationBtn = new JButton("Request Medication Advice");

        diseaseBtn.addActionListener(e -> {
            String disease = JOptionPane.showInputDialog(this, "Enter disease name:");
            if (disease != null && !disease.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Request sent for disease: " + disease);
            }
        });

        medicationBtn.addActionListener(e -> {
            String med = JOptionPane.showInputDialog(this, "Enter medication name:");
            if (med != null && !med.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Request sent for medication: " + med);
            }
        });

        panel.add(diseaseBtn);
        panel.add(medicationBtn);

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
                info.append(e.date + " - " + e.title + "\n");
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
