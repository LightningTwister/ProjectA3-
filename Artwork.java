
import java.util.ArrayList;
import java.util.Date;

/**
 * Abstract Artwork class to create the basis for creating an artwork entity.
 *
 * @author Tim Watson 880158
 * @version 1
 */
 abstract class Artwork {

    private String artworkTitle;
    private String artworkDescription;
    //To do, add photo (Dunno how yet =} )
    private String artworkCreator;
    private int artworkYearCreated;
    private double reservePrice;
    private int numOfBids;
    private Date dateTimeArtworkPlaced;
    private Bid highestBid;
    private ArrayList<Integer> bidHistory = new ArrayList<>();
    private String artworkSeller;
    private int id;

    /**
     * Constructor to create a new artwork.
     *
     * @param artworkTitle Name of Artwork
     * @param artworkDescription Description of this piece of Art.
     * @param artworkCreator Name of the creator of this Art piece.
     * @param artworkYearCreated The year this Art piece was created.
     * @param reservePrice Price for the Artwork to be sold at.
     * @param numOfBids Maximum number of bids allowed on this Artwork.
     * @param usernameOfSeller user selling this artwork.
     */
    protected Artwork(String artworkTitle, String artworkDescription,
            String artworkCreator, int artworkYearCreated, double reservePrice,
            int numOfBids, String usernameOfSeller, int id) {

        this.artworkTitle = artworkTitle;
        this.artworkDescription = artworkDescription;
        this.artworkCreator = artworkCreator;
        this.artworkYearCreated = artworkYearCreated;
        this.numOfBids = numOfBids;
        this.dateTimeArtworkPlaced = new Date();
        this.artworkSeller = usernameOfSeller;
        this.id = id;
        this.reservePrice = reservePrice;
        highestBid = new Bid(reservePrice);
        //this.artworkBid = new Bid(reservePrice, numOfBids);
    }
protected  Artwork(){

}

    protected void placeBid(Bid newBid)throws Exception {
        highestBid = newBid;
        bidHistory.add(newBid.getBidID());
        numOfBids--;
    }

    public Bid getHighestBid(){
        return highestBid;
    }

    public ArrayList getBidHistory(){
        return bidHistory;
    }

    //protected void artworkAuction(UserProfile user, int bid) {
    //    artworkBid.initBidSystem(user, bid);
    //}
    //private void updateBidInfo() {
    //    numOfBids = artworkBid.getNumOfBids();
    //    reservePrice = artworkBid.getReservePrice();
    //}
    /**
     * Get Method to retrieve the name of this Artwork
     *
     * @return The Artwork title
     */
    protected String getArtworkTitle() {
        return artworkTitle;
    }

    /**
     * Sets a new title for the artwork
     *
     * @param newArtworkTitle The new artwork title.
     */
    protected void setArtworkTitle(String newArtworkTitle) {
        this.artworkTitle = newArtworkTitle;
    }

    /**
     * Get method to retrieve the description of the Artwork
     *
     * @return Artwork Description
     */
    protected String getArtworkDescription() {
        return artworkDescription;
    }

    /**
     * Set method to change the Artwork Description
     *
     * @param newArtworkDescription The new artwork description.
     */
    protected void setArtworkDescription(String newArtworkDescription) {
        this.artworkDescription = newArtworkDescription;
    }

    /**
     * Get method to retrieve the name of the creator of this artwork.
     *
     * @return Artwork Creator's name.
     */
    protected String getArtworkCreator() {
        return artworkCreator;
    }

    /**
     * Set method to change the name of the creator of this artwork.
     *
     * @param newArtworkCreator Name of the creator
     */
    protected void setArtworkCreator(String newArtworkCreator) {
        this.artworkCreator = newArtworkCreator;
    }

    /**
     * Get method to retrieve the year that this Artwork was created.
     *
     * @return Year of creation for this Artwork.
     */
    protected int getArtworkYearCreated() {
        return artworkYearCreated;
    }

    /**
     * Set Method to change the year created to a new value.
     *
     * @param newYearCreated The new creation date.
     */
    protected void setArtworkYearCreated(int newYearCreated) {
        this.artworkYearCreated = newYearCreated;
    }

    /**
     * Get method to retrieve the Reserve price.
     *
     * @return Value of the reserved price.
     */
    protected double getReservePrice() {
    //   return reservePrice;
        return 1;
    }

    /**
     * Set method to change the reserve price.
     *
     * @param newReservePrice Value to be the new reserve price.
     */
    protected void setReservePrice(int newReservePrice) {
        this.reservePrice = newReservePrice;
    }

    /**
     * Get method to retrieve the maximum number of bids on this Artwork
     *
     * @return
     */
    protected int getNumOfBids() {
        return numOfBids;
    }

    /**
     * Set method to change the number of bids allowed on this Artwork.
     *
     * @param newNumOfBids The new number of Bids.
     */
    protected void setNumOfBids(int newNumOfBids) {
        this.numOfBids = newNumOfBids;
    }

    /**
     * Get method to retrieve the date the artwork was created.
     *
     * @return The date of the artwork's creation on Artatawe.
     */
    protected Date getDateTimeArtworkPlaced() {
        return dateTimeArtworkPlaced;
    }

    /**
     * Set Method to change the reserve price of this Artwork
     *
     * @param newReservePrice New reserve price.
     */
    protected void setReservePrice(double newReservePrice) {
    //    this.reservePrice = newReservePrice;
    }

    /**
     * Set method to change the time the Artwork was added to Artatawe system.
     *
     * @param newDateTimePlaced New date and time.
     */
    protected void setDateTimeArtworkPlaced(Date newDateTimePlaced) {
        this.dateTimeArtworkPlaced = newDateTimePlaced;
    }

    /**
     * Set method to change the name of the Artwork seller.
     *
     * @param newArtworkSeller Name of the new Artwork Seller.
     */
    protected void setArtworkSeller(String newArtworkSeller) {
        this.artworkSeller = newArtworkSeller;
    }

    /**
     * Get Method to retrieve the name of the seller of the Artwork.
     *
     * @return Name of the seller.
     */
    protected String getArtworkSeller() {
        return artworkSeller;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "artworkTitle='" + artworkTitle + '\'' +
                ", artworkDescription='" + artworkDescription + '\'' +
                ", artworkCreator='" + artworkCreator + '\'' +
                ", artworkYearCreated=" + artworkYearCreated +
              //  ", reservePrice=" + reservePrice +
                ", numOfBids=" + numOfBids +

                ", dateTimeArtworkPlaced=" + dateTimeArtworkPlaced +
                ", artworkSeller='" + artworkSeller + '\'' +
                '}';
    }
}
