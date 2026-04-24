package portfolio;

public class Stock extends FinancialInstrument {
	public int shares;
	
	public Stock(String name, int shares) {
		super(name);
		this.shares = shares;
	}
	
	public int getShares() {
		return shares;
	}
	
	// Calculates the value of the stock
	@Override
	public double value(MarketContext ctx) {
		return shares * ctx.getStockPrice();
	}
	
	// Returns a string representation of the Stock
	@Override
	public String toString() {
		return "Name: " + getName() + "\nShares: " + shares;
	}
}