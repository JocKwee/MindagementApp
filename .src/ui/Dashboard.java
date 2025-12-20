package ui;

import java.util.Scanner; 
import model.User;

public class Dashboard {
    private User user;
    private Scanner scanner = new Scanner(System.in);

    public Dashboard(User user) {
        this.user = user;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== DASHBOARD ===");
            System.out.println("Welcome, " + user.fullName + "!");
            System.out.println("1. Personal Info");
            System.out.println("2. Past Doctors");
            System.out.println("3. Medications");
            System.out.println("4. Medical History");
            System.out.println("5. Reminders");
            System.out.println("6. Calendar");
            System.out.println("7. Logout");

            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showPersonalInfo();
                case 2 -> showDoctors();
                case 3 -> showMedications();
                case 4 -> showMedicalHistory();
                case 5 -> showReminders();
                case 6 -> showCalendar();
                case 7 -> {
                    System.out.println("Logging out...");
                    return;
                }
            }
        }

        private void showPersonalInfo() {
        System.out.println("\n--- Personal Info ---");
        System.out.println("Full Name: " + user.fullName);
        System.out.println("Email: " + user.email);
        System.out.println("Age: " + user.age); 
        }

        private void manageDoctors() {
            System.out.println("--- Past Doctors ---"); // not done
        }

        private void manageMedications() {
            System.out.println("\n--- Medications ---"); // not done
        }

        private void manageMedicalHistory() {
            System.out.println("\n--- Medical History ---"); // not done
        }

        private void manageReminders() {
            System.out.println("\n--- Reminders ---"); // not done
        }

        private void manageCalendar() {
            System.out.println("\n--- Calendar ---"); // not done
        }
    }
}
