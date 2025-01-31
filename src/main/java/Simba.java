import java.util.Scanner;

public class Simba {
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        userInterface.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Echo echoGame = new Echo();
        AllTasksManager allTasksManager = new AllTasksManager();

        while (true) {
            userInterface.showMenu();
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            }

            if (input.equals("1")) {
                echoGame.startEcho();
            } else if (input.equals("2")) {
                allTasksManager.startTaskManagement();
            } else if (input.equals("3")) {
                userInterface.showExitMessage();
                break;
            } else {
                System.out.println("Invalid input! Please enter 1, 2 or 3.");
            }
        }

        sc.close();
    }
}

