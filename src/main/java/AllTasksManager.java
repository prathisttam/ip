import java.util.Scanner;

/**
 * Manages a list of tasks, allowing users to add, list, mark and unmark tasks.
 */
public class AllTasksManager {
    private Task[] tasksList = new Task[100];
    private int taskListIndex = 0;

    /**
     * Uses the Ui class to handle resuable user interface components
     */
    Ui userInterface = new Ui();

    /**
     * Adds a new task to the task list.
     *
     * @param taskDescription The description of the task to be added
     */
    public void addTask(String taskDescription) {
        tasksList[taskListIndex] = new Task(taskDescription);
        taskListIndex += 1;
        userInterface.printDashedLine();
        System.out.println("added: " + taskDescription);
        userInterface.printDashedLine();
    }

    /**
     * Lists all tasks in the order in which they were added.
     */
    public void listTasks() {
        int taskIndex = 1;
        userInterface.printDashedLine();
        for (int i = 0; i < taskListIndex; i++) {
            Task currentTask = tasksList[i];
            System.out.println(taskIndex + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getTaskDescription());
            taskIndex += 1;
        }
        userInterface.printDashedLine();
    }

    /**
     * Prints the task at the specified position in the task list.
     *
     * @param number The position of the task in the list (1-based index).
     */
    public void printSingleTask(int number) {
        Task currentTask = tasksList[number - 1];
        System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getTaskDescription());
    }

    /**
     * Handles user input for adding, listing, marking and unmarking tasks.
     * User can enter command "bye" to exit
     */
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
                        tasksList[number - 1].markAsCompleted();
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
                        tasksList[number - 1].markAsIncomplete();
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
