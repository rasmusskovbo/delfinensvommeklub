package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

public class Member implements Serializable {
    // Core attributes
    private LocalDate birthDate;
    private String name;
    private boolean isActive;
    private boolean isSenior;
    private int balance;
    private boolean isCompetitive;
    private static int IDcount = 001;
    private int id;

    // TODO Kontingent værdier for fleksibel justering. Kan man sætte disse et andet sted? Evt ved at holde dem i treasurerhandler og give dem med ved getAnnualSubscription metodekald
    private double youthSubCost = 1000;
    private double seniorSubCost = 1600;
    private double elderlySubCost = Math.round(seniorSubCost*0.75);
    private double passiveSubCost = 500;
    private int belowIsJunior = 18;
    private int higherIsElderly = 60;

    public Member(LocalDate birthDate, String name, boolean isActive) {
        this.birthDate = birthDate;
        this.name = name;
        this.isActive = isActive;
        this.id = IDcount;
        this.isCompetitive = false;
        IDcount++;

        if (getAge()<belowIsJunior) {
            isSenior = false;
        } else {
            isSenior = true;
        }
        balance = 0;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        return period.getYears();
    }

    public double getMembershipFee() {
        if (!isActive) {
            return passiveSubCost;
        } else {
            if (getAge()<belowIsJunior) {
                return youthSubCost;
            } else if (getAge()>higherIsElderly) {
                return elderlySubCost;
            } else {
                return seniorSubCost;
            }
        }
    }

    public String toString() {
        String juniorSenior;
        if (isSenior) {
            juniorSenior = "Senior";
        } else {
            juniorSenior = "Junior";
        }
        return String.format("Name: "+name+"\nAge: "+getAge()+"\nActive membership: "+ isActive +"\nTeam age: "+juniorSenior+"\nCompetitive swimmer: "+isCompetitive()+"\nCurrent subscription balance: "+balance+"\n---\n");
    }

    // competitivemember methods
    public void addSwimResult(SwimResult sr) {
    }

    public void removeSwimResult(int i) {
    }

    public SwimResult getBestResult(String discipline) {
        return new SwimResult("1", "2", 1, LocalTime.of(13,37));
    }

    public ArrayList<SwimResult> getAllResults() {
        ArrayList<SwimResult> allResults = new ArrayList<>();
        return allResults;
    }

    // getters & setters
    public LocalDate getBirthdate() {
        return birthDate;
    }

    public void setBirthdate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getYouthSubCost() {
        return youthSubCost;
    }

    public void setYouthSubCost(long youthSubCost) {
        this.youthSubCost = youthSubCost;
    }

    public double getSeniorSubCost() {
        return seniorSubCost;
    }

    public void setSeniorSubCost(long seniorSubCost) {
        this.seniorSubCost = seniorSubCost;
    }

    public double getElderlySubCost() {
        return elderlySubCost;
    }

    public void setElderlySubCost(long elderlySubCost) {
        this.elderlySubCost = elderlySubCost;
    }

    public double getPassiveSubCost() {
        return passiveSubCost;
    }

    public void setPassiveSubCost(long passiveSubCost) {
        this.passiveSubCost = passiveSubCost;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }

    public void setYouthSubCost(double youthSubCost) {
        this.youthSubCost = youthSubCost;
    }

    public void setSeniorSubCost(double seniorSubCost) {
        this.seniorSubCost = seniorSubCost;
    }

    public void setElderlySubCost(double elderlySubCost) {
        this.elderlySubCost = elderlySubCost;
    }

    public void setPassiveSubCost(double passiveSubCost) {
        this.passiveSubCost = passiveSubCost;
    }

    public int getBelowIsJunior() {
        return belowIsJunior;
    }

    public void setBelowIsJunior(int belowIsJunior) {
        this.belowIsJunior = belowIsJunior;
    }

    public int getHigherIsElderly() {
        return higherIsElderly;
    }

    public void setHigherIsElderly(int higherIsElderly) {
        this.higherIsElderly = higherIsElderly;
    }

    public boolean isCompetitive() {
        return false;
    }

    public void setCompetitive(boolean competitive) {
    }
}
