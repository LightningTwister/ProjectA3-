import java.util.ArrayList;

/**
 * Class to create a Painting Object.
 * @author Tim Watson 880158
 * @version 2
 */
public class Painting extends Artwork {
    private int width;
    private int height;
    private ArrayList<String> pathToPictures;
    
    /**
     * Constructor to instantiate a painting object.
     * @param artworkTitle Name of Painting.
     * @param artworkDescription Description of Painting.
     * @param artworkCreator Creator of the Painting.
     * @param artworkYearCreated Year the Painting was created.
     * @param reservePrice The minimum price the Painting can be sold for.
     * @param numOfBids Maximum number of bids on the painting.
     * @param userId of the seller of the Painting.
     * @param width Width of the painting in cm's.
     * @param height Height of the painting in cm's.
     * @param id Id of this artwork object
     * @param picture Paths to pictures of this object
     */
    public Painting(String artworkTitle, String artworkDescription, 
                String artworkCreator, int artworkYearCreated, 
                    double reservePrice, int numOfBids, int userId,
                        int width, int height , int id, ArrayList<String> picture) {
        
        super(artworkTitle, artworkDescription, artworkCreator, 
                artworkYearCreated, reservePrice, numOfBids, userId, id, picture.get(0));

        this.width = width;
        this.height = height;
        this.pathToPictures = picture;

    }

    /**
     * Constructor that creates a painting object
     * @param artworkTitle Name of Painting.
     * @param artworkDescription Description of Painting.
     * @param artworkCreator Creator of the Painting.
     * @param artworkYearCreated Year the Painting was created.
     * @param reservePrice The minimum price the Painting can be sold for.
     * @param numOfBids Maximum number of bids on the painting.
     * @param userId of the seller of the Painting.
     * @param width Width of the painting in cm's.
     * @param height Height of the painting in cm's.
     * @param id Id of this artwork object
     * @param picture Paths to pictures of this object
     * @param currBid Current bid on this object
     * @param bidHistory History of bids that have been placed on this object
     */
    public Painting(String artworkTitle, String artworkDescription,
                    String artworkCreator, int artworkYearCreated,
                    double reservePrice, int numOfBids, int userId,
                    int width, int height , int id,
                    ArrayList<String> picture, Bid currBid, ArrayList<Integer> bidHistory) {

        super(artworkTitle, artworkDescription, artworkCreator,
                artworkYearCreated, reservePrice, numOfBids, userId, id, picture.get(0),currBid, bidHistory );

        this.width = width;
        this.height = height;
        this.pathToPictures = picture;

    }

    /**
     * Get the paths to pictures stored on this object
     * @return List of paths to images
     */
    public ArrayList<String> getPathToPictures() {
        return pathToPictures;
    }

    /**
     * Set paths to all the pictures
     * @param pathToPictures new list of paths to images
     */
    public void setPathToPictures(ArrayList<String> pathToPictures) {
        this.pathToPictures = pathToPictures;
    }

    /**
     * Get all the paths to images
     * @return List of paths
     */
    public ArrayList<String> getPictures() {
        return pathToPictures;
    }

    /**
     * Add a picture path to the list of paths
     * @param picture Path to pictures
     */
    public void addPicture(String picture) {
        this.pathToPictures.add( picture);
    }

    /**
     * Remove a specific path to a picture
     * @param path path to a picture that needs to be removed
     */
    public void removePicture(String path){
        for(String artworkPath: this.pathToPictures){
            if (path.equals(artworkPath)){
                this.pathToPictures.remove(artworkPath);
            }
        }
    }

    /**
     * Set picture paths
     * @param pictures new picture paths to be saved
     */
    public void setPicture(ArrayList<String> pictures){
        this.pathToPictures = pictures;
    }

    /**
     * Empty constructor
     */
    public Painting(){

    }

    /**
     * Get method to retrieve the width of the painting.
     * @return width of the painting in cm's.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set method to change the width listed of this Painting.
     * @param newWidth The new width of this painting in cm's.
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * Get method to retrieve the height of this painting.
     * @return Height of this painting in cm's.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set method to change the height of the painting.
     * @param newHeight THe new height of the painting in cm's.
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
