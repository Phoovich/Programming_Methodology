package grader;

import interfaces.Duplicatable;
import interfaces.Modifiable;
import interfaces.Parallelizable;
import logic.task.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TaskManagerTest {
    @BeforeEach
    void init() {
        TaskList.setTasks(new ArrayList<>());
        TaskList.addTasks(new SimpleTask("Display", 0.125));
        TaskList.addTasks(new CalculationTask("Vector", 0.500));
        TaskList.addTasks(new SortTask("Reorder", 0.500));
        TaskList.addTasks(new RenderTask("Rainbow", 0.105));
        TaskList.addTasks(new SortTask("Scheduler", 0.200));
        TaskList.addTasks(new RenderTask("Wave", 0.450));
        TaskList.addTasks(new RenderTask("Gradient", 0.105));
        TaskList.addTasks(new SimpleTask("Random", 0.25));
        TaskList.addTasks(new CalculationTask("Multiplication", 2.500));
    }
    @AfterEach
    void finish() {
        TaskList.setTasks(new ArrayList<>());
    }
    @Test
    void testGetTaskByType() {
        ArrayList<Class> types = new ArrayList<>();
        types.add(Duplicatable.class);
        ArrayList<Task> tasks = TaskManager.getTaskByType(types);
        assertEquals(3,tasks.size());
        assertSame(tasks.get(0),TaskList.getTask(3));
        assertSame(tasks.get(1),TaskList.getTask(5));
        assertSame(tasks.get(2),TaskList.getTask(6));
    }
    @Test
    void testGetTaskByMultipleTypes() {
        ArrayList<Class> types = new ArrayList<>();
        types.add(Modifiable.class);
        types.add(Parallelizable.class);
        ArrayList<Task> tasks = TaskManager.getTaskByType(types);
        assertEquals(7,tasks.size());
        assertSame(tasks.get(0),TaskList.getTask(1));
        assertSame(tasks.get(1),TaskList.getTask(2));
        assertSame(tasks.get(2),TaskList.getTask(3));
        assertSame(tasks.get(3),TaskList.getTask(4));
        assertSame(tasks.get(4),TaskList.getTask(5));
        assertSame(tasks.get(5),TaskList.getTask(6));
        assertSame(tasks.get(6),TaskList.getTask(8));


    }
    @Test
    void testDeleteDuplicateTasks() {
        TaskList.setTasks(new ArrayList<>());
        Task task1 = new RenderTask("Wave-1", 0.450);
        Task task2 = new SimpleTask("Display-1", 0.125);
        Task task3 = new RenderTask("Gradient", 0.105);
        TaskList.addTasks(task1);
        TaskList.addTasks(task2);
        TaskList.addTasks(task3);
        ((Duplicatable)task1).duplicateTask(20);
        ((Duplicatable)task3).duplicateTask(10);
        ((Duplicatable)TaskList.getTask(3)).duplicateTask(5);
        TaskManager.deleteDuplicateTasks();
        assertEquals(2,TaskList.getTasks().size());
        assertSame(task2,TaskList.getTask(0));
        assertSame(task3,TaskList.getTask(1));
    }
}
