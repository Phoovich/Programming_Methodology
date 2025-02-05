package logic.compute;

import interfaces.Computable;
import logic.task.Task;

public class ComputeUnit extends ProcessUnit {

    public ComputeUnit(String modelName, int coreNumber, double clockSpeed) {
        super(modelName, coreNumber, clockSpeed);
    }

    public boolean canCompute(Task task) {
        return (getClockSpeed() > 0.0) && task instanceof Computable;
    }
}
