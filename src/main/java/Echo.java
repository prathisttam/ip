import java.util.Scanner;

public class Echo {
    Ui userInterface = new Ui(); // Add Ui as a class variable

    public void startEcho() {
        System.out.println("Game Started!");
        System.out.println("____________________________________________________________");
        //Scanner to read input
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            }

            // Echo the command
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
    }
}
