package portfolio;

import java.util.ArrayList;

public class Portfolio {
	
	// Calculates the total portfolio value of an ArrayList of FinancialInstrument using MarketContext
	public static double totalPortfolioValue(ArrayList<FinancialInstrument> list, MarketContext ctx) {
		double totalVal = 0.0;
		
		// Add up the values for each FinancialInstrument in the ArrayList
		for (int i = 0; i < list.size(); i++) {
			totalVal += list.get(i).value(ctx);
		}
			
		return totalVal;
    }
	
	public static void main(String[] args) {

        MarketContext ctx = new MarketContext(
                180.0,  // stock price
                0.05,   // annual rate
                5,   // years to maturity
                170.0   // strike price
        );
        
        ArrayList<FinancialInstrument> instrumentList = new ArrayList<>();
        
        Stock xyz = new Stock("XYZ", 100);
        Bond abc = new Bond("ABC", 1000.00, 0.05, 6);
        
        instrumentList.add(xyz);
        instrumentList.add(abc);
        
        double val = totalPortfolioValue(instrumentList, ctx);
        
        System.out.println("Total value: " + val);
	}

}
