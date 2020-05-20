package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface {
    private Scanner in = new Scanner(System.in);
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Display functions
    public void displayMainMenu() {
        displayMsg("--Main Menu--");
        displayMsg("1. Manage members");
        displayMsg("2. Treasurer Options");
        displayMsg("3. Competitive Results");
        displayMsg("3. Exit");
    }

    public void displayMsg(String text){
        System.out.println(text);
    }

    // Input validation
    public int intValidation(int maxAmount, int minAmount) {
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

    public String stringValidation(String pattern) { // TODO impl String for variabel fejlmeddelelse
        String choice = "";
        boolean flag = true;
        do {
            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace(); //TODO fiks fejlmeddelsle
            }
            flag = choice.matches(pattern);
            if (!flag) displayMsg("Not a valid input, please try again.");
        } while (!flag);
        displayMsg("Input accepted: "+choice);
        return choice;
    }

    // Scanner functions
    public void enterToReturn() {
        displayMsg("Press enter to return to the main menu.");
        try {
            br.readLine(); // virker pt ikke
        } catch (Exception e) {

        }
    }

    public int nextInt(){
        return in.nextInt();
    }

    public boolean hasNextInt() {
        return in.hasNextInt();
    }

    public String nextLine(){
        return in.nextLine();
    }

    // Error display
    public void displayException(Exception e) {
        System.out.print(e);
    }

}

