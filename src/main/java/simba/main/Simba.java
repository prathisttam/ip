package simba.main;

import simba.utils.Parser;
import simba.Exceptions.SimbaException;
import simba.utils.Ui;

public class Simba {
    public static void main(String[] args) throws SimbaException {
        Ui userInterface = new Ui();
        userInterface.showWelcomeMessage();

        Parser parser = new Parser();
        Echo echoGame = new Echo();

        AllTasksManager allTasksManager = new AllTasksManager(parser);

        while (true) {
            userInterface.showMenu();
            String input = parser.readCommand();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            }

            switch (input) {
            case "1":
                // Starts the Echo game
                echoGame.startEcho(parser);
                break;
            case "2":
                try {
                    // Starts task management
                    allTasksManager.startTaskManagement(parser);
                } catch (IndexOutOfBoundsException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            case "3":
                // Exits the application
                userInterface.showExitMessage();
                break;
            default:
                userInterface.printInvalidMenuChoice();
                break;
            }

            if (input.equals("3")) {
                parser.closeScanner();
                break;
            }

        }
        parser.closeScanner();
    }
}

