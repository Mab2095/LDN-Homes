import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.*;

/**
 * Write a description of JavaFX class PropertyView here.
 *
 * @author Frantisek Hermanek K20077556
 * @version 2021.03.25
 */
public class PropertyView extends Application
{
    // We keep track of the count, and label displaying the count:    
    private Label propertyNameLabel = new Label("default");
    private Label hostNameLabel = new Label("default");
    private Label neighbourhoodLabel = new Label("default");
    private Label roomTypeLabel = new Label("default");
    private Label minimumNightLabel = new Label("default");
    private Label priceLabel = new Label("default");
    
    private int propertyCounter = 0;
    
    private ArrayList<AirbnbListing> filterProperty = new ArrayList<>();
    
    private String boroughName;
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     * 
     * @author Frantisek Hermanek K20077556
     */
    @Override
    public void start(Stage stage)
    {
        // Create a new border pane
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(250, 250);
                
        // Create a new anchor pane
        AnchorPane buttonPane = new AnchorPane();
        
        // Create a new VBox for the property information
        VBox infoBox = new VBox();
        infoBox.setPadding(new Insets(0,10,20,10));
        infoBox.setSpacing(10);
        
        // Create a Button or any control item
        Button forward = new Button(">");
        forward.setOnAction(this::nextButton);
        
        Button back = new Button("<");
        back.setOnAction(this::backButton);
        
        // Add the label and anchor pane into the border pane        
        pane.setLeft(infoBox);
        pane.setBottom(buttonPane);
        
        // Add buttons into the anchor pane
        buttonPane.getChildren().addAll(forward, back);
        buttonPane.setLeftAnchor(back, 10.0);
        buttonPane.setRightAnchor(forward, 10.0);
        
        // Add property information into the infoBox VBox
        infoBox.getChildren().addAll(propertyNameLabel, hostNameLabel, neighbourhoodLabel,
                                    roomTypeLabel, minimumNightLabel, priceLabel);
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane);
        stage.setTitle(boroughName);
        stage.setScene(scene);
        stage.setHeight(300);
        stage.setWidth(500);

        // Show the Stage (window)
        stage.show();
    }
    
    /**
     * Is called from the GUI class when propery information is to be displayed.
     * Resets the property counter and adds filtered Airbnb properties from the GUI
     * "filterProperties" List into the list used in this class.
     * 
     * @param String borough The currently selected borough to be displayed.
     * @param ArrayList<AirbnbListing> properties The filtered ArrayList of Airbnb property listings to be copied.
     * 
     * @author Frantisek Hermanek K20077556
     */
    public void open(String borough, ArrayList<AirbnbListing> properties)
    {
        boroughName = borough;
        propertyCounter = 0;
        
        filterProperty.clear();
        filterProperty.addAll(properties);
        
        Stage stage = new Stage();
        start(stage);
        displayProperty(propertyCounter);
    }
    
    /**
     * This method displays each property that from the filterProperty ArrayList that containts proerties between the price range 
     * specified by the user.
     * @param int propertyIndex the index of the property to be displayed
     * @author Mugur-Cristian Iacob(k20081981) and Frantisek Hermanek K20077556
     */
    private void displayProperty(int propertyIndex)
    {
        AirbnbListing property = filterProperty.get(propertyIndex);
        
        propertyNameLabel.setText("Property name: " + property.getName());
        hostNameLabel.setText("Host name: " + property.getHost_name());
        neighbourhoodLabel.setText("Neighbourhood: " + property.getNeighbourhood());
        roomTypeLabel.setText("Room type: " + property.getRoom_type());
        minimumNightLabel.setText("Minimum nights: " + property.getMinimumNights());
        priceLabel.setText("Price: £" + property.getPrice() );
    }
    
    /**
     * This method implements the action that takes place when the next button is clicked.
     * @author Mugur-Cristian Iacob(20081981) and Frantisek Hermanek K20077556
     */
    private void nextButton(ActionEvent event)
    {
        if(propertyCounter < filterProperty.size() - 1){
            propertyCounter++;
            displayProperty(propertyCounter);
        }
    }

    /**
     * This method implements the action that takes place when the back button is cliked.
     * @author Mugur-Cristian Iacob(k20081981)
     */
    private void backButton(ActionEvent event)
    {
        if(propertyCounter > 0){
            propertyCounter--;
            displayProperty(propertyCounter);
        }
    }
}
