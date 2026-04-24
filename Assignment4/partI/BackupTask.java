package partI;

public class BackupTask extends Task {

	public BackupTask(String name) {
		super(name);
	}

	@Override
	public int getCPUTime() {
		return 20;
	}

	@Override
	public int process() {
		System.out.println("executing BackupTask " + getName());
        return getCPUTime();
	}
}
