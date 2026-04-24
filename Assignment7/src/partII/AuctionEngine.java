package partII;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.*;

public class AuctionEngine {
    private final List<AuctionItem> items;
    private final List<BidRecord> auditLog = new ArrayList<>();
    private final AtomicLong sequenceCounter = new AtomicLong(0);
    private final Lock lock = new ReentrantLock(); 

    AuctionEngine(List<AuctionItem> items) {
        this.items = items;
    }

    boolean placeBid(String bidder, int itemId, double amount) {
    	lock.lock(); // Acquire the lock
    	
    	try {
	        AuctionItem item = items.get(itemId);
	        if (amount <= item.getHighestBid()) {
	            return false; // bid too low
	        }
	        item.acceptBid(bidder, amount);
	        // Log while still holding item lock — prevents ordering gaps
	        writeToLog(itemId, bidder, amount);
	        return true;
    	} finally {
          lock.unlock(); // Release the lock
        }
        
    }

    private void writeToLog(int itemId, String bidder, double amount) {
        long seq = sequenceCounter.incrementAndGet();
        auditLog.add(new BidRecord(seq, itemId, bidder, amount));

    }

    List<BidRecord> getAuditLog() {
    	lock.lock(); // Acquire the lock
    	
    	try {
    		return new ArrayList<>(auditLog);
    	} finally {
	    	lock.unlock(); // Release the lock
	    }
         
    }

    void printResults() {
        System.out.println("\n======= FINAL RESULTS =======");
        for (AuctionItem item : items) {
            System.out.printf("Item %d (%s): $%.2f won by %s%n",
                item.id, item.name, item.getHighestBid(), item.getHighestBidder());
        }

        System.out.println("\n======= AUDIT LOG =======");
        getAuditLog().stream()
            .sorted(Comparator.comparingLong(BidRecord::sequenceNumber))
            .forEach(System.out::println);
    }
}