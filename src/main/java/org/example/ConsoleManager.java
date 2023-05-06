package org.example;

import java.util.Scanner;

public class ConsoleManager implements IConsoleManager{

    private final Scanner scanner = new Scanner(System.in);
    
    public void WriteLine(String value) {
        System.out.println(value);
    }

    public String ReadLine() {
        return scanner.nextLine();
    }

    public Long ReadLong() {
        Long value = null;
        while (value == null) {
            try {
                value = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        return value;
    }

    public void Clear() {
        System.out.print("\n\n");
    }

}
