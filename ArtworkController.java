

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Set;


/**
 * This class controls the add artwork gui page
 * @author Tim Watson 880158
 * @version 1
 */
public class ArtworkController {
    private Painting painting;
    private Sculpture sculpture;
    private int nextId, userId;
    private ArrayList<String> picturePath = new ArrayList<>();

    @FXML
    private TextField nameBox, yearBox, reserveBox, bidsBox, userNameBox, widthBox, heightBox, depthBox, materialBox
            ,nameOfArtwork, descriptionBox ;
    @FXML
    RadioButton sculptureRadio, paintingRadio;
    @FXML
    Button buttonId, cancelButton, btnViewImage;
    @FXML
    Pane rootPane;
    @FXML
    Label depthText, materialText, depthUnits;
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
        bannerImg.setImage(Utilities.getImage(Run.database.BANNER_PATH));
        this.userId = Run.database.getCurrentUser().getId();
    }
    private void showPictures() {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/artworkPictureOpen.fxml"));

            BorderPane root = (BorderPane) fxmlLoader.load();

            ArtworkPictureController controller = fxmlLoader.<ArtworkPictureController>getController();

            if (paintingRadio.isSelected()){
                controller.showPictures(picturePath, "painting");
            }else{
                controller.showPictures(picturePath, "sculpture");
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


    public void getArtwork(Object artwork){
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



        this.painting = painting;
        this.sculpture = sculpture;
        this.nextId = id;
        this.userNameBox.setText(Run.database.getCurrentUser().getUserName());
        this.bidsBox.setEditable(true);


    }


}
