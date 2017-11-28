import sun.plugin.util.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LT on 10/11/2017.
 */
public class Database {

    private final String ARTWORK_PATH;
    private final String USER_PATH;
    private final String BID_PATH;
    private UserProfile currentUser;
    private HashMap<Integer, UserProfile> users;
    private HashMap<Integer, Artwork> artworks;
    private HashMap<Integer, Bid> bids;


    public Database(){
        login();
    }
    public ArrayList<Bid> getBidHistory(){
        return Browsing.browseBidHistory(bids, currentUser.getBids());
    }
    public ArrayList<Bid> getBidHistory(int id){
        return Browsing.browseBidHistory(bids, Browsing.browseArtworks(artworks, id).get(0).getBids());
    }

    public HashMap<Integer, Artwork> getArtworkList(String typeOfList){
        switch (typeOfList){
            case "Painting": return Browsing.browseArtworks(artworks,true); break;
            case "Sculpture": return Browsing.browseArtworks(artworks,false); break;
            case "All": return artworks; break;
            default: System.out.println("ERROR: invalid getArtwork query"); return new ArrayList<Artwork>();
        }
    }



    public ArrayList<UserProfile> getUserList(){
        return Browsing.browseUsers(currentUser.getFavouriteusers());
    }



    public int bid(int artworkID, int Amount){
        Integer bidID = getNextID(bids);
        Artwork artwork = getArtwork(artworkID);
        try{
            Bid newBid = new Bid(Amount, currentUser.getID(), bidID, artwork.getArtworkBid();
            bids.put(bidID, newBid);
            artwork.bid(bidID,newBid);
            return 0;
        }catch (Exception e){
            return 1;
        }

    }

    public void toggleFavouriteUser(int userID){
        currentUser.toggleFavouriteUser(userID);
    }

    public void newArtwork( ArrayList<Object> info){
        Integer bidID = getNextID(artworks);
        int type = (int)info.get(0);
        String artworkTitle = (String) info.get(1);
        String artworkDescription = (String) info.get(2);
        String artworkCreator = (String) info.get(3);
        int artworkYearCreated = (int)info.get(4);
        double reservePrice = (double)info.get(5);
        int numOfBids = (int)info.get(6);
        String userNameSeller = (String) info.get(7);
        int width = (int)info.get(8);
        int height = (int)info.get(9);

        //Bid currentBid = new Bid(reervePrice);

        if(type == 0){
            Painting painting = new Painting(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated,
                                reservePrice, numOfBids, userNameSeller, width, height);
            artworks.put(bidID, painting);
        }else{
            int depth = (int)info.get(10);
            String material = (String) info.get(11);

            Sculpture sculpture = new Sculpture(artworkTitle, artworkDescription, artworkCreator, artworkYearCreated, reservePrice,
                                  numOfBids,String userNameSeller, width, height, depth, material);

            artworks.put(bidID, sculpture);
        }
    }

    //public int createUser(( ArrayList<Object>){
    //waiting on userprofile
    //}
/* not implementing sincelastlogin
    public ArrayList<Artwork> newArtworks(){

    }

    public ArrayList<Artwork> newBids(){

    }
*/


    public void login(){
        readArtworks();
        readUsers();
        readBids();
    }
    public void logout(){
        FileWriter.writeToFile(USER_PATH,ARTWORK_PATH,BID_PATH,users,artworks,bids);
    }

    public void setCurrentUser(String username){
        for (Map.Entry current : users.entrySet())
        {
            if(current.getValue().getUsername().equalsIgnoreCase(username)){
                currentUser = (UserProfile) current.getValue();
            }
        }
    }

    private void readArtworks(){
        artworks = FileReader.readFile(ARTWORK_PATH,"artworkList");
    }

    private void readUsers(){
        users = FileReader.readFile(USER_PATH,"userList");
    }

    private void readBids(){
        bids = FileReader.readFile(BID_PATH, "bidList");
    }


    private void updateArtworks(){
        artworks = FileWriter.writeFile(ARTWORK_PATH,"userList");
    }

    private void updateUsers(){
        users = FileWriter.writeFile(USER_PATH,"userList");
    }

    private void updateBids(){
        bids = FileWriter.writeFile(BID_PATH, "bidList");
    }

    private void updateIDs(){

    }

    private Integer getNextID(HashMap hash){
        Integer id = 0;
        for (Map.Entry current : hash.entrySet())
        {
            if(current.getKey()>id){
                id = current.getKey();
            }
        }
        id++;
        return id;
    }

    public UserProfile getUser(Integer id){
        return users.get(id);
    }

    public Artwork getArtwork(Integer id){
        return artworks.get(id);
    }

    public Bid getBids(Integer id){
        return bids.get(id);
    }

}
