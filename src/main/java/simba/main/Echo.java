package simba.main;

import simba.utils.Parser;
import simba.utils.Ui;

/**
 * Echoes commands back to the user.
 * It continuously reads commands until the user types "bye".
 */
public class Echo {
    Ui userInterface = new Ui();

    public void startEcho(Parser parser) {
        System.out.println("Game Started!");
        userInterface.printDashedLine();

        while (true) {
            String input = parser.readCommand();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            }

            userInterface.printEchoedCommands(input);
        }
    }
}
