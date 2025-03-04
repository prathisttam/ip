import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing users to add, list, mark and unmark tasks.
 */
public class AllTasksManager {
    //private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasksList = new ArrayList<>();
    //private int taskListIndex = 0;

    /**
     * Uses the Ui class to handle reusable user interface components
     */
    Ui userInterface = new Ui();

    /**
     * Adds a new task to the task list.
     *
     * @param taskDescription The description of the task to be added
     */
    public void createTaskFromInput(String taskDescription) throws SimbaException {
        String[] parts = taskDescription.split(" ", 2); // To separate out type of task

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SimbaException("Invalid Command :( Please provide a valid task description.");
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
            try {
                newTask = createDeadline(taskDetails);
                addTaskToArray(newTask);
            } catch (SimbaException e) {
                userInterface.printDashedLine();
                System.out.println(e.getMessage());
                userInterface.printDashedLine();
            }
            break;
        case "event":
            try {
                newTask = createEvent(taskDetails);
                addTaskToArray(newTask);
            } catch (SimbaException e) {
                userInterface.printDashedLine();
                System.out.println(e.getMessage());
                userInterface.printDashedLine();
            }

            break;
        default:
            throw new SimbaException("Invalid command to add tasks! \n" + "Valid Commands:todo, deadline, event");
        }

        if (newTask!= null){
            System.out.println("Got it. I've added this task:\n" + "  " + newTask.toString());
            System.out.println("Now you have " + (tasksList.size()) + " tasks in the list.");
            userInterface.printDashedLine();
        }
    }

    private void addTaskToArray(Task task){
        tasksList.add(task);
        //tasksList[taskListIndex] = task;
        //taskListIndex += 1;
    }

    /**
     * Creates a Deadline task by extracting the task description and due date from the input string.
     * @param taskDetails The input string containing the task description and due date, formatted as:
     *  *                   "description /by dueDate".
     * @return A new Deadline object if the input is valid, otherwise returns null and prints an error message.
     */
    private Task createDeadline(String taskDetails) throws SimbaException {
        String[] parts = taskDetails.split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SimbaException("Invalid deadline format!\n"
                    + "Usage: deadline <description> /by <date string>\n"
                    + "Example: deadline Submit report /by sunday");
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Creates an Event task by extracting the task description, start time, and end time from the input string.
     * @param taskDetails taskDetails The input string containing the task description, start time, and end time,
     *  *                    formatted as: "description /from startTime /to endTime".
     * @return A new Event object if the input is valid, otherwise returns null and prints an error message.
     */
    private Task createEvent(String taskDetails) throws SimbaException{
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new SimbaException("Invalid event format!\n"
                    + "Usage: event <description> /from <start to end time string>\n"
                    + "Example: event Team meeting /from 10:00 AM to 12:00 PM");
        }
        return new Event(parts[0], parts[1]);
    }

    /**
     * Lists all tasks in the order in which they were added.
     */
    public void listTasks() throws SimbaException{
       if(tasksList.isEmpty()) {
           throw new SimbaException("Your task list is empty. Add a task using todo, deadline or event.");
       }

       int taskIndex = 1;
       userInterface.printDashedLine();
       for (Task task: tasksList) {
           System.out.println(taskIndex + "." + task.toString()); //new
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
        Task currentTask = tasksList.get(number - 1);
        System.out.println("  " + currentTask.toString());
    }
    
    private void handleMark(String userInput) throws SimbaException {
        int taskNumber = getTaskNumber(userInput, "Please provide a task number to mark as completed.\nUsage: mark <task_number>", "Invalid task number! Please enter a number.\n Example: mark 2");

        tasksList.get(taskNumber-1).markAsCompleted();
        userInterface.printDashedLine();
        System.out.println("Nice! I've marked this task as done:");
        printSingleTask(taskNumber);
        userInterface.printDashedLine();
    }

    private void handleUnmark(String input) throws SimbaException {
        int taskNumber = getTaskNumber(input, "Please provide a task number to unmark.\nUsage: unmark <task_number>", "Invalid task number! Please enter a number.\n Example: unmark 2");

        tasksList.get(taskNumber - 1).markAsIncomplete();
        userInterface.printDashedLine();
        System.out.println("OK, I've marked this task as not done yet:");
        printSingleTask(taskNumber);
        userInterface.printDashedLine();
    }

    public void handleDelete(String input) throws SimbaException {
        int taskNumber = getTaskNumber(input, "Please provide a task number to delete.\nUsage: delete <task_number>", "Invalid task number! Please enter a number.\n Example: delete 2");

        Task taskToDelete = tasksList.get(taskNumber - 1);
        tasksList.remove(taskNumber - 1);

        userInterface.printDashedLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToDelete.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        userInterface.printDashedLine();
    }

    private int getTaskNumber(String input, String message, String message1) throws SimbaException {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            throw new SimbaException(message);
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new SimbaException(message1);
        }

        if (taskNumber <= 0 || taskNumber > tasksList.size()) {
            throw new SimbaException("Invalid task number! No task exists at that index.");
        }
        return taskNumber;
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
                try {
                    listTasks();
                } catch (SimbaException e) {
                    userInterface.printDashedLine();
                    System.out.println(e.getMessage());
                    userInterface.printDashedLine();
                }
            } else if (input.startsWith("mark")) {
                try {
                    handleMark(input);
                } catch (SimbaException e) {
                    userInterface.printDashedLine();
                    System.out.println(e.getMessage());
                    userInterface.printDashedLine();
                }
            } else if (input.startsWith("unmark")) {
                try {
                    handleUnmark(input);
                } catch (SimbaException e) {
                    userInterface.printDashedLine();
                    System.out.println(e.getMessage());
                    userInterface.printDashedLine();
                }
            } else if (input.startsWith("delete")) {
                try {
                    handleDelete(input);
                } catch (SimbaException e) {
                    userInterface.printDashedLine();
                    System.out.println(e.getMessage());
                    userInterface.printDashedLine();
                }
            } else {
                try {
                    createTaskFromInput(input);
                } catch (SimbaException e) {
                    userInterface.printDashedLine();
                    System.out.println(e.getMessage());
                    userInterface.printDashedLine();
                }
            }
        }
    }

}
