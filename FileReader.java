//God bless for CS115.

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileReader {
	private static ArrayList<UserProfiles> userList = new ArrayList<>();
	private static ArrayList<Artwork> artworkList = new ArrayList<>();
	//private static ArrayList<FaveUsers> faveUsersList = new ArrayList<>();
	//private static ArrayList<BidHistory> bidHistoryList = new ArrayList<>();
	private static final int ADDRESS_SIZE = 3;


     	private static ArrayList readDataFile(Scanner in, String type){
    	ArrayList file = null;
    	if (type.toLowerCase().equals("userlist")){
    		while (in.hasNext()){
    			String curLine = in.nextLine();

    			Scanner line = new Scanner(curLine);
    			file = loadUserToSystem(line);
    			line.close();
    		}

    	}else if (type.toLowerCase().equals("artworklist")) {
			while (in.hasNext()) {
				String curLine = in.nextLine();

				Scanner line = new Scanner(curLine);
				line.useDelimiter(",");
				String artType = line.next();
				if(artType.equals("p")) {
					file = loadPaintingsToSystem(line);
				}else if (artType.equals("s")){
					file = loadSculpturesToSystem(line);
				}
				line.close();
			}


		}
		/*else if (type.toLowerCase().equals("bidhistorylist")){
			while (in.hasNext()){
				String curLine = in.nextLine();

				Scanner line = new Scanner(curLine);
				file = loadArtworkBidHistory(line);
				line.close();
			}
		}*/

    	return file;
		}

    private static ArrayList loadUserToSystem(Scanner in){

        in.useDelimiter(",");
		int id = in.nextInt();
        String userName = in.next();
    	String firstName = in.next();
    	String lastName = in.next();
    	String phoneNumber = in.next();
    	ArrayList<String> userAddress = new ArrayList<>();
    	for (int i = 0; i < ADDRESS_SIZE; i++){
    		userAddress.add(in.next());
    	}
    	String postCode = in.next();
		String date = (in.next());


		String path = in.next();
		ArrayList<Integer> faveUsers = new ArrayList<>();
		while (in.hasNextInt()){
			int i = in.nextInt();
			faveUsers.add(i);
		}

		userList.add(new UserProfiles(userName, firstName, lastName, phoneNumber,userAddress, postCode ,path,
				Integer.valueOf(id), date, faveUsers));
    	return userList;
    }
    private static ArrayList loadPaintingsToSystem(Scanner in){
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
		while (in.hasNext()){
			artworkPaths.add( in.next());
		}
		artworkList.add(new Painting(artworkTitle, description, creatorName, yearCreated,
				reservePrice, numOfBids, userNameSeller,dWidth, dHeight,id,artworkPaths));
		in.close();

    	return artworkList;
    }
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
		while (in.hasNext()){
			artworkPaths.add( in.next());
		}

		artworkList.add(new Sculpture(artworkTitle, description, creatorName, yearCreated,
				reservePrice, numOfBids, userNameSeller, dWidth, dHeight,id, dDepth, material,artworkPaths));
		in.close();
		return artworkList;
    }
	/*public static ArrayList<BidHistory> loadArtworkBidHistory(Scanner in){
		in.useDelimiter(",");
		int artID = in.nextInt();
		String pBuyer;
		double amount;
		Date date;
		ArrayList<Double> amounts = new ArrayList<>();
		ArrayList<String> profiles = new ArrayList<>();
		ArrayList<Date> dates = new ArrayList<>();
		while (in.hasNext()){
			pBuyer = in.next();
			amount = in.nextDouble();
			date = new Date(in.nextLong());
			profiles.add(pBuyer);
			amounts.add(amount);
			dates.add(date);
		}
		bidHistoryList.add(new BidHistory(artID,amounts,profiles,dates));
		return bidHistoryList;
	}*/

	public static ArrayList readFile(String fileName, String type){
        Scanner in =  null;
        File inputFile = new File(fileName);
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e){
            System.out.println("Cannot open: " + fileName);
            System.exit(0);
        }



        return readDataFile(in, type);
    }
    public static ArrayList<UserProfiles> getProfiles(){
    	return userList;
	}

	//For test don't use in final version.
	public static void setProfile(UserProfiles p){
        userList.add(p);
    }
}
