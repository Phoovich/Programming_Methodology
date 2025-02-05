package logic.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<Task> getTasks() {
        return tasks;
    }
    public static Task getTask(int index) {
        return tasks.get(index);
    }
    public static void setTasks(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }
    public static void addTasks(Task task) {
        tasks.add(task);
    }
    public static void removeTask(int index) {
        tasks.remove(index);
    }
}
