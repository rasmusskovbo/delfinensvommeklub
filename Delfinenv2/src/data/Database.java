package data;

import interfaces.UserInterface;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database implements Serializable {
    private static ArrayList<Member> members = new ArrayList<>();
    private static File databaseFile = new File("src/data/database.txt");

    public static Member createMember(LocalDate birthDate, String name, boolean isActive) {
        return new Member(birthDate, name, isActive);
    }

    public static CompetitiveMember createCompetitiveMember(LocalDate birthDate, String name, boolean isActive, boolean butterfly, boolean crawl, boolean backstroke, boolean breaststroke) {
        return new CompetitiveMember(birthDate, name, isActive, butterfly, crawl, backstroke, breaststroke);
    }

    public static void addMember(Member member) {
        members.add(member);
        saveDatabase();
    }

    public static void removeMemberByIndex(int i) {
        members.remove(i);
        saveDatabase();
    }

    public static void removeMemberByObject(Member member) {
        members.remove(member);
        saveDatabase();
    }

    public static void saveDatabase() {
        try {
            FileOutputStream fos = new FileOutputStream(databaseFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(members);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadDatabase() {
        if (databaseFile!=null) {
            try {
                FileInputStream fis = new FileInputStream(databaseFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                members = (ArrayList<Member>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                UserInterface.displayMsg("Unable to find database file. Please check or create a new.");
            }
        }
    }

    // Returns String with names/options used for adding SwimResult
    public static String getAllCompetitiveNames() {
        String result = "";
        ArrayList<CompetitiveMember> competitiveMembers = getAllCompetitiveMembers();
        for (int i = 0; i<competitiveMembers.size(); i++) {
            result += competitiveMembers.get(i).getName();
            if (i<competitiveMembers.size()-1) {
                result += "|";
            }
        }
        return result;
    }

    public static ArrayList<CompetitiveMember> getCompetitiveMembersByAgeGroup(boolean isSenior) {
        ArrayList<CompetitiveMember> referenceList = getAllCompetitiveMembers();
        ArrayList<CompetitiveMember> competitiveMembers = new ArrayList<>();

        if (isSenior) {
            for (int i = 0; i < referenceList.size(); i++) {
                if (referenceList.get(i).isSenior()) {
                    competitiveMembers.add(referenceList.get(i));
                }
            }
        } else {
            for (int i = 0; i < referenceList.size(); i++) {
                if (!referenceList.get(i).isSenior()) {
                    competitiveMembers.add(referenceList.get(i));
                }
            }
        }
        return competitiveMembers;
    }

    // Looks through database for all competitive members and casts them into a new ArrayList.
    public static ArrayList<CompetitiveMember> getAllCompetitiveMembers() {
        ArrayList<CompetitiveMember> competitiveMembers = new ArrayList<>();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i) instanceof CompetitiveMember) {
                CompetitiveMember cm = (CompetitiveMember) members.get(i);
                competitiveMembers.add(cm);
            }
        }
        return competitiveMembers;
    }

    public static String getToString() {
        String result = "";
        for (int i = 0; i<members.size(); i++) {
            result += members.get(i).toString();
        }
        return result;
    }

    // getters & setters
    public static ArrayList<Member> getMembers() {
        return members;
    }

    public static void setMembers(ArrayList<Member> members) {
        members = members;
    }
}
