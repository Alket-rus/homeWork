package view;

import model.Family.Gender;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public LocalDate getDateInput(String prompt) {
        System.out.println(prompt);
        return LocalDate.parse(scanner.nextLine());
    }

    public Gender getGenderInput(String prompt) {
        System.out.println(prompt);
        return parseGender(scanner.nextLine());
    }

    private Gender parseGender(String genderInput) {
        if (genderInput.equalsIgnoreCase("Мужской")) {
            return Gender.Мужской;
        } else if (genderInput.equalsIgnoreCase("Женский")) {
            return Gender.Женский;
        } else {
            throw new IllegalArgumentException("Некорректный ввод пола: " + genderInput);
        }
    }
}
