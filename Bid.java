import java.util.Date;

/**
 * @author Martin Cvetkov 915797
 */
public class Bid {
    private int userID;
    private int ubidID;
    private int uartworkID;
    private double uamount;
    private String udatePlaced;

    /**
     *  Constructor to make a Bid object
     * @param amount Starting amount the user has bid
     */
    public Bid(double amount){
        uamount = amount;
    }

    /**
     *  Constructor To create a new bid object for an artwork
     * @param bidID New bid id
     * @param amount Amount they have bid
     * @param userID User that has placed this bid
     * @param artworkID Artwork they have bid on
     * @param currBid The current amount the user has bid
     * @throws Exception
     */
    public Bid(int bidID, double amount, int userID, int artworkID, Bid currBid) throws Exception{
        setAmount(amount);
        setArtworkID(artworkID);
        setBidID(bidID);
        setUserID(userID);

        bidAccepted(currBid);
        udatePlaced = Utilities.getDate();
    }

    /**
     *  Constructor to make a bid with a new bid amount
     * @param bidID Bid id
     * @param amount amount they have placed
     * @param userID User of the person who has placed the bid
     * @param artworkID Artwork being bid on
     * @param udatePlaced Reserve on the object
     */
    public Bid(int bidID, double amount, int userID, int artworkID, String udatePlaced) {
        setAmount(amount);
        setArtworkID(artworkID);
        setBidID(bidID);
        setUserID(userID);
        setDatePlaced(udatePlaced);
    }

    /**
     * Check to see if the amount placed in the bid is enough
     * @param currBid Amount that has been placed
     * @throws Exception
     */
    private void bidAccepted(Bid currBid) throws Exception{
        if(uamount < currBid.getAmount()) {
            throw new Exception("Bid too low");
        }
    }

    /**
     *  Set the userid who is placing a bid
     * @param userID new userid of the person placing the bid
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /**
     * Change the bid id of this object
     * @param ubidID New bid id
     */
    public void setBidID(int ubidID){
        this.ubidID = ubidID;
    }

    /**
     * Change the bid to being bid on a new object
     * @param uartworkID new artwork id
     */
    public void setArtworkID(int uartworkID){
        this.uartworkID = uartworkID;
    }

    /**
     *  Set the amount being bid on for the artwork
     * @param amount Amount of money being placed
     */
    public void setAmount(double amount){
        uamount = amount;
    }

    /**
     *  Set the date that this bid was placed to a new date
     * @param udatePlaced Date being changed
     */
    public void setDatePlaced(String udatePlaced){
        this.udatePlaced = udatePlaced;
    }

    /**
     *  Get the user id who has placed this bid
     * @return Userid reference to the user who placed this bid
     */
    public int getUserID(){
        return userID;
    }

    /**
     *  Get the id of the bid object
     * @return bid id
     */
    public int getBidID(){
        return ubidID;
    }

    /**
     * Get reference the artwork
     * @return Artwork identifier.
     */
    public int getArtworkID(){
        return uartworkID;
    }

    /**
     * Get the amount placed in this bid
     * @return the amount that has been placed on thsi bid
     */
    public double getAmount(){
        return uamount;
    }

    /**
     * Get the date this bid was placed
     * @return The date the bid of object was placed
     */
    public String getDatePlaced(){
        return udatePlaced;
    }

}
