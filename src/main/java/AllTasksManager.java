import java.util.Scanner;

public class AllTasksManager {
    private Task[] taskList = new Task[100];
    private int taskListIndex = 0;
    Ui userInterface = new Ui();

    // methods to addTask, listTasks
    public void addTask(String taskDescription) {
        taskList[taskListIndex] = new Task(taskDescription);
        taskListIndex += 1;
        userInterface.printDashedLine();
        System.out.println("added: " + taskDescription);
        userInterface.printDashedLine();
    }

    public void listTasks() {
        int taskIndex = 1;
        userInterface.printDashedLine();
        for (int i = 0; i < taskListIndex; i++) {
            Task currentTask = taskList[i];
            System.out.println(taskIndex + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getTaskDescription());
            taskIndex += 1;
        }
        userInterface.printDashedLine();
    }

    public void printSingleTask(int number) {
        Task currentTask = taskList[number - 1];
        System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getTaskDescription());
    }

    public void startTaskManagement() {
        //Scanner to read input
        System.out.println("Please enter the tasks you have yet to complete or type \"List\" to list pending tasks");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    int number = Integer.parseInt(parts[1]);
                    if (number > 0 && number <= taskListIndex) {
                        taskList[number - 1].markAsCompleted();
                        userInterface.printDashedLine();
                        System.out.println("Nice! I've marked this task as done:");
                        printSingleTask(number);
                        userInterface.printDashedLine();
                    }
                }
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    int number = Integer.parseInt(parts[1]);
                    if (number > 0 && number <= taskListIndex) {
                        taskList[number - 1].markAsIncomplete();
                        userInterface.printDashedLine();
                        System.out.println("OK, I've marked this task as not done yet:");
                        printSingleTask(number);
                        userInterface.printDashedLine();
                    }
                }
            } else {
                addTask(input);
            }
        }
    }
}
