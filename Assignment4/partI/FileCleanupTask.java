package partI;

import java.util.ArrayList;

public class FileCleanupTask extends Task {
	private ArrayList<String> files;
	
	public FileCleanupTask(String name, ArrayList<String> files) {
		super(name);
		this.files = files;
	}

	@Override
	public int getCPUTime() {
		if (files == null)
			return 0;
		return 2 * files.size();
	}

	@Override
	public int process() {
		System.out.println("executing FileCleanupTask " + getName());
        return getCPUTime();
	}
}
