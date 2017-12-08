import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

public class UserProfiles {

	private final String PHONE_REGEX = "^0[0-9]{10}$";
	private final String POSTCODE_REGEX = "^([A-PR-UWYZ](([0-9](([0-9]|[A-HJKSTUW])?)?)|([A-HK-Y][0-9]([0-9]|[ABEHMNPRVWXY])?)) ?[0-9][ABD-HJLNP-UW-Z]{2})$";
	private String userName;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private ArrayList<String> address;
	private String postCode;
	private String profilePicture;
	private int auctionID;
	private String logInDate;
	private int id;
	private ArrayList<Integer> currentAuctions;
	private ArrayList<Integer> wonArtworks;
	private ArrayList<Integer> completedAuctions;
	private ArrayList<Integer> bidHistory;
	private ArrayList<Integer> faveUsers;



	/**
	 * Constructor for existing user used by the filereader to read the files

	 * @param id id of the user main distinguishing characteristic of the user
	 * @param userName username of the user
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param phoneNumber phone number of the user
	 * @param address address of the user
	 * @param postCode post code of the user
	 * @param profilePicture profile picture of the user
	 * @param faveUsers list of favourite users of the user
	 * @param currentAuctions list of IDs of current auctions the user has on the site
	 * @param wonArtworks list of IDs auctions the user has won on the site
	 * @param completedAuctions list of auction IDs the user has put up which have since finished
	 * @param bidHistory list of bid IDs of bids the user has placed
	 */
	public UserProfiles (int id, String userName, String firstName, String lastName, String phoneNumber,
						 ArrayList<String> address, String postCode ,String profilePicture,
						 ArrayList<Integer> faveUsers, ArrayList<Integer> currentAuctions, ArrayList<Integer> wonArtworks,
						 ArrayList<Integer> completedAuctions, ArrayList<Integer> bidHistory){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postCode = postCode;
		//this.logInDate = logInDate;
		this.profilePicture = profilePicture;
		this.id = id;

		this.faveUsers = faveUsers;
		this.currentAuctions = currentAuctions;
		this.wonArtworks = wonArtworks;
		this.completedAuctions = completedAuctions;
		this.bidHistory = bidHistory;
		this.faveUsers = faveUsers;
	}

	/**
	 *
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param address
	 * @param postCode
	 * @param profilePicture
	 * @param id
	 * @param fUsers
	 */
	public UserProfiles (String userName, String firstName, String lastName, String phoneNumber,
						 	ArrayList<String> address, String postCode ,String profilePicture, int id,
								 ArrayList<Integer> fUsers, String date){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postCode = postCode;
		this.logInDate = date;
		this.profilePicture = profilePicture;
		this.id = id;
		this.faveUsers = fUsers;

		currentAuctions = new ArrayList<Integer>();
		wonArtworks = new ArrayList<Integer>();
		completedAuctions = new ArrayList<Integer>();
		bidHistory = new ArrayList<Integer>();
		//faveUsers = new ArrayList<Integer>();

	}

	public String getLogInDate() {
		return logInDate;
	}

	public void setLogInDate(String logInDate) {
		this.logInDate = logInDate;
	}


	/**
	 * Sets userID
	 * @param id id of the user
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the username of the user
	 * @param userName the username of the user
	 */
	public void setUserName (String userName) {
		this.userName = userName;

	}

	/**
	 * Sets the first name of the user
	 * @param firstName the first name  of the user
	 */
	public void setFirstName (String firstName) {
		this.firstName = firstName;

	}

	/**
	 * Sets the surname of the user
	 * @param lastName the surname of the user
	 */
	public void setLastName (String lastName) {
		this.lastName = lastName;

	}

	/**
	 * Sets the phone number of the user
	 * @param phoneNumber the phone number of the user
	 */
	public void setPhoneNumber (String phoneNumber) {
		if(Utilities.checkPhoneNum(phoneNumber)){
			this.phoneNumber = phoneNumber;
		}else{
			Utilities.wrongInputFound();
		}

	}

	/**
	 * Sets the address of the user
	 * @param address the address of the user
	 */
	public void setAddress (ArrayList<String> address) {
		this.address = address;
	}

