import sun.plugin.util.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LT on 10/11/2017.
 */
public class Database {

    private final String ARTWORK_PATH ="Data/artworkfile.txt";
    private final String USER_PATH = "Data/UserList.txt";
    public final String BANNER_PATH = "file:Data/Artatawe Banner.png";
    //private final String BID_PATH;
    private UserProfiles currentUser;
    private HashMap<Integer, UserProfiles> users = new HashMap<>();
    private HashMap<Integer, Artwork> artworks = new HashMap<>();
   // private HashMap<Integer, Bid> bids;

    /**
     * Constructor that instantiates a database
     */
    public Database(){
        login();
    }

    /**
     *  Method that sets the current user to the one that logged in
     * @param a
     */
    public  void setCurrentUser(UserProfiles a){
        this.currentUser = a;
    }

    /**
     *  Get method that returns a current user
     * @return
     */
    public UserProfiles getCurrentUser() {
        return currentUser;
    }

    /**
     * Method that runs when the database is created to get data from files
     */
    private void login(){
        loadArtworks();
        loadUsers();
    }

    /**
     *  Get method that returns a specific user based on a username
     * @param username of the user being searched for
     * @return A user profile
     */
    public UserProfiles getUser(String username){
        for(UserProfiles a: users.values()){
            if (a.getUserName().equals(username)){
                return a;
            }
        }
        return null;
    }

    /**
     *  Method that gets users from a file and stores them
     */
    private void loadUsers(){
        ArrayList<UserProfiles> rProfiles = new ArrayList<>();
            rProfiles= FileReader.readFile(USER_PATH,"userlist");

        for(UserProfiles a: rProfiles){
                users.put(Integer.valueOf(a.getId()),a);
        }
    }

    /**
     *  Method that gets artworks from a file and stores them
     */
    private void loadArtworks(){

        ArrayList<Artwork> rArtworks = new ArrayList<Artwork>();
                rArtworks=FileReader.readFile(ARTWORK_PATH,"artworkList");


        for(Artwork a: rArtworks){

                artworks.put(Integer.valueOf(a.getId()),a);
        }
    }

    /**
     * Return all artworks stored
     * @return List of all artworks
     */
    public ArrayList<Artwork> getAllArtworks(){
        ArrayList<Artwork> aArtworks = new ArrayList<>();
        for(Artwork a: artworks.values()){

            aArtworks.add(a);
        }
        return aArtworks;
    }

    /**
     *  Return all  users
     * @return List of all users
     */
    public ArrayList<UserProfiles> getAllUsers(){
        ArrayList<UserProfiles> aUsers = new ArrayList<>();
        for(UserProfiles a: users.values()){

            aUsers.add(a);
        }
        return aUsers;
    }

    /**
     * Add artwork to the database
     * @param a New artwork to be added
     */
    public void addArtwork(Artwork a){
        artworks.put(Integer.valueOf(a.getId()),a);
    }

    /**
     * Saves all artworks stored in database to file
     */
    public void saveArtwork(){
        FileWriter.openFile("Data/artworkfile1.txt","artworkList");
    }

    /**
     * Saves all users stored in the database to file
     */
    public void saveUsers(){
        FileWriter.openFile("Data/Userlist1.txt", "userlist");
    }


   // public ArrayList<Bid> getBidHistory(){
    //    ArrayList<Integer> idList = currentUser.getBids();
    //    ArrayList<Bid> result = new ArrayList<Bid>();
   //     for(int id : idList){
    //            result.add(bids.get(id));
   //     }
    //    return result;
    //}
    //public ArrayList<Bid> getBidHistory(int id){
     //   ArrayList<Integer> idList = artworks.get(id).getBids();
     //   ArrayList<Bid> result = new ArrayList<Bid>();
     //   for(int bid : idList){
     //       result.add(bids.get(bid));
     //   }
     //   return result;
    //}

    public ArrayList<Artwork> getArtworkList(String typeOfList){
        int paint1Sculp2All = -1;
        switch (typeOfList){
            case "Painting": paint1Sculp2All = 0; break;
            case "Sculpture": paint1Sculp2All = 1; break;
            case "All": break;
            default: System.out.println("ERROR: invalid getArtwork query"); return new ArrayList<Artwork>();
        }

        ArrayList<Artwork> result = new ArrayList<Artwork>();
        for (Map.Entry artwork : artworks.entrySet())
        {
            if(paint1Sculp2All == 1){
                if (artwork.getValue() instanceof  Sculpture) {
                    result.add((Artwork) artwork.getValue());
                }
            }else if(paint1Sculp2All == 0){
                if (artwork.getValue() instanceof  Painting) {
                    result.add((Artwork) artwork.getValue());
                }
            }else {
                result.add((Artwork) artwork.getValue());
            }


        }
        return result;
    }

    public static HashMap<Integer, Artwork> browseArtworks(HashMap<Integer, Artwork> artworkList, boolean isPaintings){
        HashMap<Integer, Artwork> result = new HashMap<Integer, Artwork>();
        for (Map.Entry artwork : artworkList.entrySet())
        {
            if(isPaintings){
                if (artwork.getValue() instanceof  Sculpture) {
                    result.put((Integer) artwork.getKey(), (Artwork) artwork.getValue());
                }
            }else{
                if (artwork.getValue() instanceof  Painting) {
                    result.put((Integer) artwork.getKey(), (Artwork) artwork.getValue());
                }
            }


        }
        return result;
    }

