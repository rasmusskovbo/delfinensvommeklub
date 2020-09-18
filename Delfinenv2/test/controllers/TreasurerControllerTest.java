package controllers;

import data.Database;
import interfaces.UserInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TreasurerControllerTest {

    // Da Database gemmer virker tests kun ved separate kørsler.
    @org.junit.jupiter.api.Test
    void getTotalSubscriptions_JuniorActive() {
        Database db = new Database();
        UserInterface ui = new UserInterface();
        TreasurerController tc = new TreasurerController();

        db.addMember(db.createMember(LocalDate.of(2010, 07, 02),"Test", true));

        // 1000x1 = 1000
        assertEquals(1000, tc.getTotalSubscriptions());
    }

    @org.junit.jupiter.api.Test
    void getTotalSubscriptions_PassiveOnly() {
        Database db = new Database();
        UserInterface ui = new UserInterface();
        TreasurerController tc = new TreasurerController();

        db.addMember(db.createMember(LocalDate.of(2010, 07, 02),"Test", false));

        // 500x1 = 500
        assertEquals(500, tc.getTotalSubscriptions());
    }

    @org.junit.jupiter.api.Test
    void getTotalSubscriptions_JuniorSeniorActive_SeniorPassive() {
        Database db = new Database();
        UserInterface ui = new UserInterface();
        TreasurerController tc = new TreasurerController();

        db.addMember(db.createMember(LocalDate.of(1990, 07, 02),"Test", true));
        db.addMember(db.createMember(LocalDate.of(1990, 07, 02),"Test", false));
        db.addMember(db.createMember(LocalDate.of(2010, 07, 02),"Test", true));

        // 1000+1600+500 = 3100
        assertEquals(3100, tc.getTotalSubscriptions());
    }


    @org.junit.jupiter.api.Test
    void getArrears_onlyOneMemberInArrayOfArrears() {
        Database db = new Database();
        UserInterface ui = new UserInterface();
        TreasurerController tc = new TreasurerController();

        db.addMember(db.createMember(LocalDate.of(2010, 07, 02),"Test", false));
        db.addMember(db.createMember(LocalDate.of(1990, 07, 02),"Test", false));
        db.getMembers().get(0).setBalance(-1000);

        assertEquals(1, tc.getArrears().size());

    }

    @org.junit.jupiter.api.Test
    void getMembershipFee_PassiveMember() {
        Database db = new Database();
        UserInterface ui = new UserInterface();
        TreasurerController tc = new TreasurerController();

        assertEquals(500, tc.getMembershipFee(db.createMember((LocalDate.of(2010, 07, 02)),"Test", false)));
    }

    @org.junit.jupiter.api.Test
    void getMembershipFee_ActiveSenior() {
        Database db = new Database();
        UserInterface ui = new UserInterface();
        TreasurerController tc = new TreasurerController();

        assertEquals(1600, tc.getMembershipFee(db.createMember((LocalDate.of(1990, 07, 02)),"Test", true)));
    }
}