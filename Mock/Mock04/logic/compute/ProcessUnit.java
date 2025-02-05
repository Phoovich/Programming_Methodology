package logic.compute;

import interfaces.Parallelizable;
import logic.task.Task;

public abstract class ProcessUnit {
    private String modelName;
    private int coreNumber;
    private double clockSpeed;

    public ProcessUnit(String modelName, int coreNumber, double clockSpeed) {
        setModelName(modelName);
        setCoreNumber(coreNumber);
        setClockSpeed(clockSpeed);
    }

    public String getModelType() {
        if(this instanceof ComputeUnit)
            return "Compute Unit";
        else if(this instanceof GraphicUnit)
            return "Graphic Unit";
        else
            return "General Unit";
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public double compute(Task task) {
        if(canCompute(task))
            return task.getWorkload() / getClockSpeed();
        else if(clockSpeed > 0)
            return 0;
        else
            return -1;
    }
    public double parallelCompute(Task task) {
        if(task instanceof Parallelizable && canCompute(task))
            return task.getWorkload() / (coreNumber * getClockSpeed());
        else
            return compute(task);
    }
    public abstract boolean canCompute(Task task);

    public int getCoreNumber() {
        return coreNumber;
    }

    public void setCoreNumber(int coreNumber) {
        this.coreNumber = coreNumber;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }
}
