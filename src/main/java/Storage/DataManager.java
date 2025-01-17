package Storage;

import java.io.*;
import java.util.Scanner;

import exceptions.InputException;
import task.*;

public class DataManager {
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private static final String FOLDER_PATH = "data";
    private static final String TXT_PATH = "data/chris.txt";

    /**
     * Creates a folder to store the data if it does not exist.
     */
    public static void createFolder() {
        File parentDirectory = new File(FOLDER_PATH);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }

    /**
     * Creates a file to store the data if it does not exist.
     */
    public static void createText() {
        File textFile = new File(TXT_PATH);
        try {
            if (!textFile.createNewFile()) {
                System.out.println("already have an existing file locally");
            } else {
                System.out.println("Your journey has started");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the data to the file in a simplified format.
     *
     * @param data List of tasks stored inside the ChatBot.
     */
    public static void saveText(TaskLists data) {
        try (FileWriter fw = new FileWriter(TXT_PATH, false)) {
            fw.write(data.listTasksForSave());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from a binary file.
     *
     * @return List of tasks stored inside the ChatBot.
     * @throws InputException If the file is not found or there is an I/O error.
     */
    public static TaskLists readSavedData() throws InputException {
        try (Scanner reader = new Scanner(new File(TXT_PATH))) {
            TaskLists listCommands = new TaskLists();
            while (reader.hasNextLine()) {
                addToTaskList(listCommands, reader.nextLine());
            }
            return listCommands;
        } catch (IOException e) {
            throw new InputException("There's no file exists");
        }
    }

    private static void addToTaskList(TaskLists listCommands,
                                      String input) throws InputException {
        int splitLimit = 3;
        String[] taskDetails = input.split(" / ", splitLimit);
        String information = taskDetails[2];
        boolean isDone = taskDetails[1].equals("1");
        switch (taskDetails[0]) {
        case TODO:
            Tasks todoRead = new ToDos(information);
            checkFileState(todoRead, isDone);
            listCommands.addTask(todoRead);
            break;
        case DEADLINE:
            String[] deadlines = information.split(" / ", 2);
            if (deadlines.length == 2) {
                Tasks deadlinesRead = new Deadlines(deadlines[0], deadlines[1]);
                checkFileState(deadlinesRead, isDone);
                listCommands.addTask(deadlinesRead);
            }
            break;
        case EVENT:
            String[] events = information.split(" / ", 3);
            if (events.length == 3) {
                Tasks eventRead = new Events(events[0], events[1], events[2]);
                checkFileState(eventRead, isDone);
                listCommands.addTask(eventRead);
            }
            break;
        default:
            throw new InputException("Something is wrong with file loading");
        }
    }

    private static void checkFileState(Tasks record, boolean isDone) {
        if (isDone) {
            record.mark();
        }
    }
}
