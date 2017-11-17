
/**
 * Class to create a sculpture Object.
 * @author Tim Watson 880158
 * @version 1
 */
public class Sculpture extends Artwork {
    private int width;
    private int height;
    private int depth;
    private String material;
    //To do, additional photos.

    /**
     * Constructor to instantiate a Sculpture object.
     * @param artworkTitle Name of Sculpture.
     * @param artworkDescription Description of Sculpture.
     * @param artworkCreator Name of the person who created the Sculpture.
     * @param artworkYearCreated Year the Sculpture was created.
     * @param reservePrice Minimum price the sculpture can be sold for.
     * @param numOfBids Maximum number of bids allowed on the sculpture.
     * @param sellerUserName User name of the person selling this Sculpture.
     * @param width Width of the sculpture in cm's.
     * @param height Height of the sculpture in cm's.
     * @param depth Depth of the sculpture in cm's.
     * @param material The material the sculpture is made out of.
     */
    public Sculpture(String artworkTitle, String artworkDescription, String artworkCreator, int artworkYearCreated, double reservePrice, int numOfBids,String sellerUserName, int width, int height, int depth, String material) {
        super(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated, reservePrice, numOfBids, sellerUserName);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
    }

    /**
     * Get method to return the width of the sculpture in cm's.
     * @return width .
     */
    public int getWidth() {
        return width;
    }

    /**
     *  Set method to change the width of the sculpture.
     * @param newWidth The new width.
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * Get method to return the height of the sculpture
     * @return height of the sculpture in cm's.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set method to change the height of the sculpture stored.
     * @param newHeight new height of the sculpture in cm's.
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    /**
     * Get method to retrieve the depth of the sculpture.
     * @return The depth of the sculpture in cm's.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Set method to change the depth of the Sculpture.
     * @param newDepth The new depth of the sculpture.
     */
    public void setDepth(int newDepth) {
        this.depth = newDepth;
    }

    /**
     * Get method to retrieve the material of this sculpture.
     * @return material .
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Set method to change the material of this Sculpture Object.
     * @param newMaterial The new material of the Sculpture.
     */
    public void setMaterial(String newMaterial) {
        this.material = newMaterial;
    }
}
