package controllers;

import data.Database;
import data.Member;
import interfaces.UserInterface;

import java.util.ArrayList;

public class TreasurerController {
    UserInterface ui;
    Database database;

    private double youthSubCost = 1000;
    private double seniorSubCost = 1600;
    private double elderlySubCost = Math.round(seniorSubCost*0.75);
    private double passiveSubCost = 500;
    private int belowIsJunior = 18;
    private int higherIsElderly = 60;

    public TreasurerController(UserInterface ui, Database database) {
        this.ui = ui;
        this.database = database;
    }

    public long getTotalSubscriptions() {

        long total = 0;

        for (int i = 0; i<database.getMembers().size(); i++) {
            total += getMembershipFee(database.getMembers().get(i));
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

    // TODO
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

    public double getMembershipFee(Member member) {
        if (!member.isActive()) {
            return passiveSubCost;
        } else {
            if (member.getAge()<belowIsJunior) {
                return youthSubCost;
            } else if (member.getAge()>higherIsElderly) {
                return elderlySubCost;
            } else {
                return seniorSubCost;
            }
        }
    }

}
