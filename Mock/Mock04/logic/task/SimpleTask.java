package logic.task;

public class SimpleTask extends Task {
    public SimpleTask(String name, double workload) {
        super(name, workload);
    }
    public String fullTaskName() {
        return "Simple Task";
    }
}
