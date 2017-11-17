/**
 * Class to create a Painting Object.
 * @author Tim Watson 880158
 * @version 1
 */
public class Painting extends Artwork {
    private int width;
    private int height;
    
    /**
     * Constructor to instantiate a painting object.
     * @param artworkTitle Name of Painting.
     * @param artworkDescription Description of Painting.
     * @param artworkCreator Creator of the Painting.
     * @param artworkYearCreated Year the Painting was created.
     * @param reservePrice The minimum price the Painting can be sold for.
     * @param numOfBids Maximum number of bids on the painting.
     * @param userNameSeller Name of the seller of the Painting.
     * @param width Width of the painting in cm's.
     * @param height Height of the painting in cm's.
     */
    public Painting(String artworkTitle, String artworkDescription, 
                String artworkCreator, int artworkYearCreated, 
                    double reservePrice, int numOfBids, String userNameSeller, 
                        int width, int height) {
        
        super(artworkTitle, artworkDescription, artworkCreator, 
                artworkYearCreated, reservePrice, numOfBids, userNameSeller);
        this.width = width;
        this.height = height;

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
}
