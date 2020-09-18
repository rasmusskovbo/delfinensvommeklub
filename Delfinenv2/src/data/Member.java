package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Member implements Serializable {
    // Core attributes
    private LocalDate birthDate;
    private String name;
    private boolean isActive;
    private boolean isSenior;
    private int balance;

    // ID for possible implementation of ID based sorting, adding, removing
    private static int IDcount = 001;
    private int id;

    // Constructor
    public Member(LocalDate birthDate, String name, boolean isActive) {
        this.birthDate = birthDate;
        this.name = name;
        this.isActive = isActive;
        this.id = IDcount;
        IDcount++;

        if (getAge()<18) {
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
        return
    }

    public String toString() {
        String juniorSenior;
        if (isSenior) {
            juniorSenior = "Senior";
        } else {
            juniorSenior = "Junior";
        }
        return String.format("Name: "+name+"\nAge: "+getAge()+"\nActive membership: "+ isActive +"\nTeam age: "+juniorSenior+"\nCompetitive swimmer: No\nCurrent subscription balance: "+balance+"\n---\n");
    }

    // Defined for CompetitiveMembers
    public void addSwimResult(SwimResult sr) {
    }

    public void removeSwimResult(int i) {
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

    public void setCompetitive(boolean competitive) {
    }
}
