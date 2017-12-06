import java.util.ArrayList;
import java.util.Date;

public class Bid {
    private double reservePrice;
    private int numOfBids;
    private String highestBidder;
    private double highestBid;
    private Date highestBidDate;

    private int artworkID;
    private BidHistory bidHistory;

    public Bid(double rPrice, int numOfBids, int artID){
        this.reservePrice = rPrice;
        this.numOfBids = numOfBids;
        this.artworkID = artID;
        this.highestBid = 0;
    }


    public double getReservePrice() {
        return reservePrice;
    }
    //Maybe that aint important?
    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getNumOfBids() {
        return numOfBids;
    }
    //same for this one.
    public void setNumOfBids(int numOfBids) {
        this.numOfBids = numOfBids;
    }
    private void bidResolve(){
        if (numOfBids == 0){
            System.out.println("You won the auction!");
        }
    }

    public void initBidSystem(UserProfiles currentBidder, double bid){
        if (currentBidder.getUserName().toLowerCase().equals(highestBidder)){
            System.out.println("You can't bid when you are the highest bidder.");
        }
        else if (bid < reservePrice) {
            System.out.println("Bid refused. You need to place a bid higher than the reserve price.");
        }
        else if (bid <= highestBid){
            System.out.println("Bid refused. Your bid is equal to or lower than the current highest bid.");
        }
        else{
            this.highestBid = bid;
            this.numOfBids--;
            this.highestBidder = currentBidder.getUserName();
            this.highestBidDate = new Date();
            bidHistory.updateHistories(highestBid, highestBidder, highestBidDate);
        }
        bidResolve();
    }

    public BidHistory getBidHistory() {
        return bidHistory;
    }

    public void setBidHistory(BidHistory bidHistory) {
        this.bidHistory = bidHistory;
    }

    public int getArtworkID() {
        return artworkID;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public Date getHighestBidDate() {
        return highestBidDate;
    }
}
