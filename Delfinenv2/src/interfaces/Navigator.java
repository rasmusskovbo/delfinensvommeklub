package interfaces;

import controllers.CompetitiveController;
import controllers.MembersController;
import controllers.TreasurerController;
import data.Database;
import data.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class Navigator {
    UserInterface ui = new UserInterface();
    Database database = new Database();
    CompetitiveController cc = new CompetitiveController(ui, database);
    MembersController mc = new MembersController(ui, database);
    TreasurerController tc = new TreasurerController(ui, database);

    public Navigator() {

        database.loadDatabase();
        menu();

    }

    public void menu() {

        while (true) {
            ui.displayMsg("--Main Menu--");
            ui.displayMsg("1. Manage members");
            ui.displayMsg("2. Treasurer Options");
            ui.displayMsg("3. Competitive Results");
            ui.displayMsg("4. Exit");

            menuOptions();
        }

    }

    public void menuOptions() {

        switch(ui.intValidation(4,1)) {

            case 1:
                manageMembersMenu();
                ui.enterToReturn();
                break;

            case 2:
                treasurerOptionsMenu();
                ui.enterToReturn();
                break;

            case 3:
                competitiveResultsMenu();
                ui.enterToReturn();
                break;

            case 4:
                System.exit(0);

        }

    }

    public void manageMembersMenu() {
        ui.displayMsg("--Manage Members--");
        ui.displayMsg("1. Add member");
        ui.displayMsg("2. Show current members");
        ui.displayMsg("3. Exit");

        switch(ui.intValidation(3,1)) {
            case 1:
                mc.addMember();
                break;

            case 2:
                mc.showAllMembers();
                break;

            case 3:
                break;
        }
    }

    public void treasurerOptionsMenu() {
        ui.displayMsg("--Treasurer Options--");
        ui.displayMsg("1. Display total subcription data");
        ui.displayMsg("2. Display all current members in arrear");
        ui.displayMsg("3. Exit");

        switch(ui.intValidation(3,1)) {
            case 1:
                ui.displayMsg("Total subcription amount: "+tc.getTotalSubscriptions());
                break;

            case 2:
                ui.displayMsg("List of arrears: "+tc.ArrearsToString());
                break;

            case 3:
                break;
        }
    }

    public void competitiveResultsMenu() {
        ui.displayMsg("--Competitive Results--");
        ui.displayMsg("1. Add result to member");
        ui.displayMsg("2. Show best results");
        ui.displayMsg("3. Show all results");
        ui.displayMsg("4. Exit");

        switch(ui.intValidation(4,1)) {
            case 1:
                cc.addSwimResult();
                break;

            case 2:
                cc.showBestResult();
                break;

            case 3:
                ui.displayMsg("Currently not available");
                break;

            case 4:
                break;
        }
    }
}
