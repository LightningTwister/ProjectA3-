import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.stage.FileChooser;

/**
* This class contains repeated code in static methods to be used by the whole program 
* @author Tim Watson 880158
* @version 5
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
     * Create a popup to inform the user that the bid that was placed was not large enough
     */
	 public static void BidTooLow(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Bid too low/Already the highest bidder");
        alert.showAndWait();
    }

    /**
     * Create a popup for no images for this artwork were found.
     */
    public static void noImagesInList(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("There are now no images for this artwork, default image added!");
        alert.showAndWait();
    }

    /**
     * Create a pop up to inform the user they cannot add anymore pictures
     */
    public static void maximumPicturesReached(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("You can't have more than one image for a painting!");
        alert.showAndWait();
    }

    /**
     * Create a pop up to inform the user this username is taken and is unavailable
     */
    public static void userNameTaken(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Username taken, select another");
        alert.showAndWait();
    }
    /**
     *
     * Create a pop up to inform the user the input address is the wrong size
     */
    public static void addressSizeWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Addresses must be three lines long!");
        alert.showAndWait();
    }
    /**
     * Create a pop up to inform the user the input address is the wrong size
     */
    public static void noArtworksInDatabase(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("There are no artworks in the database");
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
     * @param userId Username of the person selling this artwork
     * @param desc A brief description of the artwork
     * @param id Identifier value of the artwork 
     */
    public static void saveSculpture(Sculpture newSculpture, int year, double reservePrice, int bids, int width, int height,
                        int depth, String creatorName,int userId, String material, String title, String desc,int id,ArrayList<String> picLocation) {

        try {
            newSculpture.setArtworkYearCreated(year);
            newSculpture.setReservePrice(reservePrice);
            newSculpture.setNumOfBids(bids);
            newSculpture.setWidth(width);
            newSculpture.setHeight(height);
            newSculpture.setDepth(depth);
            newSculpture.setId(id);
            newSculpture.setArtworkCreator(creatorName);
            newSculpture.setArtworkSeller(userId);
            newSculpture.setMaterial(material);
            newSculpture.setArtworkTitle(title);
            newSculpture.setArtworkDescription(desc);
            newSculpture.setPicture(picLocation);


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
     * @param userId username of the person selling this artwork
     * @param title Name of this artwork
     * @param desc Brief description of what this artwork is
     * @param id Identifier value of this artwork
     */
    public static void savePainting(Painting newPainting, int year, double reservePrice, int bids, int width, int height
                                     , String creatorName,int userId, String title, String desc, int id, ArrayList<String> picLocation){

        try{
            newPainting.setArtworkYearCreated(year);
            newPainting.setReservePrice(reservePrice);
            newPainting.setNumOfBids(bids);
            newPainting.setWidth(width);
            newPainting.setHeight(height);
            newPainting.setArtworkCreator(creatorName);
            newPainting.setArtworkSeller(userId);
            newPainting.setId(id);
            newPainting.setArtworkTitle(title);
            newPainting.setArtworkDescription(desc);
            newPainting.setPicture(picLocation);


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
            profile.setLogInDate(getDate());



        }catch(Exception e){
            e.printStackTrace();
            Utilities.wrongInputFound();
        }
    }

    /**
     * Method that checks that a username exists in the database
     * @param entered value that may be a username
     * @return boolean if the username is found
     */
    public static boolean checkUsername(String entered){
        for(UserProfiles a: Main.database.getAllUsers()){
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

    /**
     *  Find an image based on the path given
     * @param path Path to the image being searched for
     * @return Image that can be used by the system to be displayed
     */
    public static Image getImage(String path){
        try{
            Image image = new Image(path);
            return image;
        }catch (Exception e){
            noImageFound();
            return new Image(Main.database.NO_IMAGE_PATH);
        }
    }

    /**
     *  Get a user profile based on their id number
     * @param id number of the user profile
     * @return User profile that matches this id number
     */
    public static UserProfiles getUser(int id){
        int iterator = 0;
        for (UserProfiles a : Main.database.getAllUsers()){
            if (a.getId() == id){

                return a;
            }
            iterator++;
        }
        return null;
    }

    /**
     *  Check that images were added worked
     * @param paths Paths to images
     * @return True or false if the pictures exist
     */
    public static boolean checkImagesAdded(ArrayList<String> paths){
        if(paths == null){
            return false;
        }
        if(paths.size() == 0){
            Utilities.noImageFound();
            return false;
        }else if(paths.size() == 1 && paths.get(0).equals(Main.database.NO_IMAGE_PATH)){
            Utilities.noImageFound();
            return false;
        }
        return true;
    }

    /**
     *  Opens a window to change or add a picture and returns the path to this new selected image
     * @param title Title of this window
     * @param folder Destination to open the file manager at
     * @return Path to image
     */
    public static String changeImage(String title,String folder){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(folder));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter
                ("Image types","*.jpeg","*.jpg","*.png"));

        Stage fileOpen = new Stage();
        File file = fileChooser.showOpenDialog(fileOpen);
        if (file != null){
            Path source = Paths.get(file.toURI());


            Path directory = Paths.get(folder + "/"+file.getName());

            try{
                Files.copy(source,directory, StandardCopyOption.REPLACE_EXISTING);

                return directory.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
           
        }
        return "FAILED";
    }

    /**
     * Validation method to check a phone number is a valid uk mobile number
     * @param phoneNum String to be validated to be of the right type
     * @return True or false if the phone number passes validation
     */
    public static boolean checkPhoneNum(String phoneNum){

        return (phoneNum.charAt(0) == '0' && phoneNum.charAt(1) == '7' &&
                phoneNum.length() == 11 && phoneNum.matches("[0-9]+"));
    }

    /**
     * Validate post code to check it is a valid uk postcode
     * @param postcode String to be validated
     * @return Boolean if validation passes or not
     */
    public static boolean checkPostCode(String postcode){


        return postcode.matches("^(([gG][iI][rR] {0,}0[aA]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y]?[0-9][0-9]?)" +
                "| (([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9] " +
                "[abehmnprv-yABEHMNPRV-Y]))) {0,}[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$");
    }

    /**
     * Method to check the user details that are entered are all the correct type and exist
     * @param info List of details to be checked
     * @return Boolean if validation passes
     */
    public static boolean checkUserDetails(ArrayList<Object> info){

        boolean check = true;
        try{

            for(Object a: info){

                if(a.toString().isEmpty()){
                    check = false;
                    return check;
                }

            }

            String postCode = (String) info.get(3);
            if(!(Utilities.checkPostCode(postCode))){
                return false;
            }

            String phoneNumber = ((String) info.get(4));
            if(!(Utilities.checkPhoneNum(phoneNumber))){
                return false;
            }


        }catch (Exception e){
            e.printStackTrace();
            Utilities.wrongInputFound();
            return false;
        }


        return check;

    }

    /**
     * Return the date and time of now
     * @return Formatted string of date and time now
     */
    public static String getDate(){
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("EEE dd MMM yyyy hh:mm:ss");
        String reportDate = df.format(today);
        return reportDate;
    }
}
