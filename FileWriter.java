
import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class to save system data to file
 *
 * @author Nick
 * @version 1
 */
public class FileWriter {
    private static String delim = ",";
    private static String delim1 = ";";

    /**
     * Method that takes a writer and the type of file to be written
     *
     * @param out  Writer to the file that will be used
     * @param type type of objects to be writen to file
     */
    private static void writeDataToFile(PrintWriter out, String type) {

        // write out a user
        if (type.toLowerCase().equals("userlist")) {
            //System.out.println("Writing users to file.");
            for (UserProfiles e : Main.database.getAllUsers()) {
                out.print(e.getId() + delim);
                out.print(e.getUserName() + delim);
                out.print(e.getFirstName() + delim);
                out.print(e.getLastName() + delim);
                out.print(e.getPhoneNumber() + delim);
                for (String s : e.getAddress()) {
                    out.print(s + delim1);
                }
                out.print(delim);
                out.print(e.getPostCode() + delim);

                out.print(e.getLogInDate() + delim);
                out.print(e.getProfilePicture() + delim);
                for (Integer a : e.getFaveUsers()) {
                    if (a != null) {
                        out.print(a + delim1);
                    }
                }
                out.print(delim);

                for (Integer a : e.getCurrentAuctions()) {
                    if (a != null) {
                        out.print(a + delim1);
                    }
                }
                out.print(delim);

                for (Integer a : e.getCompletedAuctions()) {
                    if (a != null) {
                        out.print(a + delim1);
                    }
                }
                out.print(delim);

                for (Integer a : e.getWonArtworks()) {
                    if (a != null) {
                        out.print(a + delim1);
                    }
                }
                out.print(delim);

                for (Integer a : e.getBidHistory()) {
                    if (a != null) {
                        out.print(a + delim1);
                    }
                }
                out.print(delim);

                out.println();
            }



            out.close();
            //write out an artwork
        } else if (type.toLowerCase().equals("artworklist")) {
            ArrayList<Artwork> artworks =  Main.database.getAllArtworks();
            for (Artwork a : artworks) {
                if (a instanceof Painting) {

                    Painting p = (Painting) a;
                    //System.out.println("Writing painting to file.");
                    out.print("p" + delim);
                    out.print(p.getArtworkTitle() + delim);
                    out.print(p.getArtworkDescription() + delim);

                    out.print(p.getArtworkCreator() + delim);
                    out.print(p.getArtworkYearCreated() + delim);
                    out.print(p.getReservePrice() + delim);
                    out.print(p.getNumOfBids() + delim);
                    out.print(p.getArtworkSeller() + delim);
                    out.print(p.getDateTimeArtworkPlaced() + delim);
                    out.print(p.getWidth() + delim);
                    out.print(p.getHeight() + delim);
                    out.print(p.getId() + delim);
                    for (String nextPicture : p.getPictures()) {
                        out.print(nextPicture + delim1);
                    }
                    out.print(delim);
                    ArrayList<Integer> bids = p.getBidHistory();
                    for(int i : bids){
                        out.print(i + delim1);
                    }
                    out.print(delim);
                    out.print(p.getHighestBid().getBidID() + delim);
                    out.print(p.getHighestBid().getUserID() + delim);
                    out.print(p.getHighestBid().getArtworkID() + delim);
                    out.print(p.getHighestBid().getAmount() + delim);
                    out.print(p.getHighestBid().getDatePlaced() + delim);

                    out.println();

                }
                //write out a sculpture
                else if (a instanceof Sculpture) {

                    Sculpture s = (Sculpture) a;
                    //System.out.println("Writing sculpture to file.");
                    out.print("s" + delim);
                    out.print(s.getArtworkTitle() + delim);
                    out.print(s.getArtworkDescription() + delim);
                    out.print(s.getArtworkCreator() + delim);
                    out.print(s.getArtworkYearCreated() + delim);
                    out.print(s.getReservePrice() + delim);
                    out.print(s.getNumOfBids() + delim);
                    out.print(s.getArtworkSeller() + delim);
                    out.print(s.getDateTimeArtworkPlaced() + delim);
                    out.print(s.getWidth() + delim);
                    out.print(s.getHeight() + delim);
                    out.print(s.getId() + delim);
                    out.print(s.getDepth() + delim);
                    out.print(s.getMaterial() + delim);
                    for (String nextPicture : s.getPictures()) {
                        out.print(nextPicture + delim1);
                    }
                    out.print(delim);
                    ArrayList<Integer> bids = s.getBidHistory();
                    for(int i : bids){
                        out.print(i + delim1);
                    }

                    out.print(delim);
                    out.print(s.getHighestBid().getBidID() + delim);
                    out.print(s.getHighestBid().getUserID() + delim);
                    out.print(s.getHighestBid().getArtworkID() + delim);
                    out.print(s.getHighestBid().getAmount() + delim);
                    out.print(s.getHighestBid().getDatePlaced() + delim);
                    out.println();
                }


            }
            out.close();

            //write out a bid
        } else if (type.toLowerCase().equals("bidlist")) {
           // System.out.println("Writing bids to file.");
            ArrayList<Bid> bids = Main.database.getBidsIO();
            for (Bid b : bids) {
                out.print(b.getBidID() + delim);
                out.print(b.getUserID() + delim);
                out.print(b.getArtworkID() + delim);
                out.print(b.getAmount() + delim);
                out.print(b.getDatePlaced() + delim);
                out.println();
            }
            out.close();
        }

    }


    /**
     * Method to open the file to be written too
     *
     * @param fileName Path to the file to be written too
     * @param type     Type of objects that will be written to file
     */
    public static void openFile(String fileName, String type) {
        File outputFile = new File(fileName);
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + fileName);
            System.exit(0);
        }
        writeDataToFile(out, type);
    }

}

