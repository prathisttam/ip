package simba;

import data.Storage;
import simba.tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

import java.util.List;

/**
 * Manages a list of tasks, allowing users to add, list, mark and unmark tasks.
 */
public class AllTasksManager {
    private static ArrayList<Task> tasksList = new ArrayList<>();

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
    public AllTasksManager(Parser parser) throws SimbaException {
        try {
            List<String> savedTasks = storage.loadTasks();
            for (String taskLine : savedTasks) {
                Task task = parser.convertStringInStorageToTask(taskLine);
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
    public void createTaskFromInput(String taskDescription, Parser parser) {
        try {
            userInterface.printDashedLine();
            Task newTask = parser.parseTaskCreation(taskDescription);
            addTaskToArray(newTask);
            saveTasksToStorage();
            userInterface.printTaskAdded(newTask, tasksList.size());
        } catch (SimbaException e) {
            userInterface.printErrorMessage(e.getMessage());
        }
    }

    private void addTaskToArray(Task task) {
        tasksList.add(task);
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

    private void handleMark(String argument, Parser parser) throws SimbaException {
        int taskNumber = parser.parseTaskNumber(argument, tasksList.size());
        tasksList.get(taskNumber - 1).markAsCompleted();
        userInterface.printMarkedTask(taskNumber);
        saveTasksToStorage();
    }

    public void handleDelete(String argument, Parser parser) throws SimbaException {
        int taskNumber = parser.parseTaskNumber(argument, tasksList.size());
        Task taskToDelete = tasksList.get(taskNumber - 1);
        tasksList.remove(taskNumber - 1);
        userInterface.printTaskDeleted(taskToDelete, tasksList.size());
    }

    private void handleUnmark(String argument, Parser parser) throws SimbaException {
        int taskNumber = parser.parseTaskNumber(argument, tasksList.size());
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
        } catch (FileNotFoundException e) {
            System.out.println("Error: Storage file not found. Please check the file path.");
        } catch (AccessDeniedException e) {
            System.out.println("Error: Cannot write to file. Please check permissions.");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


    /**
     * Handles user input for adding, listing, marking and unmarking tasks.
     * User can enter command "bye" to exit
     */
    public void startTaskManagement(Parser parser) {
        userInterface.showTaskPrompt();

        while (true) {
            String input = parser.readCommand();
            String[] parsedInput = parser.parseCommand(input);
            String command = parsedInput[0];
            String argument = parsedInput[1];

            switch (command) {
            case "bye":
                userInterface.showExitMessage();
                return;
            case "list":
                try {
                    listTasks();
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            case "mark":
                try {
                    handleMark(argument, parser);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    handleUnmark(argument, parser);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            case "delete":
                try {
                    handleDelete(argument, parser);
                } catch (SimbaException e) {
                    userInterface.printErrorMessage(e.getMessage());
                }
                break;
            default:
                createTaskFromInput(input, parser);
            }
        }
    }
}
