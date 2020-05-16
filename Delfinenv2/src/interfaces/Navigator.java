package interfaces;

import data.Database;
import data.Member;
import data.SwimResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Navigator {
    UserInterface ui = new UserInterface();
    Database database = new Database();
    private static String currentDisciplines = "Butterfly|Crawl|Backstroke|Breaststroke";

    public Navigator() {

        database.loadDatabase();
        menu();

    }

    public void menu() {

        while (true) {
            ui.displayMsg("--Main Menu--");
            ui.displayMsg("1. Manage members");
            ui.displayMsg("2. Treasurer Options");
            ui.displayMsg("3. Competitive Results");
            ui.displayMsg("4. Exit");

            menuOptions();
        }

    }

    public void menuOptions() {

        switch(ui.intValidation(4,1)) {

            case 1:
                manageMembersMenu();
                ui.enterToReturn();
                break;

            case 2:
                treasuryMenu();
                ui.enterToReturn();
                break;

            case 3:
                competitiveResultsMenu();
                ui.enterToReturn();
                break;

            case 4:
                System.exit(0);

        }

    }

    public void manageMembersMenu() {
        ui.displayMsg("--Manage Members--");
        ui.displayMsg("1. Add member");
        ui.displayMsg("2. Show current members");
        ui.displayMsg("3. Exit");

        switch(ui.intValidation(3,1)) {
            case 1:
                addMember();
                break;

            case 2:
                ui.displayMsg(database.toString());
                break;

            case 3:
                ui.enterToReturn();
                break;
        }
    }

    public void treasuryMenu() {
        ui.displayMsg("--Treasurer Options--");
        ui.displayMsg("1. Display total subcription data");
        ui.displayMsg("2. Display all current members in arrear");
        ui.displayMsg("3. Exit");

        switch(ui.intValidation(3,1)) {
            case 1:
                ui.displayMsg("Total subcription amount: "+getTotalSubscriptions());
                break;

            case 2:
                ui.displayMsg("List of arrears: "+ArrearsToString());
                break;

            case 3:
                ui.enterToReturn();
                break;
        }
    }

    public void competitiveResultsMenu() {
        ui.displayMsg("--Competitive Results--");
        ui.displayMsg("1. Add result to member");
        ui.displayMsg("2. Show best results");
        ui.displayMsg("3. Show all results");
        ui.displayMsg("4. Exit");

        switch(ui.intValidation(4,1)) {
            case 1:
                addSwimResult();
                break;

            case 2:
                showBestResult();
                break;

            case 3:
                ArrayList<Member> memberz = database.getAllCompetitiveMembers(false, true);
                System.out.print(memberz.get(0).getAllResults());

            case 4:
                ui.enterToReturn();
                break;
        }
    }

    public void addMember() {
        ui.displayMsg("Please enter name of member: ");
        String name = ui.stringValidation("[a-zA-Z]+[^\\d]{1,40}"); //"[a-zA-Z]+[^\\d]{1,40}"

        ui.displayMsg("Please enter year of birth of member, in digits (1900-2020): ");
        int year = ui.intValidation(2020,1900);
        ui.displayMsg("Please enter month of birth of member, in digits (1-12): ");
        int month = ui.intValidation(12,1);
        ui.displayMsg("Please enter day of birth of member, in digits (1-31): ");
        int day = ui.intValidation(31,1);

        LocalDate birthDate = LocalDate.of(year,month,day);

        ui.displayMsg("Please indicate membership status: ");
        ui.displayMsg("Active membership? True/False");
        boolean status = Boolean.parseBoolean(ui.stringValidation("False|True|true|false"));

        ui.displayMsg("Is member a competitive swimmer?");
        ui.displayMsg("Active membership? True/False");
        boolean isCompetitive = Boolean.parseBoolean(ui.stringValidation("False|True|true|false"));

        // TODO evt confirm details
        // Laver et competitivemember i stedet for alm hvis competitive er true
        if (isCompetitive) {
            database.addMember(database.createMember(birthDate, name, status, true));
            ui.displayMsg(database.getMembers().get(0).toString());
        } else {
            database.addMember(database.createMember(birthDate, name, status, false));
            ui.displayMsg(database.getMembers().get(0).toString());
        }
    }

    // Returner totalkontingent for alle medlemmer
    public long getTotalSubscriptions() {

        long total = 0;

        for (int i = 0; i<database.getMembers().size(); i++) {
            total += database.getMembers().get(i).getMembershipFee();
        }

        return total;
    }

    // Returner ArrayList med alle medlemmer der pt er i restance.
    public ArrayList<Member> getArrears() {

        ArrayList<Member> members = new ArrayList<>();

        for (int i = 0; i<database.getMembers().size(); i++) {
            if (database.getMembers().get(i).getBalance() < 0) {
                members.add(database.getMembers().get(i));
            }
        }

        return members;
    }

    // ToString metode for alle i restance
    public String ArrearsToString() {

        ArrayList<Member> arrears = getArrears();
        String list = "";

        for (int i = 0; i<arrears.size(); i++) {
            if (arrears.get(i).getBalance()<0) {
                list += "\n"+arrears.get(i).getName() + ", "+arrears.get(i).getAge()+", Currently owes: "+arrears.get(i).getBalance();
            }
        }

        return list;
    }

    public void addSwimResult() {
        ui.displayMsg("Enter name of competitive swimmer: ");
        ui.displayMsg(database.getAllCompetitiveNames()); // competitive only
        String name = ui.stringValidation(database.getAllCompetitiveNames());

        ui.displayMsg("Enter discpline: ");
        String choices = currentDisciplines;
        ui.displayMsg(choices);
        String discipline = ui.stringValidation(choices);

        ui.displayMsg("Enter event name: ");
        String event = ui.stringValidation("[a-zA-Z]+[^\\d]{1,40}");

        ui.displayMsg("Enter placement: ");
        int placement = ui.intValidation(1000,1);

        ui.displayMsg("Enter minutes of time: ");
        int minutes = ui.intValidation(1000,0);

        ui.displayMsg("Enter seconds of time: ");
        int seconds = ui.intValidation(60,0);

        SwimResult newSR = new SwimResult(discipline,event,placement,LocalTime.of(0, minutes,seconds));
        ui.displayMsg(newSR.toString());

        for (int i = 0; i<database.getMembers().size(); i++) {
            if (database.getMembers().get(i).getName().equals(name)) {
                database.getMembers().get(i).addSwimResult(newSR);
                ui.displayMsg("Swim result succesfully added to "+database.getMembers().get(i).getName()+"'s data.");
            }
        }

        database.saveDatabase();

    }

    public void showBestResult() {
        ui.displayMsg("Choose discipline: ");
        ui.displayMsg(currentDisciplines);
        String discipline = ui.stringValidation(currentDisciplines);

        ui.displayMsg("Junior or Senior?");
        String ageGroup = ui.stringValidation("Junior|Senior|junior|senior");
        boolean isSenior = true;
        if (ageGroup.equals("Junior") || ageGroup.equals("junior")) {
            isSenior = false;
        }

        ArrayList<Member> srmembers = database.getAllCompetitiveMembers(isSenior, false);
        ArrayList<SwimResult> srBest = new ArrayList<>();
        for (int i = 0; i<srmembers.size(); i++) {
            srBest.add(srmembers.get(i).getBestResult(discipline));
        }

        ui.displayMsg(srBest.toString());

        // TODO Sorter efter top 5 tider via compareto på swimresult


    }
}
