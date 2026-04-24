package partII;

//Represents a single accepted bid in the audit log
record BidRecord(long sequenceNumber, int itemId, String bidderName, double amount) {
 @Override
 public String toString() {
     return String.format("[#%03d] Item %d | %-12s | $%.2f",
         sequenceNumber, itemId, bidderName, amount);
 }
}