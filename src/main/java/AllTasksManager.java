import java.util.Scanner;

/**
 * Manages a list of tasks, allowing users to add, list, mark and unmark tasks.
 */
public class AllTasksManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasksList = new Task[MAX_TASKS];
    private int taskListIndex = 0;

    /**
     * Uses the Ui class to handle reusable user interface components
     */
    Ui userInterface = new Ui();

    /**
     * Adds a new task to the task list.
     *
     * @param taskDescription The description of the task to be added
     */
    public void createTaskFromInput(String taskDescription) {
        String[] parts = taskDescription.split(" ", 2); // To separate out type of task

        if (parts.length < 2) {
            System.out.println("Invalid command! Please provide a task description.");
            return;
        }

        userInterface.printDashedLine();

        String taskType = parts[0].toLowerCase();
        String taskDetails = parts[1];
        Task newTask = null;

        switch(taskType){
        case "todo":
            newTask = new Todo(taskDetails);
            addTaskToArray(newTask);
            break;
        case "deadline":
            newTask = createDeadline(taskDetails);
            if (newTask != null) {
                addTaskToArray(newTask);
            }
            break;
        case "event":
            newTask = createEvent(taskDetails);
            if(newTask != null) {
                addTaskToArray(newTask);
            }
            break;
        default:
            System.out.println("Invalid command! Please use 'todo', 'event' or 'deadline'!");
            return;
        }
        System.out.println("Got it. I've added this task:\n" + "  " + newTask.toString());
        System.out.println("Now you have " + (taskListIndex) + " tasks in the list.");
        userInterface.printDashedLine();
    }

    private void addTaskToArray(Task task){
        tasksList[taskListIndex] = task;
        taskListIndex += 1;
    }

    /**
     * Creates a Deadline task by extracting the task description and due date from the input string.
     * @param taskDetails The input string containing the task description and due date, formatted as:
     *  *                   "description /by dueDate".
     * @return A new Deadline object if the input is valid, otherwise returns null and prints an error message.
     */
    private Task createDeadline(String taskDetails) {
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2) {
            System.out.println("Invalid deadline format! Use: deadline <description> /by <date>");
            return null;
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Creates an Event task by extracting the task description, start time, and end time from the input string.
     * @param taskDetails taskDetails The input string containing the task description, start time, and end time,
     *  *                    formatted as: "description /from startTime /to endTime".
     * @return A new Event object if the input is valid, otherwise returns null and prints an error message.
     */
    private Task createEvent(String taskDetails) {
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            System.out.println("Invalid event format! Use: event <description> /from <start time> /to <end time>");
            return null;
        }
        return new Event(parts[0], parts[1]);
    }

    /**
     * Lists all tasks in the order in which they were added.
     */
    public void listTasks() {
        int taskIndex = 1;
        userInterface.printDashedLine();
        for (int i = 0; i < taskListIndex; i++) {
            Task currentTask = tasksList[i];
            System.out.println(taskIndex + "." + currentTask.toString()); //new
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
        System.out.println("  " + currentTask.toString());
    }
    
    private void handleMark(String userInput) {
        String[] parts = userInput.split(" ");
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
    }

    private void handleUnmark(String input) {
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
                handleMark(input);
            } else if (input.startsWith("unmark")) {
                handleUnmark(input);
            } else {
                createTaskFromInput(input);
            }
        }
    }

}
