

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;



/**
 * This class controls the add artwork gui page
 * @author Tim Watson 880158
 * @version 2
 */
public class ArtworkController {
    private Artwork artwork;
    private Painting painting;
    private Sculpture sculpture;
    private int nextId, userId;
    private ArrayList<String> picturePath = new ArrayList<>();
    private boolean bid;

    @FXML
    private TextField nameBox, yearBox, reserveBox, bidsBox, userNameBox, widthBox, heightBox, depthBox, materialBox
            ,nameOfArtwork, descriptionBox ;
    @FXML
    private RadioButton sculptureRadio, paintingRadio;
    @FXML
    private Button buttonId, cancelButton, btnViewImage, btnBid, btnFave;
    @FXML
    private Pane rootPane;
    @FXML
    private Label depthText, materialText, depthUnits;
    @FXML
    private ImageView bannerImg;


    /**
     *  When the artwork add page is opened this method links buttons to methods and radio buttons
     */
    public void initialize() {
        buttonId.setOnAction(e ->{
            saveChanges();

        });

        cancelButton.setOnAction(e -> {
            Utilities.cancelled();
            Utilities.closeWindow(rootPane);
        });
        sculptureRadio.setOnAction(e -> {
            toggleBoxes(true);
        });

        paintingRadio.setOnAction(e -> {
            toggleBoxes(false);
        });
        btnViewImage.setOnAction((e ->{
            showPictures();

        }));

        btnBid.setOnAction(e ->{
            goToPlaceBid();

        });

        btnFave.setOnAction(e -> {
            toggleUserToFaves();
        });
        bannerImg.setImage(Utilities.getImage(Run.database.BANNER_PATH));
        this.userId = Run.database.getCurrentUser().getId();
    }

