package controllers;

import data.Database;

import java.time.LocalDate;

import static interfaces.UserInterface.*;

public class MembersController {

    public MembersController() {
    }

    public void addMember() {
        displayMsg("Please enter name of member: ");
        String name = stringValidation("[a-zA-Z]+[^\\d]{1,40}", "Please input name, using no less than 1 and no more than 40 letters"); //"[a-zA-Z]+[^\\d]{1,40}"

        displayMsg("Please enter year of birth of member, in digits (1900-2020): ");
        int year = intValidation(2020,1900);
        displayMsg("Please enter month of birth of member, in digits (1-12): ");
        int month = intValidation(12,1);
        displayMsg("Please enter day of birth of member, in digits (1-31): ");
        int day = intValidation(31,1);

        LocalDate birthDate = LocalDate.of(year,month,day);

        displayMsg("Please indicate membership status: ");
        displayMsg("Active membership? True/False");
        boolean status = Boolean.parseBoolean(stringValidation("False|True|true|false", "You have to input False|True|true|false"));

        displayMsg("Is member a competitive swimmer?");
        displayMsg("Competitive swimmer? True/False");
        boolean isCompetitive = Boolean.parseBoolean(stringValidation("False|True|true|false", "You have to input False|True|true|false"));

        // Creates a CompetitiveMember if isCompetitive is true.
        if (isCompetitive) {
            // TODO Kan muligvis simplificeres til at parse alle korrekte kombinationer, fx "Please input all discplines member competes in: "Crawl,Butterfly".
            displayMsg("Does member compete in butterfly?");
            displayMsg("Butterfly? True/False");
            boolean butterfly = Boolean.parseBoolean(stringValidation("False|True|true|false", "You have to input False|True|true|false"));

            displayMsg("Does member compete in crawl?");
            displayMsg("crawl? True/False");
            boolean crawl = Boolean.parseBoolean(stringValidation("False|True|true|false","You have to input False|True|true|false"));

            displayMsg("Does member compete in backstroke?");
            displayMsg("backstroke? True/False");
            boolean backstroke = Boolean.parseBoolean(stringValidation("False|True|true|false","You have to input False|True|true|false"));

            displayMsg("Does member compete in breststroke?");
            displayMsg("breaststroke? True/False");
            boolean breaststroke = Boolean.parseBoolean(stringValidation("False|True|true|false","You have to input False|True|true|false"));

            Database.addMember(Database.createCompetitiveMember(birthDate, name, status, butterfly, crawl, backstroke, breaststroke));
        } else {
            Database.addMember(Database.createMember(birthDate, name, status));
        }
        displayMsg("\nEntry complete:\n---");
        displayMsg(Database.getMembers().get(Database.getMembers().size()-1).toString());

        Database.saveDatabase();
    }

    public void showAllMembers() {
        if (Database.getMembers().size() > 0) {
            displayMsg(Database.getToString());
        } else {
            displayMsg("Currently no members in the database.");
        }
    }
}
