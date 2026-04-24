package partII;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LiveAuction {
    public static void main(String[] args) throws InterruptedException {

        // Set up items
        List<AuctionItem> items = List.of(
            new AuctionItem(0, "Vintage Guitar",  500.00),
            new AuctionItem(1, "Oil Painting",    300.00),
            new AuctionItem(2, "Rare Watch",     1200.00),
            new AuctionItem(3, "Sports Memorabilia", 150.00),
            new AuctionItem(4, "Antique Vase",    800.00)
        );

        AuctionEngine engine = new AuctionEngine(items);

        // Set up bidders
        String[] names = {
            "Alice", "Bob", "Carol", "Dave", "Eve",
            "Frank", "Grace", "Hank", "Iris", "Jack",
            "Karen", "Leo", "Mia", "Ned", "Olivia",
            "Paul", "Quinn", "Rose", "Sam", "Tara"
        };
        ArrayList<Bidder> bidders = new ArrayList<>();
        for (String name : names) {
        	bidders.add(new Bidder(name, engine, items, 10));
        }
        
        ExecutorService executor = Executors.newCachedThreadPool();

        // Launch a bid for each bidder
        for (Bidder bidder : bidders) {
            executor.execute(bidder);
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        engine.printResults();
    }
}