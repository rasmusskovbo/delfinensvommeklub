package data;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitiveMember extends Member {
    private ArrayList<SwimResult> swimResults = new ArrayList<SwimResult>();
    private boolean competesInButterfly;
    private boolean competesInCrawl;
    private boolean competesInBackstroke;
    private boolean competesInBreaststroke;

    public CompetitiveMember(LocalDate birthDate, String name, boolean isActive, boolean butterfly, boolean crawl, boolean backstroke, boolean breaststroke) {
        super(birthDate, name, isActive);
        this.competesInButterfly = butterfly;
        this.competesInCrawl = crawl;
        this.competesInBackstroke = backstroke;
        this.competesInBreaststroke = breaststroke;
    }

    public void addSwimResult(SwimResult sr) {
        swimResults.add(sr);
    }

    public void removeSwimResult(int i) {
        swimResults.remove(i);
    }

    // Collects all SwimResults in the chosen discipline
    public SwimResult getBestResult(String discipline) {
        ArrayList<SwimResult> bestResultsInDiscipline = new ArrayList<>();
        for (int i = 0; i < swimResults.size(); i++) {
            if (discipline.equals(swimResults.get(i).getDiscipline())) {
                bestResultsInDiscipline.add(swimResults.get(i));
            }
        }
        SwimResult sr = null;
        if (bestResultsInDiscipline.size() > 0) {
            sr = bestResultsInDiscipline.get(0);
        }

        for (int i = 0; i < bestResultsInDiscipline.size(); i++) {
            if (bestResultsInDiscipline.get(i).getTime().isBefore(sr.getTime())) {
                sr = bestResultsInDiscipline.get(i);
            }
        }
        return sr;
    }

    public ArrayList<SwimResult> getAllResults() {
        return swimResults;
    }


    public String toString() {
        String juniorSenior;
        if (this.isSenior()) {
            juniorSenior = "Senior";
        } else {
            juniorSenior = "Junior";
        }
        return String.format("Name: " + this.getName() + "\nAge: " + getAge() + "\nActive membership: " + this.isActive() + "" +
                "\nTeam age: " + juniorSenior + "\nCompetitive swimmer: Yes" +
                "\nCompetes in:" +
                "\n Butterfly: " + competesInButterfly +
                "\n Crawl: " + competesInCrawl +
                "\n Backstroke: " + competesInBackstroke +
                "\n Breaststroke: " + competesInBreaststroke +
                "\nCurrent subscription balance: " + this.getBalance() + "\n---\n");

    }
}