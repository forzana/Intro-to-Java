package partI;

public class LoggingTask extends Task {

	public LoggingTask(String name) {
		super(name);
	}

	@Override
	public int getCPUTime() {
		return 5;
	}

	@Override
	public int process() {
		System.out.println("executing LoggingTask " + getName());
        return getCPUTime();
	}
}
