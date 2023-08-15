import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.control.Tooltip;
import javafx.geometry.*;
import javafx.collections.*;
import javafx.css.PseudoClass;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * Write a description of JavaFX class GUI here.
 *
 * @author Frantisek Hermanek K20077556, Mehrshad Abdollaei K20078610
 */
public class GUI extends Application
{
    private Stage stage;
    private BorderPane pane;
    private AnchorPane root;
    private Scene welcomeScene, mainScene;

    private Label fromLabel = new Label("From:");
    private Label toLabel = new Label("To:");
    private Label borough = new Label("Select a borough:");

    private ComboBox fromBox;
    private ComboBox toBox;
    private ComboBox boroughBox;

    private boolean canPressButton = false;

    private int fromIndex = -1;
    private int toIndex = -1;

    public ArrayList<AirbnbListing> filterProperty = new ArrayList<>();

    private AirbnbDataLoader loader = new AirbnbDataLoader();
    private ArrayList<AirbnbListing> properties = new ArrayList<>();

    private Label borughLabel = new Label("Borough:");
    private Label priceRangeLabel = new Label("Price range: ");
    private Label numberOfPropertiesLabel = new Label("Number of properties: ");

    private boolean isMinPriceSelected = false;
    private boolean isMaxPriceSelected = false;

    private PropertyView propertyView = new PropertyView();

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     * @author Frantisek Hermanek K20077556, Mehrshad Abdollaei K20078610
     */
    @Override
    public void start(Stage primaryStage)
    {        
        this.stage = stage;
        
        //Welcome Sene and instructions for the App
        root = new AnchorPane();
        
        // Image of the logo
        Label logoImage = new Label ("", new ImageView(ImageFileManager.loadImage("logo.png")));
        
        // welcome header
        Label labelW= new Label("Welcome to LDN Homes");
        labelW.getStyleClass().add("labelW");
        
        // Start Label
        Label labelS= new Label("To get started");
        labelS.getStyleClass().add("labelS");
        
        // Instruction Label
        Label labelI= new Label(" 1) Choose the price limit at the top right-hand corner of the screen \n 2) Click on the left/right buttons at the screen \n 3) Click Begin to start browsing");
        labelI.getStyleClass().add("labelI");
        
        // Begin button 
        Button beginBtn= new Button("Begin");
        beginBtn.getStyleClass().add("button");
       
        
        root.getChildren().addAll(logoImage,labelW,labelS,labelI,beginBtn);
        
        // Positions of the controls (Welcome Page)
        AnchorPane.setTopAnchor(logoImage,30.0);
        AnchorPane.setLeftAnchor(logoImage,400.0);
        
        AnchorPane.setTopAnchor(labelS, 300.0);
        AnchorPane.setLeftAnchor(labelS, 450.0);
        
        AnchorPane.setTopAnchor(labelI, 350.0);
        AnchorPane.setLeftAnchor(labelI, 300.0);
        
        AnchorPane.setTopAnchor(labelW, 210.0);
        AnchorPane.setLeftAnchor(labelW, 410.0);
        
        AnchorPane.setBottomAnchor(beginBtn, 50.0);
        AnchorPane.setLeftAnchor(beginBtn, 500.0);
      
        welcomeScene = new Scene(root);
        welcomeScene.getStylesheets().add("MaStyling.css");
      
        // Action for the begin button to go to the main scene
        beginBtn.setOnAction(e -> primaryStage.setScene(mainScene));   
   
        // Create a new border pane
        pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(1000, 750);

        // Creates the top and bottom anchor panes
        AnchorPane topPane = new AnchorPane();
        AnchorPane bottomPane = new AnchorPane();

        // Create a new HBox for price selection
        HBox price = new HBox();
        price.setPadding(new Insets(5, 5, 0, 5));
        price.setSpacing(20);

        // Create a new HBox for borough selection
        HBox boroughs = new HBox();
        boroughs.setPadding(new Insets(5, 5, 0, 5));
        boroughs.setSpacing(20);

        //Create a new HBox for the labels that show the borough details
        VBox boroughDetails = new VBox();
        boroughDetails.setPadding(new Insets(0,10,20,10));
        boroughDetails.setAlignment(Pos.CENTER_LEFT);
        boroughDetails.setSpacing(10);

        // Creates the back and forward buttons
        Button back = new Button("<");

        Button forward = new Button(">");

        // Creates the combo boxes for price range
        createComboBoxes();

        // Add the anchor panes into the main border pane
        pane.setTop(topPane);
        pane.setBottom(bottomPane);
        pane.setLeft(boroughDetails);

        // Add the HBox into the top anchor pane
        topPane.getChildren().addAll(boroughs, price);
        topPane.setLeftAnchor(boroughs, 10.0);
        topPane.setRightAnchor(price, 10.0);

        // Add the forward and back buttons into the bottom anchor pane
        bottomPane.getChildren().addAll(back, forward);
        bottomPane.setLeftAnchor(back, 10.0);
        bottomPane.setRightAnchor(forward, 10.0);

        // Add buttons and labels into the price HBox
        price.getChildren().addAll(fromLabel, fromBox, toLabel, toBox);

        // Add buttons and labels into the boroughs HBox
        boroughs.getChildren().addAll(borough, boroughBox);

        //Add the labels that show the borourgh details into the VBox
        boroughDetails.getChildren().addAll(borughLabel,priceRangeLabel,numberOfPropertiesLabel);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        mainScene = new Scene(pane);
        primaryStage.setTitle("LDN Homes App");
        primaryStage.setScene(welcomeScene);
        primaryStage.setHeight(800);
        primaryStage.setWidth(1050);

        // Show the Stage (window)
        primaryStage.show();

        properties = loader.load();
    }