	/**
	 * Sets the postcode of the user
	 * @param postCode the postcode of the user
	 */
	public void setPostCode (String postCode) {
		if(Utilities.checkPostCode(postCode)){
			this.postCode = postCode;
		}else{
			Utilities.wrongInputFound();
		}

	}

	/**
	 * Sets the profile picture of the user
	 * @param profilePicture the profile picture of the user
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * gets the id of the user
	 * @return the id of the user
	 */
	public int getId() {
		return id;
	}

	/**
	 * gets the username of the user
	 * @return the username of the user
	 */
	public String getUserName () {
		return userName;
		
	}

	/**
	 * gets the first name of the user
	 * @return the first name of the user
	 */
	public String getFirstName () {
		return firstName;
		
	}

	/**
	 * gets the surname of the user
	 * @return the surname of the user
	 */
	public String getLastName () {
		return lastName;
		
	}

	/**
	 * gets the phone number of the user
	 * @return the phone number of the user
	 */
	public String getPhoneNumber () {
		return phoneNumber;
		
	}

	/**
	 * gets the address of the user
	 * @return the address of the user
	 */
	public ArrayList<String> getAddress () {
		return address;
		
	}

	/**
	 * gets the postcode of the user
	 * @return the postcode of the user
	 */
	public String getPostCode () {
		return postCode;
		
	}

	/**
	 * gets the profile picture of the user
	 * @return the profile picture of the user
	 */
	public String getProfilePicture () {
		return profilePicture;
		
	}

	/**
	 * gets the list of favourite users the user has
	 * @return the list of favourite users the user has
	 */
	public ArrayList<Integer> getFaveUsers () {
		return faveUsers;

	}

	/**
	 * gets the list of the artworks the user has won
	 * @return the list of artworks the user has won
	 */
	public ArrayList<Integer> getWonArtworks () {
		return wonArtworks;
	
	}

	/**
	 * gets the list of completed auctions the user has
	 * @return the list of completed auctions the user has
	 */
	public ArrayList<Integer> getCompletedAuctions () {
		return completedAuctions;
		
	}

	/**
	 * gets the list of current auctions the user has
	 * @return the list of current auctions the user has
	 */
	public ArrayList<Integer> getCurrentAuctions () {
		return currentAuctions;

	}

	/**
	 * gets the list of bids the user has placed
	 * @return the list of bids the user has placed
	 */
	public ArrayList<Integer> getBidHistory () {
		return bidHistory;
		
	}


	/**
	 * adds an auction to current auctions the user has
	 * @return the list of current auctions the user has
	 */
	public void addCurrentAuction (int auctionID) {
		currentAuctions.add(auctionID);
	}

	/**
	 * adds an artwork id to the list of artworks the user has won
	 * @param auctionID the id of the artwork the user has won
	 */
	public void addWonArtwork (int auctionID) {
		wonArtworks.add(auctionID);
	}


	/**
	 * Method for changing an active auction to conpleted status
	 * @param artworkID
	 */
	public void completeAuction(int artworkID){
		boolean existed = false;
		for(int i=0;i < currentAuctions.size(); i++){
			if(currentAuctions.get(i) == artworkID){
				existed = true;
				currentAuctions.remove(i);
			}
		}

		if(existed){
			completedAuctions.add(artworkID);
		}
	}



	/**
	 * WTF IS THIS ?
	 * @param id
	 */
	public void toggleFaveUser(int id){
		boolean existed = false;
		for (int i = 0; i < faveUsers.size();i++){
			if (faveUsers.get(i) == id){
				faveUsers.remove(i);
				existed = true;
			}
		}

		if(!existed){
			faveUsers.add(id);
		}
	}
	public void addBid(int bidid){
		bidHistory.add(bidid);
	}


	@Override
	public String toString() {
		return "UserProfile{" +
				"userName='" + userName + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber=" + phoneNumber +
				", address=" + address +
				", postCode='" + postCode + '\'' +
				", profilePicture='" + profilePicture + '\'' +
				", auctionID=" + auctionID +
				", logInDate=" + logInDate +
				", id=" + id +
				'}';
	}
}
