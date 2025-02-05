package grader;

import interfaces.Computable;
import interfaces.Modifiable;
import interfaces.Parallelizable;
import logic.compute.ComputeUnit;
import logic.compute.GraphicUnit;
import logic.compute.ProcessUnit;
import logic.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTaskTest {
	private Task task;
	private ProcessUnit processUnit;
	private ProcessUnit graphicUnit;
	private ProcessUnit computeUnit;
	private ArrayList<Task> tasks;

	@BeforeEach
	void init() {
		task = new SortTask("First", 55);
		tasks = new ArrayList<>();

		tasks.add(new SimpleTask("Test", 10));
		tasks.add(new SimpleTask("Test2", 10));
		tasks.add(task);
		tasks.add(new SimpleTask("Test3", 10));
		tasks.add(new SortTask("Second", 100));
		tasks.add(new SortTask("Third", 1000));

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
		assertEquals("First Sorting Task", task.toString());

		Task sort1 = new SortTask("Extra", -7);
		assertEquals(0, sort1.getWorkload());

	}

	@Test
	void testMergeTask() {
		SortTask t = (SortTask) task;
		t.mergeTask(4);

		assertEquals(155, t.getWorkload());
		assertEquals("Third", tasks.get(4).getName());
	}

	@Test
	void testModify() {
		((Modifiable) task).modify(4);
		assertEquals(155, task.getWorkload());
		assertEquals(155, TaskList.getTask(2).getWorkload());
		assertEquals(5, TaskList.getTasks().size());

		Task newTask = TaskList.getTask(4);
		((Modifiable) newTask).modify(2);
		assertEquals(1155, newTask.getWorkload());
		assertEquals(1155, TaskList.getTask(3).getWorkload());
		assertEquals(4, TaskList.getTasks().size());

		newTask = TaskList.getTask(3);
		((Modifiable) newTask).modify(1);
		assertEquals(1155, newTask.getWorkload());
		assertEquals(1155, TaskList.getTask(3).getWorkload());
		assertEquals(4, TaskList.getTasks().size());
	}

	@Test
	void testCompute() {
		String timeStr = String.format("%.3f", ((Computable) task).compute(processUnit));
		double time = Double.parseDouble(timeStr);
		assertEquals(13.75, time);

		timeStr = String.format("%.3f", ((Computable) task).compute(computeUnit));
		time = Double.parseDouble(timeStr);
		assertEquals(16.667, time);

		/// Can't compute on graphicUnit
		timeStr = String.format("%.3f", ((Computable) task).compute(graphicUnit));
		time = Double.parseDouble(timeStr);
		assertEquals(-1, time);
	}

	@Test
	void testParallelCompute() {
		assertEquals(6.875, ((Parallelizable) task).parallelCompute(processUnit));

		String timeStr = String.format("%.3f", ((Parallelizable) task).parallelCompute(computeUnit));
		double time = Double.parseDouble(timeStr);
		assertEquals(4.167, time);

		/// Can't compute on graphicUnit
		timeStr = String.format("%.3f", ((Parallelizable) task).parallelCompute(graphicUnit));
		time = Double.parseDouble(timeStr);
		assertEquals(-1, time);
	}
}
