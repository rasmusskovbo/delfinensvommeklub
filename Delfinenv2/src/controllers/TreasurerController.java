package controllers;

import data.Database;
import data.Member;

import java.util.ArrayList;

public class TreasurerController {
    private double youthSubCost = 1000;
    private double seniorSubCost = 1600;
    private double elderlySubCost = Math.round(seniorSubCost*0.75);
    private double passiveSubCost = 500;
    private int belowIsJunior = 18;
    private int higherIsElderly = 60;

    public TreasurerController() {
    }

    // TODO implementer bedre læseligt printout
    public double getTotalSubscriptions() {

        double total = 0;

        for (int i = 0; i<Database.getMembers().size(); i++) {
            total += getMembershipFee(Database.getMembers().get(i));
        }

        return total;
    }

    // Returns ArrayList with all members currently in arrear.
    public ArrayList<Member> getArrears() {

        ArrayList<Member> members = new ArrayList<>();

        for (int i = 0; i<Database.getMembers().size(); i++) {
            if (Database.getMembers().get(i).getBalance() < 0) {
                members.add(Database.getMembers().get(i));
            }
        }

        return members;
    }

    public String arrearsToString() {

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
