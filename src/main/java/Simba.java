import java.util.Scanner;

public class Simba {
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        userInterface.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Echo echoGame = new Echo();

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
                userInterface.showExitMessage();
                break;
            } else {
                System.out.println("Invalid input! Please enter 1 or 2.");
            }
        }

        sc.close();
    }
}

