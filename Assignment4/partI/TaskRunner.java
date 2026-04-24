package partI;

import java.util.ArrayList;

public class TaskRunner {
	private ArrayList<Task> tasks;
	
	public TaskRunner() {
		tasks = new ArrayList<Task>();
	}

	// Add a task to the TaskRunner
	public void add(Task task) {
		tasks.add(task);
	}
	
	// Execute all the tasks that have been added and return total CPU time
	public int run() { 
		int totalCPUTime = 0;
		
		for (int i = 0; i < tasks.size(); i++) {
			totalCPUTime += tasks.get(i).process();
		}
		
		return totalCPUTime;
	}
	
	// Main method to test the TaskRunner
    public static void main(String[] args) {
    	TaskRunner taskRunner = new TaskRunner();

    	taskRunner.add(new BackupTask("Backup 1"));
    	taskRunner.add(new BackupTask("Backup 2"));
    	taskRunner.add(new LoggingTask("Logging"));

    	ArrayList<String> files = new ArrayList<>();
    	
    	files.add("fileA.txt");
    	files.add("fileB.csv");
    	files.add("fileC.png");
    	files.add("fileD.jpg");

    	taskRunner.add(new FileCleanupTask("File Cleanup", files));

    	int total = taskRunner.run();
    	
    	System.out.println("Total CPU time spent: " + total);
    }
}
