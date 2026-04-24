package partII;

import java.util.List;
import java.util.Random;

public class Bidder implements Runnable {
	private static int id = 0;
    private final String name;
    private final AuctionEngine engine;
    private final List<AuctionItem> items;
    private final Random random = new Random(id++);
    private final int numBids;

    Bidder(String name, AuctionEngine engine, List<AuctionItem> items, int numBids) {
        this.name = name;
        this.engine = engine;
        this.items = items;
        this.numBids = numBids;
    }

    private void placeRandomSingleBid() {
        AuctionItem item = items.get(random.nextInt(items.size()));
        // Bid between 1% and 15% above current price to create real contention
        double bump = 1.0 + (random.nextDouble() * 0.14 + 0.01);
        double amount = Math.round(item.getHighestBid() * bump * 100.0) / 100.0;
        boolean won = engine.placeBid(name, item.id, amount);
        if (won) {
            System.out.printf("  ✓ %s bid $%.2f on Item %d%n", name, amount, item.id);
        }
    }
    
    public static final int DELAY = 50;

    @Override
    public void run() {
        for (int i = 0; i < numBids; i++) {
            placeRandomSingleBid();
            try {
            	Thread.sleep((int)(DELAY*Math.random()));
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
        }
    }

}