package interfaces;

import controllers.CompetitiveController;
import controllers.MembersController;
import controllers.TreasurerController;

// On demand static import.
import static data.Database.*;
import static interfaces.UserInterface.*;

public class Navigator {
    private CompetitiveController cc = new CompetitiveController();
    private MembersController mc = new MembersController();
    private TreasurerController tc = new TreasurerController();

    public Navigator() {

        loadDatabase();
        menu();

    }

    public void menu() {

        while (true) {
            displayMsg("--Main Menu--");
            displayMsg("1. Manage members");
            displayMsg("2. Treasurer Options");
            displayMsg("3. Competitive Results");
            displayMsg("4. Exit");

            menuOptions();
        }

    }

    public void menuOptions() {

        switch(intValidation(4,1)) {

            case 1:
                manageMembersMenu();
                enterToReturn();
                break;

            case 2:
                treasurerOptionsMenu();
                enterToReturn();
                break;

            case 3:
                competitiveResultsMenu();
                enterToReturn();
                break;

            case 4:
                System.exit(0);

        }

    }

    public void manageMembersMenu() {
        displayMsg("--Manage Members--");
        displayMsg("1. Add member");
        displayMsg("2. Show current members");
        displayMsg("3. Exit");

        switch(intValidation(3,1)) {
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
        displayMsg("--Treasurer Options--");
        displayMsg("1. Display total subscription data");
        displayMsg("2. Display all current members in arrear");
        displayMsg("3. Exit");

        switch(intValidation(3,1)) {
            case 1:
                displayMsg("Total subscription amount: "+tc.getTotalSubscriptions());
                break;

            case 2:
                displayMsg("List of arrears: "+tc.arrearsToString());
                break;

            case 3:
                break;
        }
    }

    public void competitiveResultsMenu() {
        displayMsg("--Competitive Results--");
        displayMsg("1. Add result to member");
        displayMsg("2. Show best results");
        displayMsg("3. Show all results");
        displayMsg("4. Exit");

        switch(intValidation(4,1)) {
            case 1:
                cc.addSwimResult();
                break;

            case 2:
                cc.showBestResult();
                break;

            case 3:
                displayMsg("-- All Swim Results --");
                displayMsg(cc.allResultsToString());
                break;

            case 4:
                break;
        }
    }
}
