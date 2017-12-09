import java.util.ArrayList;

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
    private ArrayList<String> pathToPictures;
    //To do, additional photos.



    /**
     * Constructor to instantiate a Sculpture object.
     * @param artworkTitle Name of Sculpture.
     * @param artworkDescription Description of Sculpture.
     * @param artworkCreator Name of the person who created the Sculpture.
     * @param artworkYearCreated Year the Sculpture was created.
     * @param reservePrice Minimum price the sculpture can be sold for.
     * @param numOfBids Maximum number of bids allowed on the sculpture.
     * @param sellerId User name of the person selling this Sculpture.
     * @param width Width of the sculpture in cm's.
     * @param height Height of the sculpture in cm's.
     * @param depth Depth of the sculpture in cm's.
     * @param material The material the sculpture is made out of.
     */
    public Sculpture(String artworkTitle, String artworkDescription, String artworkCreator, int artworkYearCreated,
                     double reservePrice, int numOfBids,int sellerId, int width, int height,int id,
                     int depth, String material,ArrayList<String> picture) {
        super(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated, reservePrice, numOfBids, sellerId, id, picture.get(0));

        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
        this.pathToPictures = picture;

    }
    //For File I/O
    public Sculpture(String artworkTitle, String artworkDescription, String artworkCreator, int artworkYearCreated,
                     double reservePrice, int numOfBids,int sellerId, int width, int height,int id,
                     int depth, String material,ArrayList<String> picture, Bid highestBid, ArrayList<Integer> bidHistory) {

        super(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated, reservePrice, numOfBids,
                sellerId, id, picture.get(0), highestBid, bidHistory);

        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
        this.pathToPictures = picture;

    }


    public ArrayList<String> getPictures() {
        return pathToPictures;
    }

    public ArrayList<String> getPathToPictures() {
        return pathToPictures;
    }

    public void setPathToPictures(ArrayList<String> pathToPictures) {
        this.pathToPictures = pathToPictures;
    }

    public void addPicture(String picture) {
        this.pathToPictures.add( picture);
    }
    public void removePicture(String path){
        for(String artworkPath: this.pathToPictures){
            if (path.equals(artworkPath)){
                this.pathToPictures.remove(artworkPath);
            }
        }
    }
    public void setPicture(ArrayList<String> pictures){
        this.pathToPictures = pictures;
    }

    public Sculpture(){

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

    @Override
    public String toString() {
        return "Sculpture{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", material='" + material + '\'' +
                "} " + super.toString();
    }

}
