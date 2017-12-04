//God bless for CS115.

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {
	private static ArrayList<UserProfiles> userList = new ArrayList<>();
	private static ArrayList<Artwork> artworkList = new ArrayList<>();
	//private static ArrayList<FaveUsers> faveUsersList = new ArrayList<>();
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
		//for(Artwork a : artworkList) {
		//	System.out.println(a.toString());
		//}
		//for(UserProfile p : userList){
    	//	System.out.println(p.toString());
		//}
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

    	Date logInTime = new Date(in.nextLong());
		String path = in.next();




		userList.add(new UserProfiles(userName, firstName, lastName, phoneNumber,userAddress, postCode ,path, Integer.valueOf(id)));
		in.close();
    	return userList;
    }
    private static ArrayList loadPaintingsToSystem(Scanner in){
    	in.useDelimiter(",");
    	String artworkTitle = in.next();
    	String description = in.next();
    	String pathToArtPic = in.next();
    	String creatorName = in.next();
    	int yearCreated = in.nextInt();
    	double reservePrice = in.nextDouble();
    	int numOfBids = in.nextInt();
    	String userNameSeller = in.next();
    	Date auctionPlaced = new Date(in.nextLong());
		int dWidth = in.nextInt();
		int dHeight = in.nextInt();
		int id = in.nextInt();
		artworkList.add(new Painting(artworkTitle, description, creatorName, yearCreated, reservePrice, numOfBids, userNameSeller,dWidth, dHeight,id));
		in.close();
		//to do additional photos.
    	return artworkList;
    }
    private static ArrayList loadSculpturesToSystem(Scanner in) {
		in.useDelimiter(",");
		String artworkTitle = in.next();
		String description = in.next();
		String pathToArtPic = in.next();
		String creatorName = in.next();
		int yearCreated = in.nextInt();
		double reservePrice = in.nextDouble();
		int numOfBids = in.nextInt();
		String userNameSeller = in.next();
		Date auctionPlaced = new Date(in.nextLong());
    	int dWidth = in.nextInt();
		int dHeight = in.nextInt();
		int id = in.nextInt();
		int dDepth = in.nextInt();
		String material = in.next();

		artworkList.add(new Sculpture(artworkTitle, description, creatorName, yearCreated, reservePrice, numOfBids, userNameSeller, dWidth, dHeight,id, dDepth, material));
		in.close();
		return artworkList;
    }

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
