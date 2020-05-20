package controllers;

import data.Database;
import interfaces.UserInterface;

import java.time.LocalDate;

public class MembersController {
    UserInterface ui;
    Database database;

    public MembersController(UserInterface ui, Database database) {
        this.ui = ui;
        this.database = database;
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

        // Creates a CompetitiveMember if isCompetitive is true.
        if (isCompetitive) {
            database.addMember(database.createCompetitiveMember(birthDate, name, status));
        } else {
            database.addMember(database.createMember(birthDate, name, status));
        }
        ui.displayMsg("\nEntry complete:\n---");
        ui.displayMsg(database.getMembers().get(database.getMembers().size()-1).toString());
    }

    public void showAllMembers() {
        if (database.getMembers().size() > 0) {
            ui.displayMsg(database.toString());
        } else {
            ui.displayMsg("Currently no members in the database.");
        }
    }
}
