package data;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitiveMember extends Member {
    private ArrayList<SwimResult> swimResults = new ArrayList<SwimResult>();

    public CompetitiveMember(LocalDate birthDate, String name, boolean isActive) {
        super(birthDate, name, isActive);
    }

    public void addSwimResult(SwimResult sr) {
        swimResults.add(sr);
    }

    public void removeSwimResult(int i) {
        swimResults.remove(i);
    }

    public SwimResult getBestResult(String discipline) {
        ArrayList<SwimResult> bestResultsInDiscipline = new ArrayList<>();
        for (int i = 0; i<swimResults.size(); i++) {
            if (discipline.equals(swimResults.get(i).getDiscipline())) {
                bestResultsInDiscipline.add(swimResults.get(i));
            }
        }
        SwimResult sr = null;
        if (bestResultsInDiscipline.size()>0) {
            sr = bestResultsInDiscipline.get(0);
        }
        for (int i = 0; i<bestResultsInDiscipline.size(); i++) {
            if (bestResultsInDiscipline.get(i).getTime().isBefore(sr.getTime())) {
                sr = bestResultsInDiscipline.get(i);
            }
        }
        return sr;
    }

    public ArrayList<SwimResult> getAllResults() {
        ArrayList<SwimResult> allResults = new ArrayList<>();
        for (int i = 0; i<swimResults.size(); i++) {
            allResults.add(swimResults.get(0));
        }
        return allResults;
    }

    public String toString() {
        String juniorSenior;
        if (this.isSenior()) {
            juniorSenior = "Senior";
        } else {
            juniorSenior = "Junior";
        }
        return String.format("Name: "+this.getName()+"\nAge: "+getAge()+"\nActive membership: "+ this.isActive() +"\nTeam age: "+juniorSenior+"\nCompetitive swimmer: Yes\nCurrent subscription balance: "+this.getBalance()+"\n---\n");
    }

}
