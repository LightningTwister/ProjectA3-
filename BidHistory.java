import java.util.ArrayList;
import java.util.Date;

public class BidHistory {
    private int artworkID;
    private ArrayList<Double> amountBidArray = new ArrayList<>();
    private ArrayList<String> profilesOfBidders = new ArrayList<>();
    private ArrayList<Date> dateArray = new ArrayList<>();

    public BidHistory(int id, ArrayList<Double> amountBidArray, ArrayList<String> profilesOfBidders, ArrayList<Date> dateArray) {
        this.amountBidArray = amountBidArray;
        this.profilesOfBidders = profilesOfBidders;
        this.dateArray = dateArray;
        this.artworkID = id;
    }
    public void updateHistories(double highestBid, String highestBidder, Date highestBidDate){
        amountBidArray.add(highestBid);
        profilesOfBidders.add(highestBidder);
        dateArray.add(highestBidDate);
    }

    public ArrayList<Double> getAmountBidArray() {
        return amountBidArray;
    }

    public void setAmountBidArray(double amount) {
        this.amountBidArray.add(amount);
    }

    public ArrayList<String> getProfilesOfBidders() {
        return profilesOfBidders;
    }

    public void setProfilesOfBidders(String profile) {
        this.profilesOfBidders.add(profile);
    }

    public ArrayList<Date> getDateArray() {
        return dateArray;
    }

    public void setDateArray(Date date) {
        this.dateArray.add(date);
    }

    public int getArtworkID() {
        return artworkID;
    }

    @Override
    public String toString() {
        return "BidHistory{" +
                "artworkID=" + artworkID +
                ", amountBidArray=" + amountBidArray +
                ", profilesOfBidders=" + profilesOfBidders +
                ", dateArray=" + dateArray +
                '}';
    }
}

