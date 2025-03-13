package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles file storage operations for saving and loading tasks.
 */
public class Storage {

    private static final String FILE_PATH = "./data/Simba.txt";

    /**
     * Checks if the file and folder exist and creates them if they don't.
     *
     * @throws IOException If an error occurs while creating the file or directory.
     */
    public Storage() throws IOException {
        File file = new File(FILE_PATH);

        if(!file.exists()){
            boolean dirCreated = file.getParentFile().mkdirs();
            boolean fileCreated = file.createNewFile();

            if(!fileCreated){
                throw new FileNotFoundException();
            }

            if(!dirCreated){
                throw new FileNotFoundException();
            }
        }
    }

    /**
     * Saves the given text to the storage file, overwriting any existing content.
     *
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(String textToWrite) throws IOException {
        try ( FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Loads tasks from the storage file into a list.
     *
     * @return A list of task strings read from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<String> loadTasks() throws IOException {
        List<String> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                tasks.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Unable to load from it: " + e.getMessage());
        }
        return tasks;
    }

}
