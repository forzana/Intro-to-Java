package portfolio;

public class MarketContext {
    private final double stockPrice;        // underlying spot price (S)
    private final double annualRate;        // risk-free rate (r), e.g. 0.05
    private final double yearsToMaturity;   // T in years
    private final double strike;            // K

    public MarketContext(double stockPrice, double annualRate,
                         double yearsToMaturity, double strike) {
        this.stockPrice = stockPrice;
        this.annualRate = annualRate;
        this.yearsToMaturity = yearsToMaturity;
        this.strike = strike;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public double getYearsToMaturity() {
        return yearsToMaturity;
    }

    public double getStrike() {
        return strike;
    }
}
