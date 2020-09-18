package controllers;

import data.CompetitiveMember;
import data.Database;
import data.SwimResult;
import interfaces.UserInterface;

import java.time.LocalTime;
import java.util.ArrayList;

import static interfaces.UserInterface.*;

public class CompetitiveController {
    private static String currentDisciplines = "Butterfly|Crawl|Backstroke|Breaststroke";

    public CompetitiveController() {
    }

    public void addSwimResult() {
        displayMsg("Enter name of competitive swimmer: ");
        displayMsg(Database.getAllCompetitiveNames());
        String name = stringValidation(Database.getAllCompetitiveNames(), "You have to enter the name of a competitive svimmer.");

        displayMsg("Enter discpline: ");
        String choices = currentDisciplines;
        displayMsg(choices);
        String discipline = stringValidation(choices, "You have to input" + choices);

        displayMsg("Enter event name: ");
        String event = stringValidation("[a-zA-Z]+[^\\d]{1,40}", "Please input event name, using no less than 1 and no more than 40 letters");

        displayMsg("Enter placement: ");
        int placement = intValidation(1000,1);

        displayMsg("Enter minutes of time: ");
        int minutes = intValidation(1000,0);

        displayMsg("Enter seconds of time: ");
        int seconds = intValidation(60,0);

        SwimResult newSwimResult = new SwimResult(discipline,event,placement, LocalTime.of(0, minutes,seconds));

        // Adds name and SwimResult to corresponding member
        addSwimResultLoop(name, newSwimResult);

        Database.saveDatabase();

    }

    public void addSwimResultLoop(String name, SwimResult newSwimResult ) {
        for (int i = 0; i<Database.getMembers().size(); i++) {
            if (Database.getMembers().get(i).getName().equals(name) && Database.getMembers().get(i) instanceof CompetitiveMember) {
                newSwimResult.setOwner(name); //adds name to SwimResult for later print
                Database.getMembers().get(i).addSwimResult(newSwimResult);
                displayMsg(newSwimResult.toString());
                displayMsg("Swim result succesfully added to "+Database.getMembers().get(i).getName()+"'s data.");
            }
        }
    }

    public void showBestResult() {
        // Options for choosing discipline and age group
        displayMsg("Choose discipline: ");
        displayMsg(currentDisciplines);
        String discipline = stringValidation(currentDisciplines, "You have to input" + currentDisciplines);

        displayMsg("Junior or Senior?");
        String ageGroup = stringValidation("Junior|Senior|junior|senior", "You have to input False|True|true|false");
        boolean isSenior = true;
        if (ageGroup.equals("Junior") || ageGroup.equals("junior")) {
            isSenior = false;
        }

        // Filters members by chosen age group.
        ArrayList<CompetitiveMember> competitiveMembersSorted = Database.getCompetitiveMembersByAgeGroup(isSenior);

        // Creates arraylist to hold best swim results, then sorts according to chosen age group's best result in chosen discipline
        // Clears out to contain top 5 results only.
        ArrayList<SwimResult> bestSwimResults = sortAndClear(competitiveMembersSorted, discipline);

        // Displays Top 5, ranked by best time.
        if (bestSwimResults != null) {
            displayMsg(bestSwimResultsToString(bestSwimResults));
        }

    }

    public ArrayList<SwimResult> sortAndClear(ArrayList<CompetitiveMember> competitiveMembers, String discipline) {
        ArrayList<SwimResult> bestSwimResults = new ArrayList<>();
        for (int i = 0; i<competitiveMembers.size(); i++) {
            if(competitiveMembers.get(i).getAllResults().size() > 0) { // Checks whether member has a SwimResult before trying to retrieve.
                bestSwimResults.add(competitiveMembers.get(i).getBestResult(discipline));
            }
        }
        if (bestSwimResults.size() == 0) {
            UserInterface.displayMsg("No swim results found for discipline: "+discipline+".");
            return null;
        } else {
            bestSwimResults.sort(SwimResult::compareTo);

            if (bestSwimResults.size() > 5) {
                for (int i = bestSwimResults.size(); i > 5; i--) {
                    bestSwimResults.remove(i - 1);
                }
            }
            return bestSwimResults;
        }
    }

    public String bestSwimResultsToString(ArrayList<SwimResult> swimResults) {
        String print = "";
        for (int i = 0; i<swimResults.size(); i++) {
            print += swimResults.get(i).toString();
        }
        return print;
    }

    public String allResultsToString() {
        String print = "";
        for (int i = 0; i<Database.getMembers().size(); i++) {
            if (Database.getMembers().get(i) instanceof CompetitiveMember) {
                ArrayList<SwimResult> cmSwimResults = ((CompetitiveMember) Database.getMembers().get(i)).getAllResults();
                for (int i2 = 0; i2<cmSwimResults.size(); i2++) {
                    print += cmSwimResults.get(i2).toString();
                }
            }
        }
        return print;
    }

}
