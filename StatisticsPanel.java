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
import java.awt.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Main statistic panel. On this panel four statistic boxes will be displayed 
 * containing information about four statistics. Statistics can be changed in each box
 * providing that the same statistic is not displayed in two boxes at the same time
 *
 * @author Lubna Taraki (K20031620)
 * @25/03/2021
 */
public class StatisticsPanel extends Application
{
    private Queue<Statistics> statistics; //holds all statistics   
    
    // four statistic boxes to be displayed on panel
    private StatisticBox statsBoxOne;
    private StatisticBox statsBoxTwo;
    private StatisticBox statsBoxThree;
    private StatisticBox statsBoxFour;
    
    
    /**
     * Creates four stats boxes and creates and fills statistics queue
     */
    public StatisticsPanel()
    {
        statistics = new LinkedList<>();
         
        statsBoxOne = new StatisticBox();
        statsBoxTwo = new StatisticBox();
        statsBoxThree = new StatisticBox();
        statsBoxFour = new StatisticBox();
        
        statistics.add(new MostExpensiveBorough());
        statistics.add(new AverageReviewsPerListing());
        statistics.add(new TotalListings());
        statistics.add(new NumberOfHosts());
        statistics.add(new TotalFullListings());
        statistics.add(new GreenestBorough());
    }
    
    /**
     * Linked to statsBoxOne; changes statistic displayed when buttons clicked
     * @ param ActionEvent event that triggers this method
     */
    private void changeBoxOneStat(ActionEvent event)
    {
        Statistics statistic = statistics.poll();
        statistics.add(statsBoxOne.getStat());
        statsBoxOne.setStat(statistic);                            
    }
    
    /**
     * Linked to statsBoxTwo; changes statistic displayed when buttons clicked
     * @ param ActionEvent event that triggers this method
     */
    private void changeBoxTwoStat(ActionEvent event)
    {
        Statistics statistic = statistics.poll();
        statistics.add(statsBoxTwo.getStat());
        statsBoxTwo.setStat(statistic);                            
    }
    
    /**
     * Linked to statsBoxThree; changes statistic displayed when buttons clicked
     * @ param ActionEvent event that triggers this method
     */
    private void changeBoxThreeStat(ActionEvent event)
    {
        Statistics statistic = statistics.poll();
        statistics.add(statsBoxThree.getStat());
        statsBoxThree.setStat(statistic);                            
    }
    
    /**
     * Linked to statsBoxFour; changes statistic displayed when buttons clicked
     * @ param ActionEvent event that triggers this method
     */
    private void changeBoxFourStat(ActionEvent event)
    {
        Statistics statistic = statistics.poll();
        statistics.add(statsBoxFour.getStat());
        statsBoxFour.setStat(statistic);                             
    }
    
    /**
     * Sets up statistics displayed when panel initially launched
     * @param StatisticBox statsBox thst is being set up
     */
    public void setInitalStatistics(StatisticBox box)
    {      
        Statistics stat = statistics.poll();
        box.setStat(stat);    
    }
    
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */ 
    @Override
    public void start(Stage stage)
    {        
        // Creates  grid pane as root
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(60,60,60,60));
        pane.setVgap(20);
        pane.setHgap(30);
        
        //creates four statistic boxes in panel
        GridPane statsOne = statsBoxOne.createStatsBox();
        GridPane statsTwo = statsBoxTwo.createStatsBox();
        GridPane statsThree = statsBoxThree.createStatsBox();
        GridPane statsFour = statsBoxFour.createStatsBox();
        
        //sets initial statistic displayed on each panel
        ArrayList<StatisticBox> allStatsBoxes = new ArrayList<>();
        allStatsBoxes.add(statsBoxOne);
        allStatsBoxes.add(statsBoxTwo);
        allStatsBoxes.add(statsBoxThree);
        allStatsBoxes.add(statsBoxFour);
        for (StatisticBox box : allStatsBoxes){
            setInitalStatistics(box);
        }
        
        //attaches method to each button in panel
        statsBoxOne.getBack().setOnAction(this :: changeBoxOneStat);
        statsBoxOne.getForward().setOnAction(this :: changeBoxOneStat);
        statsBoxTwo.getBack().setOnAction(this :: changeBoxTwoStat);
        statsBoxTwo.getForward().setOnAction(this :: changeBoxTwoStat);
        statsBoxThree.getBack().setOnAction(this :: changeBoxThreeStat);
        statsBoxThree.getForward().setOnAction(this :: changeBoxThreeStat);
        statsBoxFour.getBack().setOnAction(this :: changeBoxFourStat);
        statsBoxFour.getForward().setOnAction(this :: changeBoxFourStat);
        
        //allocates each statsBox a cell in the main grid and adds as children
        GridPane.setConstraints (statsOne, 0, 0);
        GridPane.setConstraints (statsTwo, 1, 0);
        GridPane.setConstraints (statsThree, 0, 1);
        GridPane.setConstraints (statsFour, 1, 1);
        pane.getChildren().addAll(statsOne, statsTwo, statsThree, statsFour);
        
        //sets column constraints on main grid pane
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column1.setPercentWidth(50);
        column1.setHalignment(HPos.CENTER);
        column2.setPercentWidth(50);
        column2.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(column1);
        pane.getColumnConstraints().add(column2);

        //sets row constraints on main grid pane
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row1.setPercentHeight(50);
        row1.setValignment(VPos.CENTER);
        row2.setPercentHeight(50);
        row2.setValignment(VPos.CENTER);
        pane.getRowConstraints().add(row1);
        pane.getRowConstraints().add(row2);
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 650,500);
        stage.setTitle("AirBnB App");
        stage.setScene(scene);
        //pane.setGridLinesVisible(true);
        //statsOne.setGridLinesVisible(true);
        
        // Show the Stage (window)
        stage.show();
    }
}