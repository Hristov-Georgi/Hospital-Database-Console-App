package ex_04_HospitalDatabase.validateData;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDataImpl implements ValidateData{

    @Override
    public boolean patientName(String name) {
        char firstSymbol = name.charAt(0);

        for(char n : name.toCharArray()) {
            if(Character.isDigit(n)) {
                System.out.println("Name should not contain digits");
                return false;
            }
        }

        if (firstSymbol < 65 || firstSymbol > 90) {
            System.out.println("Name should start with capital letter");
            return false;
        }

        if(name.length() < 2 || name.trim().isEmpty()) {
            System.out.println("Name should be at least two symbols long");
            return false;
        }

        return true;
    }

    @Override
    public boolean address(String address) {

        try {
            double n = Double.parseDouble(address);
            System.out.println("Address should have text part");
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @Override
    public boolean date(String date) {
        Pattern pattern = Pattern.compile("^\\d{4}.\\d{1,2}.\\d{1,2}$");
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            System.out.println("Enter date in format \"yyyy.M.d\" or \"yyyy.MM.dd\":");
            return false;
        }
        return true;
    }

    @Override
    public boolean email(String email) {
        Pattern pattern = Pattern.compile("^\\w+@\\w+.\\w{2,3}$");
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()) {
            System.out.println("Enter valid email address");
            return false;
        }
        return true;
    }

    @Override
    public boolean picture(String imagePath){
        File file = new File(imagePath);
        try {
            ImageIO.read(file);
            return true;
        } catch (IOException e) {
            System.out.println("Enter valid picture path (for example: C:\\Users\\Desktop\\IMG.jpg):");
            return false;
        }

    }

    @Override
    public boolean insurance(char letter) {
        if(letter != 'Y' && letter != 'N') {
            System.out.println("Enter Y (yes) or N (no)");
            return false;
        }

        return true;
    }
}
