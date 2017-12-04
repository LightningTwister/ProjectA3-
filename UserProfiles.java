import java.util.ArrayList;
import java.util.Date;

public class UserProfiles {

	private String userName;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private ArrayList<String> address;
	private String postCode;
	private String profilePicture;
	private int auctionID;
	private Date logInDate;
	private int id;
	ArrayList<Integer> currentAuctions = new ArrayList<Integer>();
	ArrayList<Integer> wonArtworks = new ArrayList<Integer>();
	ArrayList<Integer> completedAuctions = new ArrayList<Integer>();
	ArrayList<Integer> bidHistory = new ArrayList<Integer>();
	ArrayList<Integer> FaveUsers = new ArrayList<Integer>();

	public UserProfiles (String userName, String firstName, String lastName, String phoneNumber, ArrayList<String> address, String postCode ,String profilePicture, int id){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postCode = postCode;
		//this.logInDate = logInDate;
		this.profilePicture = profilePicture;
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;

	}

	public void setAddress (ArrayList<String> address) {
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

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getLastName () {
		return lastName;
		
	}
	
	public String getPhoneNumber () {
		return phoneNumber;
		
	}
	
	public ArrayList<String> getAddress () {
		return address;
		
	}
	
	public String getPostCode () {
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
		//int retval=completedAuctions.get() = x; //x will be somin or other;
	}
	
	public void addCurrentAuction (int auctionID) {
		//add auctionID to current auction
		//currentAuctions.add();
	}
	
	public void addWonArtwork (int auctionID) {
		//add ArtworkID to wonArtwork
		//wonArtworks.add();
	}
	
	public void addCompletedAuction (int auctionID) {
		//add auctionID to completedAuction
		//completedAuctions.add();
	}
	
	public void addBidHistory (int bidID) {
		//add bidID to bidHistory 
		//bidHistory.add();
	}
	
	public void removeCurrentAuction (int auctionID) {
		//remove (No idea how to remove) auctionID from currentAuctions
		//find position in list
		//currentAuctions.remove();
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
