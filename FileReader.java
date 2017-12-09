
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class that reads the data for the system at start up
 *
 * @author Nick
 * @version 1
 */
public class FileReader {
    private static ArrayList<UserProfiles> userList = new ArrayList<>();
    private static ArrayList<Artwork> artworkList = new ArrayList<>();
    private static final int ADDRESS_SIZE = 3;

    /**
     * Method that determines which file is currently being read and splits the file into lines to be read
     *
     * @param in   The scanner on the file being read
     * @param type The type of objects that are being read currently from the scanner
     * @return Arraylist of the appropriate objects
     */
    private static ArrayList readDataFile(Scanner in, String type) {
        ArrayList file = null;
        if (type.toLowerCase().equals("userlist")) {
            while (in.hasNext()) {
                String curLine = in.nextLine();

                Scanner line = new Scanner(curLine);
                file = loadUserToSystem(line);
                line.close();
            }
        } else if (type.toLowerCase().equals("artworklist")) {
            while (in.hasNext()) {
                String curLine = in.nextLine();

                Scanner line = new Scanner(curLine);
                line.useDelimiter(",");
                String artType = line.next();
                if (artType.equals("p")) {
                    file = loadPaintingsToSystem(line);
                } else if (artType.equals("s")) {
                    file = loadSculpturesToSystem(line);
                }
                line.close();
            }


        }
        return file;
    }

    /**
     * Read a user on this line in the scanner and create a user object
     *
     * @param in Scanner of this current line in the file
     * @return User Object added to the arrayList
     */
    private static ArrayList loadUserToSystem(Scanner in) {

        in.useDelimiter(",");
        int id = in.nextInt();
        String userName = in.next();
        String firstName = in.next();
        String lastName = in.next();
        String phoneNumber = in.next();
        ArrayList<String> userAddress = new ArrayList<>();
        for (int i = 0; i < ADDRESS_SIZE; i++) {
            userAddress.add(in.next());
        }
        String postCode = in.next();
        String date = (in.next());
        String path = in.next();
        ArrayList<Integer> faveUsers = new ArrayList<>();
        while (in.hasNextInt()) {
            int i = in.nextInt();
            faveUsers.add(i);
        }
        userList.add(new UserProfiles(userName, firstName, lastName, phoneNumber, userAddress, postCode, path,
                Integer.valueOf(id), date, faveUsers));
        return userList;
    }

    /**
     * Read a new painting on the line in the file
     *
     * @param in Scanner of the line in the file
     * @return A arraylist with the current painting added to the file
     */
    private static ArrayList loadPaintingsToSystem(Scanner in) {
        in.useDelimiter(",");
        ArrayList<String> artworkPaths = new ArrayList<>();
        String artworkTitle = in.next();
        String description = in.next();

        String creatorName = in.next();
        int yearCreated = in.nextInt();
        double reservePrice = in.nextDouble();
        int numOfBids = in.nextInt();
        int userNameSeller = in.nextInt();
        Date auctionPlaced = new Date(in.nextLong());
        int dWidth = in.nextInt();
        int dHeight = in.nextInt();
        int id = in.nextInt();
        while (in.hasNext()) {
            artworkPaths.add(in.next());
        }
        artworkList.add(new Painting(artworkTitle, description, creatorName, yearCreated,
                reservePrice, numOfBids, userNameSeller, dWidth, dHeight, id, artworkPaths));
        in.close();

        return artworkList;
    }

    /**
     * Read a sculpture from the current scanner on the file
     *
     * @param in Scanner of the line in the file
     * @return Arraylist with the new sculpture ammended to it
     */
    private static ArrayList loadSculpturesToSystem(Scanner in) {
        in.useDelimiter(",");
        ArrayList<String> artworkPaths = new ArrayList<>();
        String artworkTitle = in.next();
        String description = in.next();

        String creatorName = in.next();
        int yearCreated = in.nextInt();
        double reservePrice = in.nextDouble();
        int numOfBids = in.nextInt();
        int userNameSeller = in.nextInt();
        Date auctionPlaced = new Date(in.nextLong());
        int dWidth = in.nextInt();
        int dHeight = in.nextInt();
        int id = in.nextInt();

        int dDepth = in.nextInt();
        String material = in.next();
        while (in.hasNext()) {
            artworkPaths.add(in.next());
        }

        artworkList.add(new Sculpture(artworkTitle, description, creatorName, yearCreated,
                reservePrice, numOfBids, userNameSeller, dWidth, dHeight, id, dDepth, material, artworkPaths));
        in.close();
        return artworkList;
    }

    /**
     * Method that starts reading the file
     *
     * @param fileName Path to the file being read
     * @param type     The type of objects to be read from the file
     * @return Arraylist of those objects from the file
     */
    public static ArrayList readFile(String fileName, String type) {
        Scanner in = null;
        File inputFile = new File(fileName);
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + fileName);
            System.exit(0);
        }


        return readDataFile(in, type);
    }

}
