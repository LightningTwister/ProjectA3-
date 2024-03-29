

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
    private static String delim = ",";
    private static String delim1 = ";";
    private static HashMap<Integer, UserProfiles> userList = new HashMap<>();
    private static HashMap<Integer, Artwork> artworkList = new HashMap<>();
    //private static ArrayList<FaveUsers> faveUsersList = new ArrayList<>();
    private static HashMap<Integer, Bid> bidList = new HashMap<>();
    private static final int ADDRESS_SIZE = 3;

    /**
     * Method that determines which file is currently being read and splits the file into lines to be read
     *
     * @param in   The scanner on the file being read
     * @param type The type of objects that are being read currently from the scanner
     * @return Arraylist of the appropriate objects
     */
    private static HashMap readDataFile(Scanner in, String type) {
        HashMap file = null;

        if (type.toLowerCase().equals(Main.database.USER_SPECIFIER)) {

            file = new HashMap<Integer, UserProfiles>();
            if (!in.hasNext()) {
                return userList;
            } else {
                while (in.hasNext()) {
                    String curLine = in.nextLine();

                    Scanner line = new Scanner(curLine);
                    UserProfiles p = loadUserToSystem(line);
                    file.put(p.getId(),p);
                    line.close();
                }
            }

            //Read in an artwork
        } else if (type.toLowerCase().equals(Main.database.ARTWORK_SPECIFIER)) {
            file = new HashMap<Integer, Artwork>();
            if (!in.hasNext()) {
                return artworkList;
            } else {
                while (in.hasNext()) {
                    String curLine = in.nextLine();

                    Scanner line = new Scanner(curLine);
                    line.useDelimiter(delim);
                    String artType = line.next();
                    if (artType.equals("p")) {
                        file = loadPaintingsToSystem(line);
                    } else if (artType.equals("s")) {
                        file = loadSculpturesToSystem(line);
                    }
                    line.close();
                }
            }


        }
        //Read in a bid
        else if (type.toLowerCase().equals("bidlist")) {
            file = new HashMap<Integer, Bid>();
            if (!in.hasNext()) {
                return new HashMap();
            } else {
                while (in.hasNext()) {
                    String curLine = in.nextLine();
                    Scanner line = new Scanner(curLine);
                    Bid bid = loadArtworkBids(line);
                    file.put(bid.getBidID(),bid);

                    line.close();
                }
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

    private static UserProfiles loadUserToSystem(Scanner in) {

        in.useDelimiter(delim);
        int id = in.nextInt();
        String userName = in.next();
        String firstName = in.next();
        String lastName = in.next();
        String phoneNumber = in.next();
        ArrayList<String> userAddress = new ArrayList<>();
        String testString = in.next();
        Scanner temp = new Scanner(testString);
        temp.useDelimiter(delim1);
        for(int i = 0; i < ADDRESS_SIZE; i++ )  {
            String temp1 = temp.next();
            if (temp1.length() > 0) {
                userAddress.add(temp1);
            }
        }
        String postCode = in.next();
        String date = in.next();


        String profilePicture = in.next();
        ArrayList<Integer> faveUsers = new ArrayList<>();
        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if (temp1.length() > 0) {
                faveUsers.add(Integer.parseInt(temp1));
            }
        }

        ArrayList<Integer> currentAuctions = new ArrayList<>();
        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if (temp1.length() > 0) {
                currentAuctions.add(Integer.parseInt(temp1));
            }
        }

        ArrayList<Integer> completedAuctions = new ArrayList<>();
        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if (temp1.length() > 0) {
                completedAuctions.add(Integer.parseInt(temp1));
            }
        }


        ArrayList<Integer> wonArtworks = new ArrayList<>();
        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if (temp1.length() > 0) {
                wonArtworks.add(Integer.parseInt(temp1));
            }
        }

        ArrayList<Integer> bidHistory = new ArrayList<>();
        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if (temp1.length() > 0) {
                bidHistory.add(Integer.parseInt(temp1));
            }
        }


        UserProfiles user = new UserProfiles( id,  userName,  firstName,  lastName,  phoneNumber,
                userAddress,  postCode,  profilePicture,  date,
                 faveUsers,  currentAuctions,  wonArtworks,
                 completedAuctions,  bidHistory);
        return user;
    }

    /**
     * Read a new painting on the line in the file
     *
     * @param in Scanner of the line in the file
     * @return A arraylist with the current painting added to the file
     */
    private static HashMap loadPaintingsToSystem(Scanner in) {
        in.useDelimiter(delim);
        ArrayList<String> artworkPaths = new ArrayList<>();
        String artworkTitle = in.next();
        String description = in.next();

        String artworkCreator = in.next();
        int artworkYearCreated = in.nextInt();
        double reservePrice = in.nextDouble();
        int numOfBids = in.nextInt();
        int userId = in.nextInt();
        String auctionPlaced = in.next();

        int dWidth = in.nextInt();
        int dHeight = in.nextInt();
        int id = in.nextInt();


        Scanner temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if(temp1.length()>0) {
                artworkPaths.add(temp1);
            }
        }

        ArrayList<Integer> bidHistory = new ArrayList<>();


        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if(temp1.length()>0) {
                bidHistory.add(Integer.parseInt(temp1));
            }
        }
        temp = new Scanner(in.nextLine());
        Bid highestBid = loadArtworkBids(temp);

        artworkList.put(id, new Painting( artworkTitle, description, artworkCreator,  artworkYearCreated,
                            reservePrice,  numOfBids,  userId, dWidth,  dHeight,  id,
                                    artworkPaths,  highestBid, bidHistory, auctionPlaced));
        in.close();

        return artworkList;
    }

    /**
     * Read a sculpture from the current scanner on the file
     *
     * @param in Scanner of the line in the file
     * @return Arraylist with the new sculpture ammended to it
     */
    private static HashMap loadSculpturesToSystem(Scanner in) {
        in.useDelimiter(delim);
        ArrayList<String> artworkPaths = new ArrayList<>();
        String artworkTitle = in.next();
        String description = in.next();

        String creatorName = in.next();
        int yearCreated = in.nextInt();
        double reservePrice = in.nextDouble();
        int numOfBids = in.nextInt();
        int userNameSeller = in.nextInt();

        String auctionPlaced = in.next();
        int dWidth = in.nextInt();
        int dHeight = in.nextInt();
        int id = in.nextInt();
        int dDepth = in.nextInt();
        String material = in.next();

        String tempString = in.next();
        Scanner temp = new Scanner(tempString);
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if(temp1.length()>0) {
                artworkPaths.add(temp1);
            }
        }

        ArrayList<Integer> bidHistory = new ArrayList<>();

        temp = new Scanner(in.next());
        temp.useDelimiter(delim1);
        while (temp.hasNext()) {
            String temp1 = temp.next();
            if(temp1.length()>0) {
                bidHistory.add(Integer.parseInt(temp1));
            }
        }
        temp = new Scanner(in.nextLine());
        Bid highestBid = loadArtworkBids(temp);

        artworkList.put(id, new Sculpture(artworkTitle, description, creatorName, yearCreated,
                reservePrice, numOfBids, userNameSeller, dWidth, dHeight, id, dDepth, material, artworkPaths, highestBid, bidHistory,auctionPlaced));
        in.close();
        return artworkList;
    }

    /**
     * Method that reads bids from a file
     *
     * @param in The scanner of each line for a bid
     * @return Arraylist of those objects from the file
     */
    public static Bid loadArtworkBids(Scanner in) {
        in.useDelimiter(delim);
        int bidID = in.nextInt();
        int userID = in.nextInt();
        int artworkID = in.nextInt();
        double amount = in.nextDouble();
        String datePlaced = in.next();
        Bid bid = new Bid(bidID, amount, userID, artworkID, datePlaced);
        return bid;
    }

    /**
     * Method that starts reading the file
     *
     * @param fileName Path to the file being read
     * @param type     The type of objects to be read from the file
     * @return Arraylist of those objects from the file
     */
    public static HashMap readFile(String fileName, String type) {
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