    /**
     * This method selects all the properties from the properties ArrayList that have a price between minPriceSelected and maxPriceSelected
     * and adds the poroperties to the filterProperty ArrayList.
     * @param int minPriceSelected the minimum price selected by the user
     * @param int maxPriceSelected the maximum price selected by the user
     * @author Mugur-Cristian Iacob(20081981)
     */
    private void selectProperty(int minPriceSelected, int maxPriceSelected,String borough)
    {
        int i =0;
        int price = 0;
        filterProperty.clear();
        canPressButton = false;

        while(i < properties.size())
        {  
            price = properties.get(i).getPrice();
            String neighbourhood = properties.get(i).getNeighbourhood();
            if( price>=minPriceSelected && price<=maxPriceSelected && neighbourhood.equals(borough))
            {
                filterProperty.add(properties.get(i));
            }
            i++;
        }

        if(filterProperty.size()>0){
            canPressButton = true;
        }
    }

    /**
     * This method selects all the properties from the properties ArrayList that are in the selected borough
     * and adds the poroperties to the filterProperty ArrayList.
     * @param String borough The property selected by the user
     * @author Mugur-Cristian Iacob(20081981)
     */
    private void selectPropertyBasedOnBororugh(String borough)
    {
        int i = 0;
        filterProperty.clear();
        canPressButton = false;

        while(i < properties.size())
        {  
            String neighbourhood = properties.get(i).getNeighbourhood();
            if(neighbourhood.equals(borough))
            {
                filterProperty.add(properties.get(i));
            }
            i++;
        }

        if(filterProperty.size()>0){
            canPressButton = true;
        }
    }

    /**
     * This method returns the minimum value selected by the user in the ComboBox called fromBox.
     * @return int minValue
     * @author Mugur-Cristian Iacob(20081981)
     */
    private int getMinPriceSelected()
    {
        int minValue =  (int)fromBox.getValue();

        return minValue; 
    }

    /**
     * This method return the maximum price value choosen by the user in the ComboBox called toBox.
     * @return int maxValue
     * @author Mugur-Cristian Iacob(20081981)
     */
    private int getMaxPriceSelected()
    {
        int maxValue = (int)toBox.getValue();

        return maxValue;
    }

    /**
     * This method loads the image of the map representing the boroughs into the center of the BorderPane pane.
     * @author Mugur-Cristian Iacob(20081981)
     */
    private void addMapImage()
    {
        ImageView image = new ImageView(ImageFileManager.loadImage("boroughs.png"));
        image.setFitHeight(650);
        image.setFitWidth(750);

        Label imageLabel = new Label("",image);

        pane.setCenter(imageLabel);
    }

