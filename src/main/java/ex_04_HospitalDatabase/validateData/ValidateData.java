package ex_04_HospitalDatabase.validateData;

public interface ValidateData {

    boolean patientName(String name);

    boolean address(String address);

    boolean date(String date);

    boolean email(String email);

    boolean picture(String imagePath);

    boolean insurance(char letter);


}
