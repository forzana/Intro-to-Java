package partI;

public abstract class Task {
	private String name;
	
	public Task(String name) {
		this.setName(name);
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract int getCPUTime();
	
	public abstract int process();
}
