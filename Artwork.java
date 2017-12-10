
import java.util.ArrayList;
import java.util.Date;

/**
 * Abstract Artwork class to create the basis for creating an artwork entity.
 *
 * @author Tim Watson 880158
 * @version 3
 */
abstract class Artwork {

    private String artworkTitle;
    private String artworkDescription;

    private String artworkCreator;
    private int artworkYearCreated;
    private double reservePrice;
    private int numOfBids;
    private String dateTimeArtworkPlaced;
    private Bid highestBid;
    private ArrayList<Integer> bidHistory = new ArrayList<>();
    private int artworkSellerId;
    private int id;
    private String picture;


    /**
     * Constructor to create a new artwork.
     *
     * @param artworkTitle       Name of Artwork
     * @param artworkDescription Description of this piece of Art.
     * @param artworkCreator     Name of the creator of this Art piece.
     * @param artworkYearCreated The year this Art piece was created.
     * @param reservePrice       Price for the Artwork to be sold at.
     * @param numOfBids          Maximum number of bids allowed on this Artwork.
     * @param idOfSeller         user selling this artwork.
     * @param id                 Id value of this artwork.
     * @param picture            Path to the main picture of this artwork
     */
    protected Artwork(String artworkTitle, String artworkDescription,
                      String artworkCreator, int artworkYearCreated, double reservePrice,
                      int numOfBids, int idOfSeller, int id, String picture) {

        this.artworkTitle = artworkTitle;
        this.artworkDescription = artworkDescription;
        this.artworkCreator = artworkCreator;
        this.artworkYearCreated = artworkYearCreated;
        this.reservePrice = reservePrice;
        this.numOfBids = numOfBids;
        this.dateTimeArtworkPlaced = Utilities.getDate();
        this.artworkSellerId = idOfSeller;
        highestBid = new Bid(reservePrice);
        this.id = id;
        this.picture = picture;

    }

    /**
     * Constructor for Artwork
     *
     * @param artworkTitle       Title of the artwork
     * @param artworkDescription Optional description of this artwork object
     * @param artworkCreator     Person who created this artwork
     * @param artworkYearCreated The year the artwork was created
     * @param reservePrice       Minimum price a bid can be for
     * @param numOfBids          Number of bids left in this auction
     * @param idOfSeller         The id of the of the account selling this artwork
     * @param id                 Id of this artwork
     * @param picture            Path to picture
     * @param highestBid         Highest bid on this artwork
     * @param bidHistory         History of bids placed on this artwork
     */
    protected Artwork(String artworkTitle, String artworkDescription,
                      String artworkCreator, int artworkYearCreated, double reservePrice,
                      int numOfBids, int idOfSeller, int id, String picture, Bid highestBid, ArrayList<Integer> bidHistory) {

        this.artworkTitle = artworkTitle;
        this.artworkDescription = artworkDescription;
        this.artworkCreator = artworkCreator;
        this.artworkYearCreated = artworkYearCreated;
        this.reservePrice = reservePrice;
        this.numOfBids = numOfBids;
        this.dateTimeArtworkPlaced = Utilities.getDate();
        this.artworkSellerId = idOfSeller;
        this.highestBid = highestBid;
        this.bidHistory = bidHistory;
        this.id = id;
        this.picture = picture;

    }

    protected Artwork() {

    }

    /**
     * Get picture path for this artwork
     *
     * @return path to picture.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Set a new picture for this artwork
     *
     * @param picture new path to a picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Add another bid to this object if the artwork is still being auctioned
     *
     * @param newBid the new bid object
     * @throws Exception
     */
    protected void placeBid(Bid newBid) throws Exception {
        if (numOfBids == 0) {
            throw new Exception("Artwork is completed");
        }
        highestBid = newBid;
        bidHistory.add(newBid.getBidID());
        numOfBids--;
    }

    /**
     * Return highest bid on this object
     *
     * @return Bid object of the highest bid.
     */
    public Bid getHighestBid() {
        return highestBid;
    }

    /**
     * Get the list of bids made on this object
     *
     * @return
     */
    public ArrayList getBidHistory() {
        return bidHistory;
    }

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
        return reservePrice;
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
     * Get method to retrieve the number of bids left on this artwork
     *
     * @return Number of bids left.
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
    protected String getDateTimeArtworkPlaced() {
        return dateTimeArtworkPlaced;
    }

    /**
     * Set Method to change the reserve price of this Artwork
     *
     * @param newReservePrice New reserve price.
     */
    protected void setReservePrice(double newReservePrice) {
        this.reservePrice = newReservePrice;
    }

    /**
     * Set method to change the time the Artwork was added to Artatawe system.
     *
     * @param newDateTimePlaced New date and time.
     */
    protected void setDateTimeArtworkPlaced(String newDateTimePlaced) {
        this.dateTimeArtworkPlaced = newDateTimePlaced;
    }

    /**
     * Set method to change the name of the Artwork seller.
     *
     * @param newArtworkSeller Name of the new Artwork Seller.
     */
    protected void setArtworkSeller(int newArtworkSeller) {
        this.artworkSellerId = newArtworkSeller;
    }

    /**
     * Get Method to retrieve the name of the seller of the Artwork.
     *
     * @return Name of the seller.
     */
    protected int getArtworkSeller() {
        return artworkSellerId;
    }

    /**
     * Get the artwork identifier of this Artwork
     *
     * @return Identifier of the artwork object
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set a new id for this object
     *
     * @param id Integer value of new artwork
     */
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Artwork{" +
                "artworkTitle='" + artworkTitle + '\'' +
                ", artworkDescription='" + artworkDescription + '\'' +
                ", artworkCreator='" + artworkCreator + '\'' +
                ", artworkYearCreated=" + artworkYearCreated +
                ", reservePrice=" + reservePrice +
                ", numOfBids=" + numOfBids +
                ", id=" + id +
                ", dateTimeArtworkPlaced=" + dateTimeArtworkPlaced +
                ", artworkSeller='" + artworkSellerId + '\'' +
                '}';
    }
}
