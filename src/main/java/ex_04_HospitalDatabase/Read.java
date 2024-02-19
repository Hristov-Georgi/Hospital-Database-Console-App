package ex_04_HospitalDatabase;

import ex_04_HospitalDatabase.entities.Medicament;
import ex_04_HospitalDatabase.validateData.ValidateData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    private final ValidateData validate;

    public Read(ValidateData validateData) {
        this.validate = validateData;
    }

    public String firstName(Scanner scanner) {
        System.out.println("Enter patient's first name:");
        String name = scanner.nextLine();

        if(!this.validate.patientName(name)) {
            return firstName(scanner);
        }
        return name;
    }

    public String lastName(Scanner scanner) {
        System.out.println("Enter patient's last name:");
        String name = scanner.nextLine();

        if(!this.validate.patientName(name)) {
            return lastName(scanner);
        }
        return name;
    }

    public String address(Scanner scanner) {
        System.out.println("Enter patient's address:");
        String address = scanner.nextLine();

        if(!validate.address(address)) {
            return address(scanner);
        }
        return address;
    }

    public String email(Scanner scanner) {
        System.out.println("Enter patient's email address:");
        String emailAddress = scanner.nextLine();

        if(!validate.email(emailAddress)) {
            return email(scanner);
        }
        return emailAddress;
    }

    public LocalDate date(Scanner scanner, String command) {
        if (command.equals("add patient")) {
            System.out.println("Enter patient's birth date");
        } else if (command.equals("add visitation")) {
            System.out.println("Enter visitation date");
        }

        String date = scanner.nextLine();
        if(!validate.date(date)) {
            return date(scanner, command);
        }
        int year = Integer.parseInt(date.split("\\.")[0]);
        int month = Integer.parseInt(date.split("\\.")[1]);
        int day = Integer.parseInt(date.split("\\.")[2]);
        return LocalDate.of(year, month, day);
    }

    public String picture(Scanner scanner) {
        System.out.println("Enter patient's picture path:");
        String path = scanner.nextLine();

        if(!validate.picture(path)) {
            return picture(scanner);
        }
        return path;
    }

    public char insurance(Scanner scanner) {
        System.out.println("Enter if patient has valid insurance (Y or N):");
        char symbol = scanner.nextLine().charAt(0);

        if(!validate.insurance(symbol)) {
            return insurance(scanner);
        }
        return symbol;
    }

    public String comment(Scanner scanner) {
        System.out.println("Enter comment");
        return scanner.nextLine();
    }

    public String diagnoseName(Scanner scanner) {
        System.out.println("Enter diagnose name:");
        return scanner.nextLine();
    }

    public List<Medicament> medicamentList(Scanner scanner) {
        System.out.println("Write the names of all medicaments prescribed for current diagnose," +
                " divided with comma and space (example: \"name\", \"name\"):");

        List<Medicament> medicamentsList = new ArrayList<>();

        try {
            String[] names = scanner.nextLine().split(",\\s+");

            for (String n : names) {
                Medicament med = new Medicament(n);
                medicamentsList.add(med);
            }

        } catch (Exception e){
            medicamentList(scanner);

        }
        return medicamentsList;
    }

}
