package data;

import java.io.*;
import java.nio.channels.MembershipKey;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database implements Serializable {
    private ArrayList<Member> members = new ArrayList<>();
    private static boolean databaseIsSaved = false;
    private File databaseFile = new File("src/data/database.txt");

    public Member createMember(LocalDate birthDate, String name, boolean isActive) {
        return new Member(birthDate, name, isActive, false);
    }

    public CompetitiveMember createMember(LocalDate birthDate, String name, boolean isActive, boolean isCompetitive) {
        return new CompetitiveMember(birthDate, name, isActive, true);
    }

    public void addMember(Member member) {
        members.add(member);
        saveDatabase();
    }

    public void removeMemberByIndex(int i) {
        members.remove(i);
        saveDatabase();
    }

    public void removeMemberByObject(Member member) {
        members.remove(member);
        saveDatabase();
    }

    public void saveDatabase() {
        try {
            FileOutputStream fos = new FileOutputStream(databaseFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(members);
            oos.close();
            fos.close();
            databaseIsSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDatabase() {
        if (databaseFile!=null) {
            try {
                FileInputStream fis = new FileInputStream(databaseFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.members = (ArrayList<Member>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                System.out.print("Unable to find database file. Please check or create a new.\n");
            }
        }
    }

    // Returner String med alle navne ifbm indtastning af SwimResults
    public String getAllCompetitiveNames() {
        String result = "";
        ArrayList<Member> compMembers = getAllCompetitiveMembers(true, true);
        for (int i = 0; i<compMembers.size(); i++) {
            result += members.get(i).getName();
            result += "|";
        }
        return result;
    }

    public ArrayList<Member> getAllCompetitiveMembers(boolean isSenior, boolean getAll) {
        if (!getAll) {
            ArrayList<Member> compMembers = new ArrayList<>();
            if (isSenior) {
                for (int i = 0; i < members.size(); i++) {
                    if (members.get(i).isCompetitive() && members.get(i).isSenior()) {
                        compMembers.add(members.get(i));
                    }
                }
            } else {
                for (int i = 0; i < members.size(); i++) {
                    if (members.get(i).isCompetitive() && !members.get(i).isSenior()) {
                        compMembers.add(members.get(i));
                    }
                }
            }
            return compMembers;
        } else {
            ArrayList<Member> compMembers = new ArrayList<>();
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).isCompetitive()) {
                    compMembers.add(members.get(i));
                   }
            }
            return compMembers;
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i<members.size(); i++) {
            result += members.get(i).toString();
        }
        return result;
    }

    // getters & setters

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
