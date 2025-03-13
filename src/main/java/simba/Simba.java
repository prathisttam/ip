package simba;

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
                echoGame.startEcho(parser); // Starts the Echo game
                break;
            case "2":
                try {
                    allTasksManager.startTaskManagement(parser); // Starts task management
                } catch (IndexOutOfBoundsException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            case "3":
                userInterface.showExitMessage(); // Exits the application
                //parser.closeScanner();
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

