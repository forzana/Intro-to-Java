package portfolio;

public class FinancialInstrument {
	private String name;
	
	public FinancialInstrument(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public double value(MarketContext ctx) {
		return 0;
	}
	
	public String toString() {
		return "name: " + name;
	}
}
