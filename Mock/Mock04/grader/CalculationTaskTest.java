package grader;

import interfaces.Computable;
import interfaces.Modifiable;
import logic.compute.ComputeUnit;
import logic.compute.GraphicUnit;
import logic.compute.ProcessUnit;
import logic.task.CalculationTask;
import logic.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationTaskTest {
    private Task task;
    private ProcessUnit processUnit;
    private ProcessUnit graphicUnit;
    private ProcessUnit computeUnit;
    @BeforeEach
    void init() {
        task = new CalculationTask("First", 10);
        processUnit = new ProcessUnit("Test Model",2,4) {
            @Override
            public boolean canCompute(Task task) {
                return true;
            }
        };
        graphicUnit = new GraphicUnit("GTX 1050",640,1.455 );
        computeUnit = new ComputeUnit("Intel Core i5-2500",4,3.3 );
    }
    
    @Test
    void testConstructor() {
        assertEquals(10, task.getWorkload());
        assertEquals("First", task.getName());
        assertEquals("First Calculation Task", task.toString());
        
        task = new CalculationTask("Second", -1);
        assertEquals(0, task.getWorkload());
    }
    
    @Test
    void testChangeWorkload() {
        CalculationTask t = (CalculationTask)task;
        t.changeWorkload(4);
        assertEquals(14, t.getWorkload());
        
        t.changeWorkload(-6);
        assertEquals(8, t.getWorkload());
        
        t.changeWorkload(-10);
        assertEquals(0, t.getWorkload());    
    }
    
    
    @Test
    void testModify() {
        ((Modifiable)task).modify(10);
        assertEquals(20, task.getWorkload());
        
        ((Modifiable)task).modify(-5);
        assertEquals(15, task.getWorkload());

        ((Modifiable)task).modify(-50);
        assertEquals(0, task.getWorkload());

        ((Modifiable)task).modify(30);
        assertEquals(30, task.getWorkload());
    }
    
    @Test
    void testCompute() {
        String timeStr = String.format("%.3f",((Computable)task).compute(processUnit));
        double time = Double.parseDouble(timeStr);
        assertEquals(2.5, time);

        timeStr = String.format("%.3f",((Computable)task).compute(computeUnit));
        time = Double.parseDouble(timeStr);
        assertEquals(3.030, time);

        /// Can't compute on graphicUnit
        timeStr = String.format("%.3f",((Computable)task).compute(graphicUnit));
        time = Double.parseDouble(timeStr);
        assertEquals(-1.000, time);

    }
}