    /**
     * Method that brings up a new page with all pictures currently stored for this artwork
     */
    private void showPictures() {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkPictureOpen.fxml"));

            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkPictureController controller = fxmlLoader.<ArtworkPictureController>getController();

            if (paintingRadio.isSelected()){
                controller.showPictures(picturePath, "painting",bid);
            }else{
                controller.showPictures(picturePath, "sculpture",bid);
            }


            Scene editScene = new Scene(root, Run.EDIT_WINDOW_WIDTH, Run.EDIT_WINDOW_HEIGHT);
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Pictures");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();


        } catch (Exception e) {
            Utilities.noImageFound();

        }
    }


    /**
     *  Method that takes either a reference to a painting or sculpture object to be edited and saved
     * @param artwork Artwork object to be edited
     */
    public void getArtwork(Object artwork){
        bid = false;
        if (artwork instanceof Painting){
            this.painting = (Painting) artwork;
            this.picturePath = this.painting.getPictures();

            nameBox.setText(painting.getArtworkCreator());
            yearBox.setText(String.valueOf(painting.getArtworkYearCreated()));
            reserveBox.setText(String.valueOf(painting.getReservePrice()));
            bidsBox.setText(String.valueOf(painting.getNumOfBids()));
            nextId = painting.getId();
            userNameBox.setText(Run.database.getUser(userId).getUserName());
            widthBox.setText(String.valueOf(painting.getWidth()));
            heightBox.setText(String.valueOf(painting.getHeight()));
            nameOfArtwork.setText(painting.getArtworkTitle());
            descriptionBox.setText(painting.getArtworkDescription());
            paintingRadio.setSelected(true);
            paintingRadio.setVisible(false);
            sculptureRadio.setVisible(false);
            toggleBoxes(false);


        }else{
            this.sculpture = (Sculpture) artwork;

            this.picturePath = this.sculpture.getPictures();
            nameBox.setText(sculpture.getArtworkCreator());
            yearBox.setText(String.valueOf(sculpture.getArtworkYearCreated()));
            reserveBox.setText(String.valueOf(sculpture.getReservePrice()));
            bidsBox.setText(String.valueOf(sculpture.getNumOfBids()));
            nextId = sculpture.getId();
            userNameBox.setText(Run.database.getUser(userId).getUserName());
            widthBox.setText(String.valueOf(sculpture.getWidth()));
            heightBox.setText(String.valueOf(sculpture.getHeight()));
            descriptionBox.setText(sculpture.getArtworkDescription());
            nameOfArtwork.setText(sculpture.getArtworkTitle());
            depthBox.setText(String.valueOf(sculpture.getDepth()));
            materialBox.setText(sculpture.getMaterial());
            sculptureRadio.setSelected(true);
            paintingRadio.setVisible(false);
            sculptureRadio.setVisible(false);

        }
        btnBid.setVisible(false);
        btnFave.setVisible(false);
    }

    /**
     *  This method un/hides the boxes for depth and material if the object the user wants to add is either a
     *  sculpture or not
     * @param objectView Value that determines if this add object is a sculpture or not.
     */
    private void toggleBoxes(boolean objectView){
        depthBox.setVisible(objectView);
        materialBox.setVisible(objectView);
        depthText.setVisible(objectView);
        materialText.setVisible(objectView);
        depthUnits.setVisible(objectView);

    }

    /**
     * When the save button is pressed this method verifies the variable types and then adds this new object to the
     * artwork list.
     */
    
    private void saveChanges() {
        try{
           int year = Integer.valueOf(yearBox.getText());
           double reserve = Double.valueOf(reserveBox.getText());
           int bids =Integer.valueOf(bidsBox.getText());
           int width =Integer.valueOf(widthBox.getText());
           int height = Integer.valueOf(heightBox.getText());


            ArrayList checkLetters = new ArrayList<String>();


            if(!Utilities.checkImagesAdded(this.picturePath)){
                throw new Exception("No image specified please add one");

            }

            checkLetters.add(nameOfArtwork.getText());
            checkLetters.add(nameBox.getText());
            //checkLetters.add(descriptionBox.getText());

            if (!Utilities.checkInputAreAlpha(checkLetters)){
                throw new Exception("Input type is incorrect");
            }
            String creatorName = nameBox.getText();

            String title = nameOfArtwork.getText();
            String desc = descriptionBox.getText();



            if (sculptureRadio.isSelected()){
               int depth = Integer.valueOf(depthBox.getText());

                checkLetters.add(materialBox.getText());

                if (!Utilities.checkInputAreAlpha(checkLetters)){
                    throw new Exception("Error ");
                }

                String material = materialBox.getText();
               Utilities.saveSculpture(sculpture,year, reserve, bids, width,height,depth,
                        creatorName,userId,material,title,desc,nextId, this.picturePath );
               Run.database.addArtwork(sculpture);
               Run.database.saveArtwork();

            }else if (paintingRadio.isSelected()){
               Utilities.savePainting(painting, year, reserve, bids, width,height,
                        creatorName,userId,title,desc, nextId, this.picturePath);

                Run.database.addArtwork(painting);
                Run.database.saveArtwork();
            }else{
                throw new Exception("Error: Radio button not selected");
            }

            for(Artwork a: Run.database.getAllArtworks()){
                System.out.println(a.toString());
            }

            Utilities.savedInput();

            Utilities.closeWindow(rootPane);

        }catch(Exception e){

            Utilities.wrongInputFound();
        }


    }


    /**
     * When the artwork add controller is created this page is called to reference the adding objects.
     *
     * @param painting  the painting object that may be created by the user
     * @param sculpture the sculpture object that may be created by the user
     * @param id        the id of this new object
     */
    public void artworkToAdd(Painting painting, Sculpture sculpture, int id) {


        bid = false;
        this.painting = painting;
        this.sculpture = sculpture;
        this.nextId = id;
        this.userNameBox.setText(Run.database.getCurrentUser().getUserName());
        this.bidsBox.setEditable(true);
        btnBid.setVisible(false);
        btnFave.setVisible(false);


    }

    /**
     *  Method that takes an artwork that can be bid on and shows its details aswell as displays bidding button
     * @param artwork Artwork that can be bid on
     */
    public void artworkToBid(Artwork artwork){
        this.artwork = artwork;

        bid = true;
        btnBid.setVisible(true);
        btnFave.setVisible(true);
        buttonId.setVisible(false);
        paintingRadio.setVisible(false);
        sculptureRadio.setVisible(false);


        nameBox.setText(artwork.getArtworkCreator());
        yearBox.setText(String.valueOf(artwork.getArtworkYearCreated()));
        reserveBox.setText(String.valueOf(artwork.getReservePrice()));
        bidsBox.setText(String.valueOf(artwork.getNumOfBids()));


        userId = artwork.getArtworkSeller();
        if(Run.database.getCurrentUser().getFaveUsers().contains(userId)){
            btnFave.setVisible(false);
        }

        userNameBox.setText(String.valueOf(Run.database.getUser(artwork.getArtworkSeller()).getUserName()));
        nameOfArtwork.setText(artwork.getArtworkTitle());
        descriptionBox.setText(artwork.getArtworkDescription());


        if(artwork instanceof Sculpture){
            this.sculpture = (Sculpture) artwork;
            widthBox.setText(String.valueOf(this.sculpture.getWidth()));
            heightBox.setText(String.valueOf(this.sculpture.getHeight()));
            depthBox.setText(String.valueOf(this.sculpture.getDepth()));
            materialBox.setText(this.sculpture.getMaterial());
            this.picturePath = this.sculpture.getPictures();

        }else{
            depthBox.setVisible(false);
            materialBox.setVisible(false);
            materialText.setVisible(false);
            depthUnits.setVisible(false);
            depthText.setVisible(false);
            this.painting = (Painting) artwork;
            widthBox.setText(String.valueOf(this.painting.getWidth()));
            heightBox.setText(String.valueOf(this.painting.getHeight()));
            this.picturePath = this.painting.getPictures();
        }
        

    }

    /**
     * Adds the seller of this artwork to the current users favourite user list
     */
    private void toggleUserToFaves(){
        Run.database.getCurrentUser().toggleFaveUser(userId);
        Run.database.saveUsers();
        
        Utilities.savedInput();
        btnFave.setVisible(false);
    }


    /**
     *  Open the bid window, to place a bid
     */
    private void goToPlaceBid(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/placeBid.fxml"));
            Pane root = fxmlLoader.load();
            placeBidController controller = fxmlLoader.getController();
            controller.setArtwork(artwork);

            Scene editScene = new Scene(root, (Run.EDIT_WINDOW_WIDTH/2), (Run.EDIT_WINDOW_HEIGHT/2));
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle("Place Bid");
            editStage.initModality(Modality.APPLICATION_MODAL);

            editStage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
