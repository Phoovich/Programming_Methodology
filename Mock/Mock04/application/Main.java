package application;

import interfaces.Duplicatable;
import interfaces.Modifiable;
import logic.compute.ComputeUnit;
import logic.compute.GraphicUnit;
import logic.compute.ProcessUnit;
import logic.task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList <ProcessUnit> processUnits = new ArrayList<>();
    public static Scanner scanner;
    public static void main(String[] args) {
        int option = 0;
        scanner = new Scanner(System.in);
        initTask();
        initProcessUnit();
        System.out.println("======== Welcome to Task time simulation ========");
        while(true) {
            System.out.println("Select option(1 - 9)");
            System.out.println("1. List all of Processing Units");
            System.out.println("2. List all of Tasks");
            System.out.println("3. Add Processing Units");
            System.out.println("4. Add Task");
            System.out.println("5. Remove Processing Units");
            System.out.println("6. Remove Task");
            System.out.println("7. Modify Task");
            System.out.println("8. Estimate task time");
            System.out.println("9. Exit");
            System.out.print("Option: ");

            try {
                option = Integer.parseInt(scanner.next());
                if(option < 1 || option > 9)
                    throw new Exception();
            }
            catch (Exception e) {
                option = 0;
                System.out.println("------");
                System.out.println("Invalid option, please try again");
            }
            System.out.println("------");

            switch (option) {
                case 1:
                    showProcessorUnits();
                    break;
                case 2:
                    showTasks(TaskList.getTasks());
                    break;
                case 3:
                    addProcessorUnit();
                    break;
                case 4:
                    addTask();
                    break;
                case 5:
                    showProcessorUnits();
                    while(true) {
                        System.out.print(String.format("Select Processor Unit to be removed (1 - %d): ", processUnits.size()));
                        int index = 0;
                        try {
                            index = Integer.parseInt(scanner.next());
                            if(index < 1 || index > processUnits.size()) {
                                throw new Exception();
                            }
                            processUnits.remove(index-1);
                            break;
                        }
                        catch (Exception e) {
                            System.out.println("Invalid index");
                        }
                    }
                    break;
                case 6:
                    showTasks(TaskList.getTasks());
                    while(true) {
                        System.out.print(String.format("Select Task to be removed (1 - %d): ", TaskList.getTasks().size()));
                        int index = 0;
                        try {
                            index = Integer.parseInt(scanner.next());
                            if(index < 1 || index > TaskList.getTasks().size())
                                throw new Exception();
                            TaskList.removeTask(index-1);
                            break;
                        }
                        catch (Exception e) {
                            System.out.println("Invalid index");
                        }
                    }
                    break;
                case 7:
                    modifyTask();
                    break;
                case 8:
                    estimateTask();
                    break;
                case 9:
                    System.out.println("Exiting program");
                    System.exit(0);
                    break;
            }

            System.out.println("=========================");
        }
    }
    public static void initTask() {
        TaskList.addTasks(new SimpleTask("Display", 0.125));
        TaskList.addTasks(new CalculationTask("Vector", 0.500));
        TaskList.addTasks(new SortTask("Reorder", 0.500));
        TaskList.addTasks(new SortTask("Scheduler", 0.200));
        TaskList.addTasks(new RenderTask("Wave", 0.450));
        TaskList.addTasks(new RenderTask("Gradient", 0.105));
    }
    public static void initProcessUnit() {
        processUnits.add(new GraphicUnit("Nvidia GTX 1050",640,1.455 ));
        processUnits.add(new ComputeUnit("Intel Core i5-2500",4,3.3 ));
        processUnits.add(new GraphicUnit("Nvidia RTX 4070",4608,1.230 ));
        processUnits.add(new GraphicUnit("Nvidia RTX 4080",7424 ,1.350  ));
        processUnits.add(new GraphicUnit("Nvidia RTX 4070",4608,1.230 ));
        processUnits.add(new GraphicUnit("Nvidia RTX 4060",3072,1.470 ));
        processUnits.add(new ComputeUnit("Intel Core i7-8750H",6,4.1 ));
        processUnits.add(new ComputeUnit("Intel Core i9-9900K",8,5.0 ));
        processUnits.add(new GraphicUnit("Intel UHD Graphics 630",192,0.35 ));

    }
    public static void addProcessorUnit() {
        String modelName;
        int type;
        int coreNumber=1;
        double clockSpeed=1;
        System.out.println("Please enter process unit information");
        System.out.print("Model Name: ");
        modelName = scanner.next();
        System.out.print("Model Type (1 for CPU, anything else for GPU): ");
        try {
            type = Integer.parseInt(scanner.next());
            if(type != 1)
                throw new Exception();
        }
        catch (Exception e) {
            type = 2;
        }
        while(true) {
            System.out.print("Core Number: ");
            try {
                coreNumber = Integer.parseInt(scanner.next());
                if (coreNumber < 1)
                    throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        }

        while(true) {
            System.out.print("Clock Speed(GHz): ");
            try {
                clockSpeed = Double.parseDouble(scanner.next());
                if (clockSpeed <= 0)
                    throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        }
        switch (type) {
            case 1:
                processUnits.add(new ComputeUnit(modelName,coreNumber,clockSpeed));
                break;
            case 2:
                processUnits.add(new GraphicUnit(modelName,coreNumber,clockSpeed));
                break;
        }
        System.out.println("Finish adding " + modelName + " " + processUnits.getLast().getModelType());
    }
    public static void addTask() {
        String taskName;
        int taskType = 0;
        double workload = 1;
        System.out.println("Please enter task information");
        System.out.print("Task Name: ");
        taskName = scanner.next();
        System.out.println("Task Type:");
        System.out.println("1. Simple Task");
        System.out.println("2. Calculation Task");
        System.out.println("3. Sorting Task");
        System.out.println("4. Rendering Task");
        while(true) {
            System.out.println("Select Type(1 - 4): ");

            try {
                taskType = Integer.parseInt(scanner.next());
                if (taskType > 4 || taskType <= 0)
                    throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Invalid type");
            }
        }
        while(true) {
            System.out.print("Instruction count(Billion instructions): ");
            try {
                workload = Double.parseDouble(scanner.next());
                if (workload < 0)
                    throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        }

        switch (taskType) {
            case 1:
                TaskList.addTasks(new SimpleTask(taskName,workload));
                break;
            case 2:
                TaskList.addTasks(new CalculationTask(taskName,workload));
                break;
            case 3:
                TaskList.addTasks(new SortTask(taskName,workload));
                break;
            case 4:
                TaskList.addTasks(new RenderTask(taskName,workload));
                break;
        }
        System.out.println("Finish adding " + TaskList.getTasks().getLast().toString());
    }
    public static void showProcessorUnits() {
        int current;
        current = 1;
        for(ProcessUnit processUnit:processUnits) {
            System.out.println("Processor " + current);
            System.out.println("Model Name: " + processUnit.getModelName());
            System.out.println("Model Type: " + processUnit.getModelType());
            System.out.println("Core Number: " + processUnit.getCoreNumber());
            System.out.println("Clock Speed: " + processUnit.getClockSpeed() + " GHz");
            System.out.println("----------------");
            current++;
        }
    }
    public static void showTasks(ArrayList<Task> displayTask) {
        int current;
        current = 1;
        for(Task task:displayTask) {
            System.out.println("Task " + current);
            System.out.println("Task Name: " + task.toString());
            System.out.println("Task Type: " + task.fullTaskName());
            System.out.println("Instruction Count: " + task.getWorkload() + " Billion Instructions");
            System.out.println("----------------");
            current++;
        }
    }
    public static void estimateTask() {
        int current;
        current = 1;
        int max = Math.max(processUnits.size(),TaskList.getTasks().size());
        ArrayList<String> displayText = new ArrayList<>();
        for(ProcessUnit processUnit:processUnits) {
            displayText.add("Processor " + current);
            displayText.add("Model Name: " + processUnit.getModelName());
            displayText.add("Model Type: " + processUnit.getModelType());
            displayText.add("Core Number: " + processUnit.getCoreNumber());
            displayText.add("Clock Speed: " + processUnit.getClockSpeed() + " GHz");
            current++;
        }
        for(int ck = 1;ck <= max;ck++) {
            String addString = "";
            if(displayText.size() < 5*ck) {
                for(int j = 0;j < 5;j++)
                    displayText.add(" ".repeat(40));
            }
            for(int j = 0;j < 5;j++) {
                addString = displayText.get((ck - 1) * 5 + j);
                addString += " ".repeat(40 - addString.length());
                addString += "|";
                displayText.remove((ck - 1) * 5 + j);
                displayText.add((ck - 1) * 5 + j,addString);
            }
        }
        for(int ck = 1;ck <= TaskList.getTasks().size();ck++) {
            String addString = "";
            for(int j = 0;j < 4;j++) {
                addString = displayText.get((ck - 1) * 5 + j);
                switch (j) {
                    case 0:
                        addString += "Task " + ck;
                        break;
                    case 1:
                        addString += "Task Name: " + TaskList.getTask(ck-1).toString();
                        break;
                    case 2:
                        addString += "Task Type: " + TaskList.getTask(ck-1).fullTaskName();
                        break;
                    case 3:
                        addString += "Instruction Count: " + TaskList.getTask(ck-1).getWorkload() + " Billion Instructions";
                        break;
                }
                addString += " ".repeat(101 - addString.length());

                displayText.remove((ck - 1) * 5 + j);
                displayText.add((ck - 1) * 5 + j,addString);
            }
        }
        for(int ck = 1;ck <= max;ck++) {
            for(int i = 0;i < 5;i++) {
                System.out.println(displayText.get(5*(ck-1)+i));
            }
            System.out.println("-".repeat(102));
        }
        int taskIndex = 0,processorIndex = 0;
        while(true) {
            System.out.print(String.format("Select Processor Unit to be used (1 - %d): ", processUnits.size()));
            try {
                processorIndex = Integer.parseInt(scanner.next());
                if(processorIndex < 1 || processorIndex > processUnits.size())
                    throw new Exception();
                break;
            }
            catch (Exception e) {
                System.out.println("Invalid index");
            }
        }
        while(true) {
            System.out.print(String.format("Select Task to be estimated (1 - %d): ", TaskList.getTasks().size()));
            try {
                taskIndex = Integer.parseInt(scanner.next());
                if(taskIndex < 1 || taskIndex > TaskList.getTasks().size())
                    throw new Exception();
                break;
            }
            catch (Exception e) {
                System.out.println("Invalid index");
            }
        }
        taskIndex--;
        processorIndex--;

        Task task = TaskList.getTask(taskIndex);
        ProcessUnit processUnit = processUnits.get(processorIndex);
        System.out.println("Estimated task time for non-parallel computing: " + processUnit.compute(task) + " second");
        System.out.println("Estimated task time for parallel computing: " + processUnit.parallelCompute(task) + " second");
    }
    public static void modifyTask() {
        System.out.println("1. Add Instruction Count");
        System.out.println("2. Duplicate Task");
        System.out.println("3. Merge Task");
        System.out.println("4. Remove Duplicate Task");
        int option = 0;
        while(true) {
            try {
                System.out.print("Select Option(1 - 4): ");
                option = Integer.parseInt(scanner.next());
                if (option < 1 || option > 4) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid option");
            }
        }
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Integer> tasksIndex = new ArrayList<>();
        int current = 0;
        switch (option) {
            case 1:
                for(Task task:TaskList.getTasks()) {
                    if(task instanceof CalculationTask) {
                        tasks.add(task);
                        tasksIndex.add(current);
                    }
                    current++;
                }
                break;
            case 2:
                for(Task task:TaskList.getTasks()) {
                    if(task instanceof Duplicatable) {
                        tasks.add(task);
                        tasksIndex.add(current);
                    }
                    current++;
                }
                break;
            case 3:
                for(Task task:TaskList.getTasks()) {
                    if(task instanceof SortTask) {
                        tasks.add(task);
                        tasksIndex.add(current);
                    }
                    current++;
                }
                break;
            case 4:
                TaskManager.deleteDuplicateTasks();
                System.out.println("Finish operation");
                return;
        }
        System.out.println("------");
        showTasks(tasks);
        if((tasks.isEmpty() && option!=3) || (tasks.size() <= 1 && option==3)) {
            System.out.printf("Require at least %d tasks",1 + (option==3? 1:0));
        }
        int taskIndex = 0;
        while(true) {
            try {
                System.out.printf("Select Task(1 - %d): ", tasks.size());
                taskIndex = Integer.parseInt(scanner.next());
                if (taskIndex < 1 || taskIndex > tasks.size()) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid index");
            }

        }
        Task task = tasks.get(taskIndex-1);
        int modifyValue = 0;
        switch (option) {
            case 1:
                while(true) {
                    try {
                        System.out.print("Number of instruction to add (billion instructions in integer): ");
                        int ic = Integer.parseInt(scanner.next());
                        if (ic <= -task.getWorkload()) {
                            System.out.printf("Number of instruction must be greater than %.3f billion instructions\n", -task.getWorkload());
                            continue;
                        }
                        modifyValue = ic;
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid number");
                    }
                }
                break;
            case 2:
                while(true) {
                    try {
                        System.out.print("Number of task to be duplicated: ");
                        int dupNumber = Integer.parseInt(scanner.next());
                        if (dupNumber <= 0) {
                            System.out.println("Number of duplication task must be greater than 0");
                            continue;
                        }
                        modifyValue = dupNumber;
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid number");
                    }
                }
                break;
            case 3:
                while(true) {
                    try {
                        System.out.printf("Index of task to be merge with selected task(1 - %d, not %d): ",tasks.size(),taskIndex);
                        int anotherTask = Integer.parseInt(scanner.next());
                        if (anotherTask == taskIndex) {
                            System.out.println("Same selected task");
                            continue;
                        }
                        else if (anotherTask <= 0) {
                            System.out.println("Number of duplication task must be greater than 0");
                            continue;
                        }
                        else if (anotherTask > tasks.size()) {
                            System.out.printf("Number of duplication task must not exceed %d\n",tasks.size());
                            continue;
                        }
                        modifyValue = tasksIndex.get(anotherTask-1);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid index");
                    }
                }
                break;
        }
        if(option==2)
            ((Duplicatable)task).duplicateTask(modifyValue);
        else
            ((Modifiable)task).modify(modifyValue);
        System.out.println("Finish operation");
    }
}