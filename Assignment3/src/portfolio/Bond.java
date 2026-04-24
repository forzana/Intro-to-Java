package portfolio;

public class Bond extends FinancialInstrument {
	private double faceValue;
	private double couponRate;
	private int years;
	
	public Bond(String name, double faceValue, double couponRate, int years) {
		super(name);
		this.faceValue = faceValue;
		this.couponRate = couponRate;
		this.years = years;
	}
	
	public double getFaceValue() {
		return faceValue;
	}

	public double getCouponRate() {
		return couponRate;
	}

	public int getYears() {
		return years;
	}
	
	// Calculates the present value of the bond according to the formula provided
	@Override
	public double value(MarketContext ctx) {
		double r = ctx.getAnnualRate(); 
		
		double presentVal = 0.0;
		for (int t = 1; t <= years; t++) {
			presentVal += (faceValue * couponRate) / Math.pow((1.0 + r), t);
		}
		presentVal += faceValue / Math.pow((1.0 + r), years);
		
		return presentVal;
	}
	
	// Returns a string representation of the bond
	@Override
	public String toString() {
		return "Name: " + getName() + "\nFace Value: " + faceValue
				+ "\nCoupon Rate: " + couponRate + "\nYears: " + years;
	}
}