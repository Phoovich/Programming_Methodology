package grader;

import interfaces.Computable;
import interfaces.Duplicatable;
import interfaces.Parallelizable;
import logic.compute.ComputeUnit;
import logic.compute.GraphicUnit;
import logic.compute.ProcessUnit;
import logic.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenderTaskTest {
	private Task task;
	private ProcessUnit processUnit;
	private ProcessUnit graphicUnit;
	private ProcessUnit computeUnit;
	private ArrayList<Task> tasks;

	@BeforeEach
	void init() {
		task = new RenderTask("First", 55);
		tasks = new ArrayList<>();

		tasks.add(new SimpleTask("Test", 10));
		tasks.add(new SimpleTask("Test2", 10));
		tasks.add(task);
		tasks.add(new SimpleTask("Test3", 10));

		TaskList.setTasks(tasks);
		processUnit = new ProcessUnit("Test Model", 2, 4) {
			@Override
			public boolean canCompute(Task task) {
				return true;
			}
		};
		graphicUnit = new GraphicUnit("GTX 1050", 640, 1.455);
		computeUnit = new ComputeUnit("Intel Core i5-2500", 4, 3.3);
	}

	@Test
	void testConstructor() {
		assertEquals(55, task.getWorkload());
		assertEquals("First", task.getName());
		assertEquals("First Rendering Task", task.toString());

		Task t = new RenderTask("Extra", -10);
		assertEquals(0, t.getWorkload());

	}

	@Test
	void testDuplicateTask() {
		((Duplicatable) task).duplicateTask(5);
		assertEquals(9, TaskList.getTasks().size());
		for (int i = 4; i < 9; i++) {
			assertEquals("First-" + (i - 3) + " Rendering Task", TaskList.getTask(i).toString());
		}

		Task newTask = TaskList.getTask(5);
		((Duplicatable) newTask).duplicateTask(3);
		assertEquals(12, TaskList.getTasks().size());
		for (int i = 9; i < 12; i++) {
			assertEquals("First-2-" + (i - 8) + " Rendering Task", TaskList.getTask(i).toString());
		}
	}

	@Test
	void testCompute() {
		String timeStr = String.format("%.3f", ((Computable) task).compute(processUnit));
		double time = Double.parseDouble(timeStr);
		assertEquals(13.75, time);

		timeStr = String.format("%.3f", ((Computable) task).compute(computeUnit));
		time = Double.parseDouble(timeStr);
		assertEquals(16.667, time);

		timeStr = String.format("%.3f", ((Computable) task).compute(graphicUnit));
		time = Double.parseDouble(timeStr);
		assertEquals(37.801, time);
	}

	@Test
	void testParallelCompute() {
		assertEquals(6.875, ((Parallelizable) task).parallelCompute(processUnit));

		String timeStr = String.format("%.3f", ((Parallelizable) task).parallelCompute(computeUnit));
		double time = Double.parseDouble(timeStr);
		assertEquals(4.167, time);

		timeStr = String.format("%.3f", ((Parallelizable) task).parallelCompute(graphicUnit));
		time = Double.parseDouble(timeStr);
		assertEquals(0.059, time);
	}
}
