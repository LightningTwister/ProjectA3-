import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriter {
    private static String delim = ",";

    private static void writeDataToFile(PrintWriter out, String type){
         if (type.toLowerCase().equals("userlist")){
             System.out.println("Writing users to file.");
             for (UserProfiles e : Run.database.getAllUsers()){
                 out.print(e.getId()+ delim);
                 out.print(e.getUserName() + delim);
                 out.print(e.getFirstName() + delim);
                 out.print(e.getLastName() + delim);
                 out.print(e.getPhoneNumber()+ delim);
                 for (String s : e.getAddress()){
                     out.print(s + delim);
                 }
                 out.print(e.getPostCode() + delim);
                 out.print( "3123213124"+ delim);
                 out.print(e.getProfilePicture());
                 out.println();
             }

             out.close();
         }else if (type.toLowerCase().equals("artworklist")) {
             for (Artwork a : Run.database.getAllArtworks()) {
                 if (a instanceof Painting) {

                     Painting p = (Painting)a;
                     //System.out.println("Writing painting to file.");
                         out.print("p" + delim);
                         out.print(p.getArtworkTitle() + delim);
                         out.print(p.getArtworkDescription() + delim);

                         out.print(p.getArtworkCreator() + delim);
                         out.print(p.getArtworkYearCreated() + delim);
                         out.print(p.getReservePrice() + delim);
                         out.print(p.getNumOfBids() + delim);
                         out.print(p.getArtworkSeller() + delim);
                         out.print(231240124+delim);
                         out.print(p.getWidth() + delim);
                         out.print(p.getHeight() + delim);
                         out.print(p.getId()+ delim);
                         for(String nextPicture: p.getPictures()){
                             out.print(nextPicture+delim);
                         }

                         out.println();

                 }

                     else if (a instanceof Sculpture) {

                        Sculpture s = (Sculpture)a;
                        //System.out.println("Writing sculpture to file.");
                        out.print("s" + delim);
                         out.print(s.getArtworkTitle() + delim);
                         out.print(s.getArtworkDescription() + delim);
                         out.print(s.getArtworkCreator() + delim);
                         out.print(s.getArtworkYearCreated() + delim);
                         out.print(s.getReservePrice() + delim);
                         out.print(s.getNumOfBids() + delim);
                         out.print(s.getArtworkSeller() + delim);
                         out.print(231240124+delim);
                         out.print(s.getWidth() + delim);
                         out.print(s.getHeight() + delim);
                         out.print(s.getId()+delim);
                         out.print(s.getDepth()+ delim);
                         out.print(s.getMaterial()+ delim);
                     for(String nextPicture: s.getPictures()){
                         out.print(nextPicture+delim);
                     }
                         out.println();
                 }


             }
             out.close();


         }else if (type.toLowerCase().equals("bidhistorylist")){
             System.out.println("Writing bidhistory to file.");
             for (Artwork a : Run.database.getAllArtworks()){
                 out.print(a.getId());
                 for(int i = 0; i < a.getArtworkBid().getBidHistory().getAmountBidArray().size(); i++){
                     out.print(delim + a.getArtworkBid().getBidHistory().getProfilesOfBidders().get(i));
                     out.print(delim + a.getArtworkBid().getBidHistory().getAmountBidArray().get(i));
                     out.print(delim + (a.getArtworkBid().getBidHistory().getDateArray().get(i)));
                     out.println();
                 }
             }
             out.close();
         }
    }

    public static void openFile(String fileName, String type){
        File outputFile = new File(fileName);
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
        } catch (FileNotFoundException e){
            System.out.println("Cannot open " + fileName);
            System.exit(0);
        }
        writeDataToFile(out, type);
    }

}

