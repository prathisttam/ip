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
                    System.out.println(e.getMessage());
                }
                break;
            case "3":
                userInterface.showExitMessage();
                break label;
            default:
                System.out.println("Invalid input! Please enter 1, 2 or 3.");
                break;
            }
        }

        sc.close();
    }
}

