import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Pos;

/**
 * Class that produces individual statistic box; four of these are present at one 
 * time in the statistics panel
 *
 * @Lubna TarakiK20031620 
 * @ 25/03/2021
 */

public class StatisticBox 

{
    // Different componenets of Statistics Box
    private Statistics currentStat; // statistic being displayed in box
    private Label statsTitle;
    private Label stat;
    private Button back;
    private Button forward;

    /**
     * 
     */
    public StatisticBox()
    {
        statsTitle = new Label("");
        stat = new Label("");
        back = new Button("<");
        forward = new Button(">");
    }
    
    /**
     * @return GridPane returns template of statisctics box
     */
    public GridPane createStatsBox()
    {
        //Sets constraints for buttons
        back.setMaxHeight(Double.MAX_VALUE);
        GridPane.setConstraints (back, 0, 0);
        
        forward.setMaxHeight(Double.MAX_VALUE);
        GridPane.setConstraints (forward, 2, 0);
        
        //Creates VBox stores the labels, sets constraints
        VBox infoBox = new VBox(8);
        infoBox.getChildren().addAll(statsTitle, stat);
        infoBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints (infoBox, 1, 0);
        
        //Creates main gridpane
        GridPane statsBox = new GridPane();
        statsBox.setAlignment(Pos.CENTER);
        
        //Sets constriants on size of rows and columns
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(80);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(10);
        statsBox.getColumnConstraints().addAll(col1,col2,col3);
        statsBox.getRowConstraints().addAll(row);
        
        //adds all elements to gridpane
        statsBox.getChildren().addAll(back, infoBox, forward);
        
        return statsBox;
    }
            
    // /**
     // * @return VBox that holds the statistic details
     // */
    // public VBox getBox()
    // {
        // return infoBox;
    // }
    
    /**
     * @return current statistic being displayed
     */
    public Statistics getStat()
    {
        return currentStat;
    }
    
    /**
     * Updates current statostci being displayed
     * @param Statitsics statistic to be displayed in panel
     */
    public void setStat(Statistics statistic)
    {
        currentStat = statistic;
        statsTitle.setText(statistic.getStatisticTitle().toString());
        stat.setText(statistic.calculateStatistic().toString());
    }
    
    /**
     * @return back button
     */
    public Button getBack()
    {
        return back;
    }
    
    /**
     * @return forward button
     */
    public Button getForward()
    {
        return forward;
    }
}