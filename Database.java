import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LT on 10/11/2017.
 */
public class Database {

    public final static String ARTWORK_PATH = "Data/artworkfile.txt";
    public final static String ARTWORK_SPECIFIER = "artworklist";
    public final static String ARTWORK_FOLDER_PATH = "Data/ArtworkPictures";
    public final static String USER_PATH = "Data/UserList.txt";
    public final static String USER_SPECIFIER = "userlist";
    public final static String PROFILE_PATH = "Data/ProfilePictures/";
    public final static String DEFAULT_PROFILE_PATH = "Data/ProfilePictures/Built In";
    public final static String BID_PATH = "Data/bidlist.txt";
    public final static String BID_HISTORY_PATH = "Data/bidhistory.txt";
    public final static String BANNER_PATH = "file:Data/SystemPictures/Artatawe Banner.png";
    public final static String ICON_PATH = "file:Data/SystemPictures/Artatawe Logo.jpg";
    public final static String NO_IMAGE_PATH = "file:Data/SystemPictures/noImageFound.jpg";
    //private final String BID_PATH;
    private UserProfiles currentUser;
    private HashMap<Integer, UserProfiles> users = new HashMap<>();
    private HashMap<Integer, Artwork> artworks = new HashMap<>();
    private static HashMap<Integer, Bid> bids = new HashMap<>();

    /**
     * Constructor that instantiates a database
     */
    public Database() {
        login();
    }

    /**
     * Method that sets the current user to the one that logged in
     *
     * @param a
     */
    public void setCurrentUser(UserProfiles a) {
        this.currentUser = a;
    }

    /**
     * Get method that returns a current user
     *
     * @return The profile of the users using the system at the moment
     */
    public UserProfiles getCurrentUser() {
        return currentUser;
    }

    /**
     * Method that runs when the database is created to get data from files
     */
    private void login() {
        loadArtworks();
        loadUsers();
        loadBids();

    }

    /**
     * Get method that returns a specific user based on a username
     *
     * @param username of the user being searched for
     * @return A user profile
     */
    public UserProfiles getUser(String username) {
        for (UserProfiles a : users.values()) {
            if (a.getUserName().equals(username)) {
                //a.setLogInDate(Utilities.getDate());
                return a;

            }
        }
        return null;
    }

    /**
     * Method that gets users from a file and stores them
     */
    private void loadUsers() {
        users = FileReader.readFile(USER_PATH, USER_SPECIFIER);
    }

    /**
     * Method that gets artworks from a file and stores them
     */
    private void loadArtworks() {
        artworks = FileReader.readFile(ARTWORK_PATH, ARTWORK_SPECIFIER);
    }

    /**
     * Method that loads the bids to the system
     */
    private void loadBids() {
        this.bids = FileReader.readFile(BID_PATH,"bidlist");
        bids.put(3,new Bid(1,99.2,1,9,"10 Dec 2017 16:01:25"));
        System.out.println(getBidsIO().size());
        for(Bid a: getAllBids()){
            System.out.println(a);
        }
        saveBids();


    }

    /**
     * Return all artworks stored
     *
     * @return List of all artworks
     */
    public ArrayList<Artwork> getAllArtworks() {
        ArrayList<Artwork> aArtworks = new ArrayList<>();
        if (artworks.isEmpty()) {
            return aArtworks;
        } else {
            for (Artwork a : artworks.values()) {
                aArtworks.add(a);
            }
            return aArtworks;
        }
    }
    public static ArrayList<Bid> getAllBids(){
        ArrayList<Bid> returnBids = new ArrayList<>();
        if (bids.isEmpty()){
            return returnBids;
        }else{
            for(Bid a: bids.values()){
                returnBids.add(a);
            }
            return returnBids;
        }
    }

    /**
     * Return all  users
     *
     * @return List of all users
     */
    public ArrayList<UserProfiles> getAllUsers() {
        ArrayList<UserProfiles> aUsers = new ArrayList<>();
        for (UserProfiles a : users.values()) {

            aUsers.add(a);
        }
        return aUsers;
    }

    /**
     * Add artwork to the database
     *
     * @param a New artwork to be added
     */
    public void addArtwork(Artwork a) {
        artworks.put(Integer.valueOf(a.getId()), a);
    }

    /**
     * Saves all artworks stored in database to file
     */
    public void saveArtwork() {
        FileWriter.openFile(ARTWORK_PATH, ARTWORK_SPECIFIER);
    }

    /**
     * Saves all users stored in the database to file
     */
    public void saveUsers() {
        FileWriter.openFile(USER_PATH, USER_SPECIFIER);
    }

    public ArrayList<Bid> getBidHistory() {
        ArrayList<Integer> idList = currentUser.getBidHistory();
        ArrayList<Bid> result = new ArrayList<Bid>();
        for (int id : idList) {
            result.add(bids.get(id));
        }
        return result;
    }

    public void saveBids(){
        FileWriter.openFile(BID_PATH,"bidlist");
    }

    /**
     * Return the bid history for a bid object
     */
    public ArrayList<String> getBidHistory(int id) {
        ArrayList<Integer> idList = artworks.get(id).getBidHistory();
        ArrayList<String> result = new ArrayList<>();
        UserProfiles user;
        Bid currBid;
        for (int bid : idList) {
            currBid = bids.get(bid);
            user = users.get(currBid.getUserID());
            result.add("Username of Bidder: " + user.getUserName() + "Amount Bid: " + currBid.getAmount()
                    + " on " + currBid.getDatePlaced());
        }

        return result;
    }

