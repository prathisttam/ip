package simba;

import java.util.Scanner;

public class Echo {
    Ui userInterface = new Ui(); // Add Ui as a class variable

    /**
     * Echoes commands entered by the user until user inputs the command "bye"
     */
    public void startEcho(Parser parser) {
        System.out.println("Game Started!");
        userInterface.printDashedLine();

        while (true) {
            String input = parser.readCommand();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            }

            // Echo the command
            userInterface.printEchoedCommands(input);
        }
    }
}
