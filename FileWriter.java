import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriter {
    private static ArrayList<UserProfile> userList = ArtataweSystem.getProfilesToWrite();
    private static ArrayList<Painting> paintingsList  = ArtataweSystem.getPaintingsToWrite();
    private static ArrayList<Sculpture> sculpturesList = ArtataweSystem.getSculpturesToWrite();
    private static String delim = ",";

    private static void writeDataToFile(PrintWriter out, String type){
         if (type.toLowerCase().equals("userlist")){
             System.out.println("Writing users to file.");
             for (UserProfile e : userList){
                 out.print(e.getUserName() + delim);
                 out.print(e.getFirstName() + delim);
                 out.print(e.getLastName() + delim);
                 for (String s : e.getUserAddress()){
                     out.print(s + delim);
                 }
                 out.print(e.getLogInDate().getTime() + delim);
                 out.print(e.getPathToPicture());
                 out.println();
             }

             out.close();
         }
         else if (type.toLowerCase().equals("paintingslist")){
            System.out.println("Writing paintings to file.");
            System.out.println(paintingsList.size());
            for (Painting p : paintingsList){
                out.print(p.getArtworkTitle() + delim);
                out.print(p.getArtworkDescription() + delim);
                out.print(p.getArtworkYearCreated() + delim);
                out.print(p.getReservePrice() + delim);
                out.print(p.getNumOfBids() + delim);
                out.print(p.getArtworkSeller() + delim);
                out.print(p.getdWidth() + delim);
                out.print(p.getdHeight());
                out.println();

            }
            out.close();
         }
         else if (type.toLowerCase().equals("sculptureslist")){
             System.out.println(sculpturesList.size());

             System.out.println("Writing sculptures to file.");
             for (Sculpture s : sculpturesList) {
                 out.print(s.getArtworkTitle() + delim);
                 out.print(s.getArtworkDescription() + delim);
                 out.print(s.getArtworkYearCreated() + delim);
                 out.print(s.getReservePrice() + delim);
                 out.print(s.getNumOfBids() + delim);
                 out.print(s.getArtworkSeller() + delim);
                 out.print(s.getsWidth() + delim);
                 out.print(s.getsHeight() + delim);
                 out.print(s.getsMaterial());
                 out.println();
             }
             out.close();
         }
    }
    //I dunno what's this, don't use it lol...
    private static void userProfileWrite(PrintWriter out){
        out.println("Hello");
        out.close();

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

    //For testing.
    public static void showProfiles(){
        for (UserProfile e : userList){
            System.out.println(e.getUserName());
        }
    }
}
