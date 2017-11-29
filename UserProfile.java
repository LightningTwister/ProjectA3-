import java.util.ArrayList;

public class UserProfile {

	private String userName;
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private String address;
	private String postCode;
	private String profilePicture;
	private int auctionID;
	ArrayList<Integer> currentAuctions = new ArrayList<Integer>();
	ArrayList<Integer> wonArtworks = new ArrayList<Integer>();.
	ArrayList<Integer> completedAuctions = new ArrayList<Integer>();
	ArrayList<Integer> bidHistory = new ArrayList<Integer>();
	ArrayList<Integer> FaveUsers = new ArrayList<Integer>();

	public UserProfile (String userName, String firstName, String lastName, int phoneNumber, String address, String postCode, String profilePicture){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postCode = postCode;
		this.profilePicture = profilePicture;

	}

	public void setUserName (String userName) {
		this.userName = userName;

	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;

	}

	public void setLastName (String lastName) {
		this.lastName = lastName;

	}

	public void setPhoneNumber (int phoneNumber) {
		this.phoneNumber = phoneNumber;

	}

	public void setAddress (String address) {
		this.address = address;

	}

	public void setPostCode (String postCode ) {
		this.postCode = postCode;

	}
	
	public String getUserName () {
		return userName;
		
	}
	
	public String getFirstName () {
		return firstName;
		
	}
	
	public String getLastName () {
		return lastName;
		
	}
	
	public int getPhoneNumber () {
		return phoneNumber;
		
	}
	
	public String getAddress () {
		return address;
		
	}
	
	public String getPostCode () {
		return postCode;
		
	}
	
	public String getFullAddress () {
		return address;
		return postCode;
		
	}
	
	public ArrayList<Integer> getFaveUsers () {
		return FaveUsers;

	}
	
	public String getProfilePicture () {
		return profilePicture;
		
	}
	
	public ArrayList<Integer> getWonArtworks () {
		return wonArtworks;
	
	}
	
	public ArrayList<Integer> getCompletedAuctions () {
		return completedAuctions;
		
	}
	
	public ArrayList<Integer> getBidHistory () {
		return bidHistory;
		
	}
	
	public void getCompletedAuction (int auctionID) {
		int retval=completedAuctions.get() = x; //x will be somin or other;
	}
	
	public boolean toggleFavouriteUser (int userID) {
		//Have no idea how to do this or even what it is supposed to do
	}
	
	public boolean isFavouriteUser (int userID) {
		//If statement userID = true then ?
	}
	
	public void changeProfilePicture (image newImage) {
		//Same as set statement???
		this.profilePicture = profilePicture;
	}
	
	public void addCurrentAuction (int auctionID) {
		//add auctionID to current auction
		currentAuctions.add();
	}
	
	public void addWonArtwork (int auctionID) {
		//add ArtworkID to wonArtwork
		wonArtworks.add();
	}
	
	public void addCompletedAuction (int auctionID) {
		//add auctionID to completedAuction
		completedAuctions.add();
	}
	
	public void addBidHistory (int bidID) {
		//add bidID to bidHistory 
		bidHistory.add();
	}
	
	public void removeCurrentAuction (int auctionID) {
		//remove (No idea how to remove) auctionID from currentAuctions
		//find position in list
		currentAuctions.remove();
	}
	
	public String toString() {
		String outputString = (" ") ;
		return outputString;
	}

}
