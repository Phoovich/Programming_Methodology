package logic.task;


import java.util.ArrayList;

public class TaskManager {
	public static ArrayList<Task> getTaskByType(ArrayList<Class> types) {
    	// TODO implement this method
        
        return new ArrayList<>();
    }
	
    public static void deleteDuplicateTasks() {
    	// TODO implement this method
        
    }

    public static boolean instanceOf(Class checkClass, Class interfaceClass) {
        return interfaceClass.isAssignableFrom(checkClass);
    }
}
