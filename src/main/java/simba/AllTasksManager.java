package simba;

import data.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

import java.util.List;

/**
 * Manages a list of tasks, allowing users to add, list, mark and unmark tasks.
 */
public class AllTasksManager {
    //private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasksList = new ArrayList<>();
    //private int taskListIndex = 0;

    public static ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Uses the Ui class to handle reusable user interface components
     */
    Ui userInterface = new Ui();
    Storage storage;

    {
        try {
            storage = new Storage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor: Load tasks from file at startup
     */
    public AllTasksManager() throws SimbaException {
        try {
            List<String> savedTasks = storage.loadTasks();
            for (String taskLine : savedTasks) {
                Task task = convertStringInStorageToTask(taskLine);
                if (task != null) {
                    addTaskToArray(task);
                }
            }
        } catch (IOException e) {
            throw new SimbaException("Unable to load tasks list" + e.getMessage());
        }
    }

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

        switch (taskType) {
        case "todo":
            newTask = new Todo(taskDetails);
            addTaskToArray(newTask);
            break;
        case "deadline":
            try {
                newTask = createDeadline(taskDetails);
                if (newTask != null) {
                    addTaskToArray(newTask);
                }
            } catch (SimbaException e) {
                userInterface.printErrorMessage(e.getMessage());
            }
            break;
        case "event":
            try {
                newTask = createEvent(taskDetails);
                if (newTask != null) {
                    addTaskToArray(newTask);
                }
            } catch (SimbaException e) {
                userInterface.printErrorMessage(e.getMessage());
            }

            break;
        default:
            throw new SimbaException("Invalid command to add tasks! \n" + "Valid Commands:todo, deadline, event");
        }

        if (newTask != null) {
            saveTasksToStorage();
            userInterface.printTaskAdded(newTask, tasksList.size());
        }
    }

    private void addTaskToArray(Task task) {
        tasksList.add(task);
    }

    /**
     * Creates a Deadline task by extracting the task description and due date from the input string.
     *
     * @param taskDetails The input string containing the task description and due date, formatted as:
     *                    *                   "description /by dueDate".
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
     *
     * @param taskDetails taskDetails The input string containing the task description, start time, and end time,
     *                    *                    formatted as: "description /from startTime /to endTime".
     * @return A new Event object if the input is valid, otherwise returns null and prints an error message.
     */
    private Task createEvent(String taskDetails) throws SimbaException {
        String[] parts = taskDetails.split(" /from ", 2);
        if (parts.length < 2) {
            throw new SimbaException("Invalid event format!\n"
                    + "Usage: event <description> /from <start /to end time string>\n"
                    + "Example: event Team meeting /from 10:00 AM /to 12:00 PM");
        }
        String eventDescription = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);

        if (timeParts.length < 2) {
            throw new SimbaException("Invalid time format!\n"
                    + "Usage: time <start /to end time string>\n"
                    + "Example: event project meeting /from Mon 2pm /to 4pm");
        }

        String startTime = timeParts[0];
        String endTime = timeParts[1];

        return new Event(eventDescription, startTime, endTime);
    }

    /**
     * Lists all tasks in the order in which they were added.
     */
    public void listTasks() throws SimbaException {
        if (tasksList.isEmpty()) {
            throw new SimbaException("Your task list is empty. Add a task using todo, deadline or event.");
        }

        userInterface.printTaskList(tasksList);
    }

    /**
     * Prints the task at the specified position in the task list.
     *
     * @param number The position of the task in the list (1-based index).
     */
    public void printSingleTask(int number) {
        userInterface.printSingleTask(number);
    }

    private void handleMark(String userInput) throws SimbaException {
        String[] parts = userInput.split(" ");

        if (parts.length < 2) {
            throw new SimbaException("Please provide a task number to mark as completed.\nUsage: mark <task_number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new SimbaException("Invalid task number! Please enter a number.\n Example: mark 2");
        }

        if (taskNumber <= 0 || taskNumber > tasksList.size()) {
            throw new SimbaException("Invalid task number! No task exists at that index.");
        }

        tasksList.get(taskNumber - 1).markAsCompleted();
        userInterface.printMarkedTask(taskNumber);
        saveTasksToStorage();
    }

    public void handleDelete(String input) throws SimbaException {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            throw new SimbaException("Please provide a task number to delete.\nUsage: delete <task_number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new SimbaException("Invalid task number! Please enter a number.\n Example: delete 2");
        }

        if (taskNumber <= 0 || taskNumber > tasksList.size()) {
            throw new SimbaException("Invalid task number! No task exists at that index.");
        }

        Task taskToDelete = tasksList.get(taskNumber - 1);
        tasksList.remove(taskNumber - 1);
        userInterface.printTaskDeleted(taskToDelete, tasksList.size());
    }

    private void handleUnmark(String input) throws SimbaException {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            throw new SimbaException("Please provide a task number to unmark.\nUsage: unmark <task_number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new SimbaException("Invalid task number! Please enter a number.\n Example: unmark 2");
        }

        if (taskNumber <= 0 || taskNumber > tasksList.size()) {
            throw new SimbaException("Invalid task number! No task exists at that index.");
        }

        tasksList.get(taskNumber - 1).markAsIncomplete();
        userInterface.printUnmarkedTask(taskNumber);
        saveTasksToStorage();
    }

    //recheck this
    private void saveTasksToStorage() {
        List<String> tasksToSave = new ArrayList<>();
        for (Task task : tasksList) {
            tasksToSave.add(task.toStorageString());
        }

        try {
            for (String taskToSave : tasksToSave) {
                storage.saveTasks(taskToSave); //
            }
            storage.saveTasks(String.join(System.lineSeparator(), tasksToSave));
        } catch (IOException e) {
            System.out.println("Error saving tasks to storage." + e.getMessage());
        }
    }

    private Task convertStringInStorageToTask(String taskLine) throws SimbaException {
        String[] parts = taskLine.split("\\|");

        if (parts.length < 3) {
            throw new SimbaException("Invalid task entered by user previously.\n");
        }

        String taskType = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (taskType) {
        case "T":
            Todo todo = new Todo(description);
            if (isCompleted) {
                todo.markAsCompleted();
            }
            return todo;
        case "D":
            if (parts.length < 4) {
                throw new SimbaException("Corrupt deadline, skipping: " + taskLine);
            }

            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);

            if (isCompleted) {
                deadline.markAsCompleted();
            }
            return deadline;
        case "E":
            if (parts.length < 5) {
                throw new SimbaException("Corrupt event, skipping: " + taskLine);
            }

            String from = parts[3].trim();
            String to = parts[4].trim();
            Event event = new Event(description, from, to);

            if (isCompleted) {
                event.markAsCompleted();
            }

            return event;
        default:
            throw new SimbaException("Unknown task type. Skipping task " + taskType);
        }
    }

    /**
     * Handles user input for adding, listing, marking and unmarking tasks.
     * User can enter command "bye" to exit
     */
    public void startTaskManagement() {
        //Scanner to read input
        userInterface.showTaskPrompt();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                userInterface.showExitMessage();
                break;
            } else if (input.trim().equalsIgnoreCase("list")) {
                try {
                    listTasks();
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
            } else if (input.startsWith("mark")) {
                try {
                    handleMark(input);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
            } else if (input.startsWith("unmark")) {
                try {
                    handleUnmark(input);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
            } else if (input.startsWith("delete")) {
                try {
                    handleDelete(input);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
            } else {
                try {
                    createTaskFromInput(input);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
            }
        }
    }
}
