import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LT on 21/11/2017.
 */
public class Browsing {
    public static ArrayList<Artwork> browseArtworks(ArrayList<Artwork> artworkList, ArrayList<Integer> idList){
        ArrayList<Artwork> result = new ArrayList<Artwork>();
        for(int id : idList){
            for(Artwork artwork : artworkList){
                if(artwork.getId() == id){
                    result.add(artwork);
                }
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

    public static HashMap<Integer, UserProfile> browseUsers(HashMap<Integer, UserProfile> userList, ArrayList<Integer> idList ){
        HashMap<Integer, UserProfile> result = new HashMap<Integer, UserProfile>();
        for(int id : idList){
            result.put(id, userList.get(id));
        }
        return result;
    }




    public static HashMap<Integer, Bid> browseBidHistory(ArrayList<Bid> bidList, ArrayList<Integer> idList ){
        HashMap<Integer, Bid> result = new ArrayList<Bid>();
        for(Bid bid : bidList){
            for(int id : idList){
                if(bid.getId() == id){
                    result.put(id, bid);
                }
            }
        }
        return result;
    }

    //public static ArrayList<Bid> browseBuyerBidHistory(in bidList:ArrayList<Bid>, in idList: ArrayList<Integer> ){}

}
