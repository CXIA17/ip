package task;

import java.util.List;
import java.util.ArrayList;

import userInterface.Message;

public class TaskLists {
    /** All the tasks stored */
    private final List<Tasks> tasksList;

    /**
     * Constructor of the class.
     */
    public TaskLists() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Add Task to the array inside TaskLists.
     *
     * @param task Task assigned.
     */
    public void addTask(Tasks task) {
        this.tasksList.add(task);
    }

    /**
     * Change the status of the Task at that index to checked.
     *
     * @param index index of the Task to change.
     */
    public void box(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        fetch.mark();
        this.tasksList.set(index - 1, fetch);
        System.out.println(Message.MARK_TASK_FRONT);
        System.out.println("  " + fetch);
        System.out.print(Message.DASH);
    }

    /**
     * Change the status of the Task at that index to unchecked.
     *
     * @param index index of the Task to change.
     */
    public void unBox(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        fetch.unMark();
        this.tasksList.set(index - 1, fetch);
        System.out.println(Message.UNMARK_TASK_FRONT);
        System.out.println("  " + fetch);
        System.out.print(Message.DASH);
    }

    /**
     * Delete the Task at that index.
     *
     * @param index index of the Task to delete.
     */
    public void delete(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        this.tasksList.remove(index - 1);
        System.out.println(Message.DELETE_TASK_FRONT);
        System.out.println("  " + fetch);
        int size = this.tasksList.size();
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(Message.DASH);
    }

    /**
     * Print the number of tasks stored inside the array of the class.
     */
    public void taskNumPrint() {
        System.out.println("Now you have " + this.tasksList.size() + " tasks in the list");
    }

    /**
     * Get the number of tasks stored inside the array of the class.
     *
     * @return the number of tasks stored inside the array of the class.
     */
    public int tasksSize() {
        return this.tasksList.size();
    }

    /**
     * Print all the tasks stored inside the array of the class with their index.
     */
    public void printTasks() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasksList.get(i).toString());
        }
    }

    /**
     * Print all the tasks inside the class in the intended format.
     *
     * @return all the tasks inside the class in an intended format.
     */
    public void search(String key) {
        int count = 0;
        String out = "";
        for (Tasks command : this.tasksList) {
            if (command.contain(key)) {
                count += 1;
                out += count + ". " + command.toString() + '\n';
            }
        }
        if (count > 0) {
            System.out.println(Message.FIND_OUTPUT_FRONT);
            System.out.print(out);
        } else {
            System.out.println(Message.NO_RESULT);
        }
        System.out.print(Message.DASH);
    }

    /**
     * Converts the string of each task into the format of the local file.
     *
     * @return Strings to store inside the local file.
     */
    public String listTasksForSave() {
        String output = "";
        for (Tasks command : this.tasksList) {
            output += String.format("%s\n", command.toSave());
        }
        return output;
    }
}