    //public ArrayList<UserProfile> getUserList(){
    //    ArrayList<UserProfile> result = new ArrayList<UserProfile>();
    //    for(int id : currentUser.getFaveUsers()){
    //        result.add(users.get(id));
    //    }
    //    return result;
   // }



   // public int bid(int artworkID, int Amount){
    //    Integer bidID = getNextID(bids);
    //    Artwork artwork = getArtwork(artworkID);
    //    try{
    //        Bid newBid = new Bid(Amount, currentUser.getID(), bidID, artwork.getArtworkBid();
    //        bids.put(bidID, newBid);
    //        artwork.bid(bidID,newBid);
    //        return 0;
    //    }catch (Exception e){
    //        return 1;
    //    }

   // }

   // public void toggleFavouriteUser(int userID){
     //   currentUser.toggleFavouriteUser(userID);
    //}

    //public void newArtwork( ArrayList<Object> info){
    //    Integer artworkID = getNextID(artworks);
    //    int type = (int)info.get(0);
     //   String artworkTitle = (String) info.get(1);
     //   String artworkDescription = (String) info.get(2);
     //   String artworkCreator = (String) info.get(3);
     //   int artworkYearCreated = (int)info.get(4);
    //    double reservePrice = (double)info.get(5);
    ////    int numOfBids = (int)info.get(6);
     //   String userNameSeller = (String) info.get(7);
    //    int width = (int)info.get(8);
    //    int height = (int)info.get(9);

        //Bid currentBid = new Bid(reervePrice);

    //    if(type == 0){
     //       Painting painting = new Painting(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated,
    //                            reservePrice, numOfBids, userNameSeller, width, height);
     //       artworks.put(artworkID, painting);
     //   }else{
     //       int depth = (int)info.get(10);
     //       String material = (String) info.get(11);
//
    //        Sculpture sculpture = new Sculpture(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated, reservePrice,
     //                             numOfBids,String userNameSeller, width, height, depth, material);
//
    //        artworks.put(artworkID, sculpture);
    //    }
   // }

   // public void createUser( ArrayList<Object> info){
   //     Integer userID = getNextID(users);
   //     String userName = (String) info.get(1);
   //     String firstName = (String) info.get(1);
   //     String lastName = (String) info.get(1);
   //     int phoneNumber = (int) info.get(1);
   //     String address = (String) info.get(1);
   //     String postCode = (String) info.get(1);
    //    String profilePicture = "Generic path ?";

        //id in constructor where ?
    //    UserProfile newUser = new UserProfile (String userName, String firstName, String lastName, phoneNumber, address, postCode, profilePicture);
   //     users.put(userID, newUser);
   // }



/* not implementing sincelastlogin
    public ArrayList<Artwork> newArtworks(){

    }

    public ArrayList<Artwork> newBids(){

    }
*/



   // public void logout(){
    //    FileWriter.writeToFile(USER_PATH,ARTWORK_PATH,BID_PATH,users,artworks,bids);
   // }


   // public void setCurrentUser(String username){
   //     for (Map.Entry current : users.entrySet())
    //    {
    //        if(current.getValue().getUsername().equalsIgnoreCase(username)){
     //           currentUser = (UserProfile) current.getValue();
     //       }
     //   }
    //}



   // private void readUsers(){
    //    users = FileReader.readFile(USER_PATH,"userList");
    //}

 //   private void readBids(){
  //      bids = FileReader.readFile(BID_PATH, "bidList");
  //  }


   // private void updateArtworks(){
   //     artworks = FileWriter.writeFile(ARTWORK_PATH,"userList");
   // }

  //  private void updateUsers(){
    //    users = FileWriter.writeFile(USER_PATH,"userList");
    //}

   // private void updateBids(){
    //    bids = FileWriter.writeFile(BID_PATH, "bidList");
   // }

    //private Integer getNextID(HashMap hash){
    //    Integer id = 0;
     //   Integer currentID = 0;
    //    for (Map.Entry current : hash.entrySet())
    //    {
    //        currentID = (Integer)current.getKey();
    //        if((currentID>id){
    //            id = currentID;
    //        }
   //     }
   //     id++;
  //      return id;
   // }

    public UserProfiles getUser(Integer id){
        return users.get(id);
    }

    public Artwork getArtwork(Integer id){
        return artworks.get(id);
    }

   // public Bid getBids(Integer id){
     //   return bids.get(id);
    //}
    public ArrayList<UserProfiles> getFaveUsers (){
        ArrayList<UserProfiles> currentUserFaveProfiles = new ArrayList<>();
        for (int faveUsersIterator : currentUser.faveUsers){
            for (Map.Entry<Integer, UserProfiles> hashMapIterator : users.entrySet()){
                if ( faveUsersIterator == hashMapIterator.getKey()){
                    currentUserFaveProfiles.add(hashMapIterator.getValue());
                }
            }
        }
        return currentUserFaveProfiles;
    }

}
