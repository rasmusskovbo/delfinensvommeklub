package controllers;

import data.CompetitiveMember;
import data.Database;
import data.Member;
import data.SwimResult;
import interfaces.UserInterface;

import java.time.LocalTime;
import java.util.ArrayList;

public class CompetitiveController {
    UserInterface ui;
    Database database;
    private static String currentDisciplines = "Butterfly|Crawl|Backstroke|Breaststroke";

    public CompetitiveController(UserInterface ui, Database database) {
        this.ui = ui;
        this.database = database;
    }

    public void addSwimResult() {
        ui.displayMsg("Enter name of competitive swimmer: ");
        ui.displayMsg(database.getAllCompetitiveNames());
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

        SwimResult newSwimResult = new SwimResult(discipline,event,placement, LocalTime.of(0, minutes,seconds));
        ui.displayMsg(newSwimResult.toString());

        for (int i = 0; i<database.getMembers().size(); i++) {
            if (database.getMembers().get(i).getName().equals(name) && database.getMembers().get(i) instanceof CompetitiveMember) {
                database.getMembers().get(i).addSwimResult(newSwimResult);
                ui.displayMsg("Swim result succesfully added to "+database.getMembers().get(i).getName()+"'s data.");
            }
        }

        database.saveDatabase();

    }

    public void showBestResult() {
        // Options for choosing discipline and age group
        ui.displayMsg("Choose discipline: ");
        ui.displayMsg(currentDisciplines);
        String discipline = ui.stringValidation(currentDisciplines);

        ui.displayMsg("Junior or Senior?");
        String ageGroup = ui.stringValidation("Junior|Senior|junior|senior");
        boolean isSenior = true;
        if (ageGroup.equals("Junior") || ageGroup.equals("junior")) {
            isSenior = false;
        }

        // Filters members by age group.
        ArrayList<CompetitiveMember> competitiveMembersSorted = database.getCompetitiveMembersByAgeGroup(isSenior);

        // Creates arraylist to hold best swim results
        ArrayList<SwimResult> bestSwimResults = new ArrayList<>();

        // Adds all competitive members from chosen age group's best result in chosen discipline.
        for (int i = 0; i<competitiveMembersSorted.size(); i++) {
            bestSwimResults.add(competitiveMembersSorted.get(i).getBestResult(discipline));
        }

        // Test kald
        System.out.print(bestSwimResults);

        // TODO Sorter efter top tider via CompareTo
        // TODO Slet alt andet end top 5 tider i ArrayList
        // TODO Display top 5 tider

    }
}
