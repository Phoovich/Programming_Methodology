package logic.task;
import static java.lang.Double.max;


public abstract class Task {
    private String name;
    private double workload;

    public Task(String name, double workload) {
        setName(name);
        setWorkload(workload);
    }

    public abstract String fullTaskName();

    public String toString() {
        return name + " " + fullTaskName();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = max(workload,0);
    }

}
