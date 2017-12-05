import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
* This class contains repeated code in static methods to be used by the whole program 
* @author Tim Watson 880158
* @version 1
*/
public class Utilities {
	/**
	* Create a pop up alert when a wrong input is found when saving objects
	*/
    public static void wrongInputFound(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong Input Found!");
        alert.showAndWait();
    }
	/**
	*Create a pop up alert when username entered can not be found
	*/
    public static void userNotFound(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Username not recognised!");
        alert.showAndWait();
    }
	/**
	* Create a pop up alert when the object successfully saved
	*/
    public static void savedInput(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setHeaderText(null);
        alert.setContentText("Saved!");
        alert.showAndWait();
    }
	/**
	* Create a pop up alert when a cancel button is pressed
	* to inform the user the editing has been cancelled.
	*/
    public static void cancelled(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancelled!");
        alert.setHeaderText(null);
        alert.setContentText("Cancelled");
        alert.showAndWait();
    }

    /**
     * If a selected artwork is not Sculpture or Painting create a pop up
     */
    public static void artworkSelectionFailed(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Selected index is not a Painting or a Sculpture");
        alert.showAndWait();
    }

    /**
     * Create a pop up alert when nothing is selected from a combo/list view
     */
    public static void nothingSelected(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Nothing was selected, please select something first!");
        alert.showAndWait();
    }
    /**
     * Create a pop up alert when nothing is selected from a combo/list view
     */
    public static void noImageFound(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("No image found, please specify a path for an image");
        alert.showAndWait();
    }
	/**
	* Temporary class that creates a pop up because class is not implemented yet
	* DELETE THIS *************************************************
	*/
    public static void classNotImplemented(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("CLASS NOT IMPLEMENTED!");
        alert.showAndWait();
    }
	/**
	* Method that checks all characters are alphabetical characters(or space)
	*
	*@param name The string to be checked
	*@return boolean if checks are met
	*/
    private static boolean isAlpha(String name){

        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c) && !(c == ' ') ) {
                return false;
            }
        }
        return true;
    }
	/**
	* Method that takes multiple Strings, each of which that need to be checked that
	* they only contain alphabetical characters
	* @param list multiple strings stored to be checked
	* @return boolean if all strings meet the criteria of the tests
	*/
    public static boolean checkInputAreAlpha(ArrayList<String> list){

        for(String a : list){
            if (!isAlpha(a)){
                return false;
            }
            if(!isEmpty(a)){
                return false;
            }
        }
        return true;
    }
	/**
	* Method that checks a string is not empty
	* @param name String value to be checked
	* @return boolean if the String is not empty
	*/
    private static boolean isEmpty(String name){
        if(name.trim().isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * Method that saves values entered in the gui to the sculpture object
     * @param newSculpture Reference to the object the values are being saved too
     * @param year value of the year the object was created
     * @param reservePrice value of the reserve price of this object
     * @param title Title of this artwork
     * @param height Height value in cm of this artwork
     * @param bids Number of bids allowed on this object
     * @param creatorName Name of the person who created this artwork
     * @param width Width value in cm of this artwork
     * @param depth Depth value in cm of this Sculpture
     * @param material Name of the main material this Sculpture is made of
     * @param userName Username of the person selling this artwork
     * @param desc A brief description of the artwork
     * @param id Identifier value of the artwork 
     */
    public static void saveSculpture(Sculpture newSculpture, int year, double reservePrice, int bids, int width, int height,
                        int depth, String creatorName,String userName, String material, String title, String desc,int id) {

        try {
            newSculpture.setArtworkYearCreated(year);
            newSculpture.setReservePrice(reservePrice);
            newSculpture.setNumOfBids(bids);
            newSculpture.setWidth(width);
            newSculpture.setHeight(height);
            newSculpture.setDepth(depth);

            newSculpture.setArtworkCreator(creatorName);
            newSculpture.setArtworkSeller(userName);
            newSculpture.setMaterial(material);
            newSculpture.setArtworkTitle(title);
            newSculpture.setArtworkDescription(desc);


        } catch (Exception e) {
            Utilities.wrongInputFound();
        }

    }

    /**
     *  Method that saves values entered in the gui to a painting object
     * @param newPainting The reference to an object to save the values too
     * @param year Value of the year this artwork was created
     * @param reservePrice Value of the minimum price this artwork can be sold for
     * @param bids Maximum number of bids allowed on this artwork
     * @param width Value of the width of this artwork in cm's
     * @param height Value of the height of this artwork in cm's
     * @param creatorName name of the person who created this painting
     * @param userName username of the person selling this artwork
     * @param title Name of this artwork
     * @param desc Brief description of what this artwork is
     * @param id Identifier value of this artwork
     */
    public static void savePainting(Painting newPainting, int year, double reservePrice, int bids, int width, int height
                                     , String creatorName,String userName, String title, String desc, int id){


        try{
            newPainting.setArtworkYearCreated(year);
            newPainting.setReservePrice(reservePrice);
            newPainting.setNumOfBids(bids);
            newPainting.setWidth(width);
            newPainting.setHeight(height);
            newPainting.setArtworkCreator(creatorName);
            newPainting.setArtworkSeller(userName);

            newPainting.setArtworkTitle(title);
            newPainting.setArtworkDescription(desc);


        }catch(Exception e){
            Utilities.wrongInputFound();
        }

    }

    /**
     * Method that updates a userprofile based on values entered in the gui
     * @param profile reference to the user profile being edited
     * @param userName display name of this user account
     * @param fName first name of the user of this account
     * @param lName last name of the user of this account
     * @param phoneNum phone number of the user of this account
     * @param address address of the user of this account, split by seperate lines
     * @param postcode post code of where the user of this account lives
     * @param profilePicture Image of the user/ to represent the user
     * @param id identifier value of this user account
     */
    public static void saveUser(UserProfiles profile,String userName, String fName, String lName, String phoneNum,
                                ArrayList<String> address, String postcode, String profilePicture, int id  ){
        try{
            profile.setUserName(userName);
            profile.setFirstName(fName);
            profile.setLastName(lName);
            profile.setPhoneNumber(phoneNum);

            profile.setAddress(address);
            profile.setPostCode(postcode);
            profile.setId(id);
            profile.setProfilePicture(profilePicture);



        }catch(Exception e){
            Utilities.wrongInputFound();
        }
    }

    /**
     * Method that checks that a username exists in the database
     * @param entered value that may be a username
     * @return boolean if the username is found
     */
    public static boolean checkUsername(String entered){
        for(UserProfiles a: Run.database.getAllUsers()){
            if(a.getUserName().equals(entered)){
                return true;
            }
        }
        return false;
    }
	
	/**
     *  Closes this window once it is not required.
	 @param rootPane pane of this window to be deleted
     */
    public static void closeWindow(Pane rootPane) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public static Image getImage(String path){
        try{
            Image image = new Image(path);
            return image;
        }catch (Exception e){
            noImageFound();
            return new Image("file:Data/SystemPictures/noImageFound.jpg");
        }
    }
    public static UserProfiles getUser(int id){
        int iterator = 0;
        for (UserProfiles a : Run.database.getAllUsers()){
            if (a.getId() == id){

                return a;
            }
            iterator++;
        }
        return null;
    }



}
