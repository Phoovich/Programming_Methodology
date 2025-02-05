package logic.compute;

import interfaces.Computable;
import interfaces.Modifiable;
import logic.task.Task;

public class GraphicUnit extends ProcessUnit {
    public GraphicUnit(String modelName, int coreNumber, double clockSpeed) {
        super(modelName, coreNumber, clockSpeed);
    }
    public boolean canCompute(Task task) {
        return (getClockSpeed() > 0.0) && ((task instanceof Computable) && !(task instanceof Modifiable));
    }
    @Override
    public double compute(Task task) {
        if(canCompute(task))
            return task.getWorkload()/getClockSpeed();
        else
            return -1;
    }
}
