
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

    private String artworkCreator;
    private int artworkYearCreated;
    private double reservePrice;
    private int numOfBids;
    private Date dateTimeArtworkPlaced;
    private Bid highestBid;
    private ArrayList<Integer> bidHistory = new ArrayList<>();
    private int artworkSellerId;
    private int id;
    private String picture;



    /**
     * Constructor to create a new artwork.
     *
     * @param artworkTitle Name of Artwork
     * @param artworkDescription Description of this piece of Art.
     * @param artworkCreator Name of the creator of this Art piece.
     * @param artworkYearCreated The year this Art piece was created.
     * @param reservePrice Price for the Artwork to be sold at.
     * @param numOfBids Maximum number of bids allowed on this Artwork.
     * @param idOfSeller user selling this artwork.
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
        this.dateTimeArtworkPlaced = new Date();
        this.artworkSellerId = idOfSeller;
		highestBid = new Bid(reservePrice);
        this.id = id;
        this.picture = picture;

    }

    //For File I/O
    protected Artwork(String artworkTitle, String artworkDescription,
                      String artworkCreator, int artworkYearCreated, double reservePrice,
                      int numOfBids, int idOfSeller, int id, String picture, Bid highestBid, ArrayList<Integer> bidHistory) {

        this.artworkTitle = artworkTitle;
        this.artworkDescription = artworkDescription;
        this.artworkCreator = artworkCreator;
        this.artworkYearCreated = artworkYearCreated;
        this.reservePrice = reservePrice;
        this.numOfBids = numOfBids;
        this.dateTimeArtworkPlaced = new Date();
        this.artworkSellerId = idOfSeller;
        this.highestBid = highestBid;
        this.bidHistory = bidHistory;
        this.id = id;
        this.picture = picture;

    }

    protected Artwork(){

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
        this.reservePrice = newReservePrice;
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
                ", reservePrice=" + reservePrice +
                ", numOfBids=" + numOfBids +
                ", id="+id +
                ", dateTimeArtworkPlaced=" + dateTimeArtworkPlaced +
                ", artworkSeller='" + artworkSellerId + '\'' +
                '}';
    }
}
