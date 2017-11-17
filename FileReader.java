//God bless for CS115.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	private static ArrayList<UserProfile> userList = new ArrayList<>();
	private static ArrayList<Painting> paintingsList = new ArrayList<>();
	private static ArrayList<Sculpture> sculpturesList = new ArrayList<>();
	private static ArrayList<FaveUsers> faveUsersList = new ArrayList<>();


     	private static ArrayList readDataFile(Scanner in, String type){
    	ArrayList file = null;
    	if (type.toLowerCase().equals("userlist")){
    		while (in.hasNext()){
    			String curLine = in.nextLine();
    			Scanner line = new Scanner(curLine);
    			file = loadUserToSystem(line);
    			line.close();
    		}

    	}else if (type.equals("paintingslist")){
    		while (in.hasNext()){
    			String curLine = in.nextLine();
    			Scanner line = new Scanner(curLine);
    			file = loadPaintingsToSystem(line);
    			line.close();
    		}

    	}
    	else if (type.toLowerCase().equals("sculptureslist")){
			while (in.hasNext()){
				String curLine = in.nextLine();
				Scanner line = new Scanner(curLine);
				file = loadSculpturesToSystem(line);
				line.close();
			}
		}
		else if (type.toLowerCase().equals("favelist")) {
			while (in.hasNext()) {
				String curLine = in.nextLine();
				Scanner line = new Scanner(curLine);
				file = loadFaveListToSystem(line);

				line.close();
			}
			//for (FaveUsers f : faveUsersList){
			//	System.out.println(f.toString());
			//}
		}
    	return file;
		}

    private static ArrayList loadUserToSystem(Scanner in){
        in.useDelimiter(",");
        String userName = in.next();
    	String firstName = in.next();
    	String lastName = in.next();
    	ArrayList<String> userAddress = new ArrayList<>();
    	for (int i = 0; i < 4; i++){
    		userAddress.add(in.next());
    	}
        //System.out.println(in.hasNextLong());
    	Date logInTime = new Date(in.nextLong());
		String path = in.next();
		userList.add(new UserProfile(userName, firstName, lastName, userAddress, logInTime ,path));
    	return userList;
    }
    private static ArrayList loadPaintingsToSystem(Scanner in){
    	in.useDelimiter(",");
    	String artworkTitle = in.next();
    	String description = in.next();
    	String pathToArtPic = in.next();
    	String creatorName = in.next();
    	System.out.println(creatorName);
    	int yearCreated = in.nextInt();
    	double reservePrice = in.nextDouble();
    	int numOfBids = in.nextInt();
    	String userNameSeller = in.next();
    	Date auctionPlaced = new Date(in.nextLong());
		int dWidth = in.nextInt();
		int dHeight = in.nextInt();
		paintingsList.add(new Painting(artworkTitle, description, creatorName, yearCreated, reservePrice, numOfBids, userNameSeller,dWidth, dHeight));
		System.out.println(paintingsList.toString());
		//to do additional photos.
    	return paintingsList;
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
		int dDepth = in.nextInt();
		String material = in.next();
		sculpturesList.add(new Sculpture(artworkTitle, description, creatorName, yearCreated, reservePrice, numOfBids, userNameSeller, dWidth, dHeight, dDepth, material));
		return sculpturesList;
    }
    private static ArrayList loadFaveListToSystem(Scanner in){
		ArrayList<String> favList = new ArrayList<>();
		in.useDelimiter(",");
		System.out.println("loading users");
		favList.clear();
		while (in.hasNext()){
			favList.add(in.next());
		}
		FaveUsers u = new FaveUsers(favList);
		System.out.println(u.toString());
		faveUsersList.add(u);
		for (FaveUsers f : faveUsersList) {
			System.out.println(f.toString());
		}
		return faveUsersList;

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
    public static ArrayList<UserProfile> getProfiles(){
    	return userList;
	}

	//For test don't use in final version.
	public static void setProfile(UserProfile p){
        userList.add(p);
    }
}