    /**
     * Creates the combo boxes for the price range and London boroughs.
     * 
     * @author Frantisek Hermanek K20077556
     */
    private void createComboBoxes()
    {
        // Creates the combo box for the lower end of the property selection
        fromBox = new ComboBox();
        fromBox.getItems().addAll(0, 100, 250, 500, 750, 1000, 2500, 5000, 7000);

        // Checks if the selected price range is valid
        fromBox.setOnAction((event) -> {
                int selectedIndex = fromBox.getSelectionModel().getSelectedIndex();
                setFromIndex(selectedIndex);
            });

        // Creates the combo box for the lower end of the property selection
        toBox = new ComboBox();
        toBox.getItems().addAll(0, 100, 250, 500, 750, 1000, 2500, 5000, 7000);

        // Checks if the selected price range is valid
        toBox.setOnAction((event) -> {
                int selectedIndex = toBox.getSelectionModel().getSelectedIndex();
                setToIndex(selectedIndex);
            });

        //Creates a choice box to store the boroughs of London
        boroughBox = new ComboBox();

        boroughBox.getItems().addAll("City of London", "Barking and Dagenham", "Barnet", "Bexley",
            "Brent", "Bromley", "Camden", "Croydon", "Ealing", "Enfield", "Greenwich",
            "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow", "Havering",
            "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea",
            "Kingston upon Thames", "Lambeth, Lewisham", "Merton", "Newham",
            "Redbridge", "Richmond upon Thames", "Southwark", "Sutton",
            "Tower Hamlets", "Waltham Forest", "Wandsworth", "Westminster");

        //Have to reference this    
        boroughBox.setCellFactory(
            new Callback<ListView<String>, ListCell<String>>() {
                @Override public ListCell<String> call(ListView<String> param) {
                    final ListCell<String> cell = new ListCell<String>() {
                            {
                                super.setPrefWidth(100);
                            }    
                            @Override public void updateItem(String item, 
                            boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);    
                                    Tooltip tooltip = new Tooltip();
                                    tooltip.setText("Number of properties: " +  checkNumberOfProperties(item));
                                    setTooltip(tooltip);
                                    if (checkNumberOfProperties(item) > 1000) {
                                        setTextFill(Color.RED);
                                    }
                                    else if (checkNumberOfProperties(item) < 300){
                                        setTextFill(Color.GREEN);
                                    }
                                    else {
                                        setTextFill(Color.ORANGE);
                                    }
                                }
                                else {
                                    setText(null);
                                }
                            }
                        };
                    return cell;
                }
            });

        boroughBox.setOnAction((event)-> {
                String borough = returnBorough() ;  
                displayBoroughDetails(borough);
                propertyView.open(borough, filterProperty);
            });
    } 

    public int checkNumberOfProperties(String item )
    {
        selectPropertyBasedOnBororugh(item);

        return filterProperty.size();
    }

    /**
     * This method returns a String that contains the name of the borough selected or "Borough not selected" in case the user
     * has not selected a borough.
     * @return borough The borough selected
     * @author Mugur-Cristian Iacob(20081981)
     */
    public String returnBorough()
    {
        if(boroughBox.getValue()!=null){
            String borough = (String) boroughBox.getValue();
            return borough;
        }
        else
        {
            return "Borough not selected";
        }
    }

    /**
     * Checks whether the selected price range is valid.
     * 
     * @param index The selected index from the fromBox combo box.
     * @author Frantisek Hermanek K20077556
     */
    private void setFromIndex(int index)
    {
        fromIndex = index;
        if(fromIndex > toIndex && toIndex >= 0){
            showInvalidPriceRangeError();
        }
        else if(getMaxPriceSelected()>0){
            isMinPriceSelected=true;
            isMaxPriceSelected= true;
            addMapImage();
            displayBoroughDetails(returnBorough());
        }
    }

    /**
     * Checks whether the selected price range is valid.
     * 
     * @param index The selected index from the toBox combo box.
     * @author Frantisek Hermanek K20077556
     */
    private void setToIndex(int index)
    {
        toIndex = index;
        if(fromIndex > toIndex && fromIndex > 0){
            showInvalidPriceRangeError();
        }
        else if(getMinPriceSelected()>=0){
            isMinPriceSelected=true;
            isMaxPriceSelected= true;
            addMapImage();
            displayBoroughDetails(returnBorough());
        }
    }

    /**
     * This method displayes the name of the borough selected, the price range selected by the user and the total number of
     * properties within the price range selected.
     * @param borough The borough selected
     * @author Mugur-Cristian Iacob(20081981)
     */
    private void displayBoroughDetails(String borough)
    {
        if(isMinPriceSelected && isMaxPriceSelected){
            selectProperty(getMinPriceSelected(), getMaxPriceSelected(),borough);
            borughLabel.setText("Borough : " + borough);
            priceRangeLabel.setText("Price range: " + "" + getMinPriceSelected() + "-" + getMaxPriceSelected() );
            numberOfPropertiesLabel.setText("Number of properties : " + filterProperty.size());
        }
        else
        {
            selectPropertyBasedOnBororugh(borough);
            borughLabel.setText("Borough : " + borough);
            priceRangeLabel.setText("Price range: not selected" );
            numberOfPropertiesLabel.setText("Number of properties : " + filterProperty.size());
        }
    }

    /**
     * Creates a notification if the selected price range is invalid.
     * 
     * @author Frantisek Hermanek K20077556
     */
    private void showInvalidPriceRangeError()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Price Range Error");
        alert.setHeaderText(null);
        alert.setContentText("The selected price range is invalid.");

        alert.showAndWait();
    }
}