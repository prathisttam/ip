package simba;

import java.util.Scanner;

public class Simba {
    public static void main(String[] args) throws SimbaException {
        Ui userInterface = new Ui();
        userInterface.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Echo echoGame = new Echo();
        AllTasksManager allTasksManager = new AllTasksManager();

        label:
        while (true) {
            userInterface.showMenu();
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            }

            switch (input) {
            case "1":
                echoGame.startEcho();
                break;
            case "2":
                try {
                    allTasksManager.startTaskManagement();
                } catch (Exception e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            case "3":
                userInterface.showExitMessage();
                break label;
            default:
                userInterface.printInvalidMenuChoice();
                break;
            }
        }

        sc.close();
    }
}

