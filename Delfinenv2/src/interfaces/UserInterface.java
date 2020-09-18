package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface {
    private static Scanner in = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Display functions
    public static void  displayMsg(String text){
        System.out.println(text);
    }

    // Input validation
    public static int intValidation(int maxAmount, int minAmount) {
        int choice = 0;
        while (true) {
            choice = 0; //reset valg efter hvert loop
            try {
                if (!in.hasNextInt()) {
                    in.nextLine();
                    throw new Exception("Not a valid input. Please enter a number: ");
                } else {
                    choice = in.nextInt();
                    if (choice > maxAmount || choice < 0 || choice < minAmount) {
                        in.nextLine();
                        throw new Exception("Not a valid input. Number must be higher than "+minAmount+" and equal to or lower than " + maxAmount +": ");
                    } else {
                        return choice;
                    }
                }
            } catch (Exception e) {
                displayException(e);
            }
        }
    }

    public static String stringValidation(String pattern, String errorMsg) {
        String choice = "";
        boolean flag = true;
        do {
            try {
                choice = br.readLine();
            } catch (IOException e) {
                System.out.print("Cannot read line.");
            }
            flag = choice.matches(pattern);
            if (!flag) displayMsg(errorMsg);
        } while (!flag);
        displayMsg("Input accepted: "+choice);
        return choice;
    }

    // Scanner functions
    public static void enterToReturn() {
        displayMsg("Press enter to return to the main menu.");
        try {
            br.readLine();
        } catch (Exception e) {

        }
    }

    // Error display
    public static void displayException(Exception e) {
        System.out.print(e);
    }

}

