package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_PATH = "data/Simba.txt";

    /**
     * Constructor: Check if the file and folder exist, create them if they don't
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

//    public void saveTasksToStorage(String textToAppend) throws IOException {
//        try {
//            FileWriter fw = new FileWriter(FILE_PATH, true);
//            fw.write(textToAppend + System.lineSeparator());
//            //fw.close();
//        } catch (IOException e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//            throw e;
//        }
//    }
//
     // Processed string --> Basically everything is formatted already
    public void saveTasks(String textToWrite) throws IOException {
        try ( FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            throw e;
        }
    }

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
