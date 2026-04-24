package partII;


// Represents a single auction item
public class AuctionItem {
    final int id;
    final String name;
    private volatile double highestBid;
    private volatile String highestBidder;

    AuctionItem(int id, String name, double startingPrice) {
        this.id = id;
        this.name = name;
        this.highestBid = startingPrice;
        this.highestBidder = "STARTING PRICE";
    }

    double getHighestBid() { return highestBid; }
    String getHighestBidder() { return highestBidder; }

    // Only called while holding this item's lock
    void acceptBid(String bidder, double amount) {
        this.highestBid = amount;
        this.highestBidder = bidder;
    }
}