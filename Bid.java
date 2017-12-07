import java.util.Date;

/**
 * Created by LT on 07/12/2017.
 */
public class Bid {
    private int userID;
    private int ubidID;
    private int uartworkID;
    private double uamount;
    private Date udatePlaced;

    public Bid(double amount){
        uamount = amount;
    }
    public Bid(int bidID, double amount, int userID, int artworkID, Bid currBid) throws Exception{
        setAmount(amount);
        setArtworkID(artworkID);
        setBidID(bidID);
        setUserID(userID);

        bidAccepted(currBid);

        //udatePlaced = new Date();
        //udatePlaced.setTime();
    }
    private void bidAccepted(Bid currBid) throws Exception{
        if(uamount < currBid.getAmount()) {
            throw new Exception("Bid too low");
        }
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public void setBidID(int ubidID){
        this.ubidID = ubidID;
    }

    public void setArtworkID(int uartworkID){
        this.uartworkID = uartworkID;
    }

    public void setAmount(double amount){
        uamount = amount;
    }

    public void datePlaced(Date udatePlaced){
        this.udatePlaced = udatePlaced;
    }

    public int getUserID(){
        return userID;
    }
    public int getBidID(){
        return ubidID;
    }

    public int getArtworkID(){
        return uartworkID;
    }

    public double getAmount(){
        return uamount;
    }

    public Date getDatePlaced(){
        return udatePlaced;
    }

}