    /**
     * Place a new bid on an artwork
     *
     * @param amount Amount being placed on the artwork
     * @param artID  Artwork Identifier
     * @throws Exception
     */
    public void placeBid(double amount, int artID) throws Exception {
        Artwork art = this.getArtwork(artID);
        int bid = this.getNextIDBid();
        Bid currentHighest = art.getHighestBid();
        if (currentHighest.getUserID() == currentUser.getId()) {
            throw new Exception("You can't bid again");
        }
        Bid newBid = new Bid(bid, amount, currentUser.getId(), artID, currentHighest);
        art.placeBid(newBid);
        currentUser.addBid(bid);
        bids.put(bid, newBid);
        if (art.getNumOfBids() == 0) {
            currentUser.addWonArtwork(newBid.getBidID());
        }
    }

    /**
     * Get the list of artworks based on which type you want
     *
     * @param typeOfList Parameter to determine which artworks are wanted
     * @return List of appropriate artworks
     */
    public ArrayList<Artwork> getArtworkList(String typeOfList) {
        int paint1Sculp2All = -1;
        switch (typeOfList) {
            case "Painting":
                paint1Sculp2All = 0;
                break;
            case "Sculpture":
                paint1Sculp2All = 1;
                break;
            case "All":
                break;
            default:
                System.out.println("ERROR: invalid getArtwork query");
                return new ArrayList<Artwork>();
        }

        ArrayList<Artwork> result = new ArrayList<Artwork>();
        for (Map.Entry artwork : artworks.entrySet()) {
            if (paint1Sculp2All == 1) {
                if (artwork.getValue() instanceof Sculpture) {
                    result.add((Artwork) artwork.getValue());
                }
            } else if (paint1Sculp2All == 0) {
                if (artwork.getValue() instanceof Painting) {
                    result.add((Artwork) artwork.getValue());
                }
            } else {
                result.add((Artwork) artwork.getValue());
            }

            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getNumOfBids() == 0) {
                    result.remove(i);
                }
            }


        }
        return result;
    }


    /**
     * Return a list of artworks where the auction has ended
     *
     * @return List of artworks that have been bought
     */
    public ArrayList<Artwork> getCompletedAuctions() {
        ArrayList<Artwork> artworks = new ArrayList<>();
        ArrayList<Integer> artIDs = currentUser.getCompletedAuctions();
        for (int id : artIDs) {
            artworks.add(this.artworks.get(id));

        }
        return artworks;
    }

    /**
     * Get auctions that have been won by the current user
     *
     * @return List of artworks that the current user has won
     */
    public ArrayList<Artwork> getWonAuctions() {
        ArrayList<Artwork> artworks = new ArrayList<>();
        ArrayList<Integer> artIDs = currentUser.getWonArtworks();
        for (int id : artIDs) {
            artworks.add(this.artworks.get(id));

        }
        return artworks;
    }

    /**
     * Create a new user for the artatawe system
     *
     * @param info A list of data that has been created for the user
     * @return Boolean to determine if the user has been created with valid data
     */
    public boolean createUser(ArrayList<Object> info) {
        Integer userID = getNextIDProfile();

        try {

            String userName = (String) info.get(0);
            String firstName = (String) info.get(1);
            String lastName = (String) info.get(2);
            String postCode = (String) info.get(3);
            String phoneNumber = ((String) info.get(4));

            ArrayList<String> address = (ArrayList<String>) info.get(5);
            String profilePicture = (String) info.get(6);

            ArrayList<Integer> fUsers = new ArrayList<>();

            if (!(Utilities.checkUserDetails(info))) {
                return false;
            }
            for (UserProfiles profile : this.getAllUsers()) {
                if (profile.getUserName().equalsIgnoreCase(userName)) {
                    Utilities.userNameTaken();
                    return false;
                }
            }
            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);


            UserProfiles newUser = new UserProfiles(userName, firstName, lastName, phoneNumber, address, postCode,
                    profilePicture, userID, reportDate, fUsers);
            users.put(userID, newUser);
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.wrongInputFound();
            return false;
        }


        return true;

    }

    /**
     * get the next available id for a profile
     *
     * @return An available id for a new profile
     */
    private Integer getNextIDProfile() {
        if (users.size() == 0) return 1;
        Integer id = 0;
        Integer currentID = 0;
        for (Map.Entry current : users.entrySet()) {
            currentID = (Integer) current.getKey();
            if (currentID > id) {
                id = currentID;
            }
        }
        id++;
        return id;
    }

    /**
     * get the next available id fro a bid object
     *
     * @return An available id for a new bid
     */
    private Integer getNextIDBid() {
        if (bids.size() == 0) return 1;
        Integer id = 0;
        Integer currentID = 0;

        for (Map.Entry current : bids.entrySet()) {
            currentID = (Integer) current.getKey();
            if (currentID > id) {
                id = currentID;
            }
        }
        id++;

        return id;
    }

    /**
     * Find a user profile based on its id number
     *
     * @param id of the user you are looking for
     * @return The uersprofile of the user searching for
     */
    public UserProfiles getUser(Integer id) {

        return Utilities.getUser(id);
    }

    /**
     * Get an artwork based on its id number
     *
     * @param id of the artwork being searched for
     * @return Artwork searched for
     */
    public Artwork getArtwork(Integer id) {
        return artworks.get(id);
    }

    /**
     * Get a bid object based on its id number
     *
     * @param id of the bid object being searched for
     * @return The bid object
     */
    public Bid getBid(Integer id) {
        return bids.get(id);
    }

    public ArrayList<Bid> getBidsIO() {

        ArrayList<Bid> result = new ArrayList<Bid>();

        for (Bid n : bids.values()) {

            result.add(n);

        }


        return result;

    }
}
