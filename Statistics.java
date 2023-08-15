import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class represents statsictics 
 *
 * @author Lubna Taraki(K20031620)
 * @version 25/03/2021
 */
public abstract class Statistics
{
    private String statisticTitle;
    private HashMap<String, Integer> allBoroughs;//hashmap with all boroughs and number properties in each borough
    private AirbnbDataLoader portfolio;
    
    /**
     * 
     */
    public Statistics()
    {        
        allBoroughs = new HashMap<>();
        portfolio = new AirbnbDataLoader();
        for(AirbnbListing property : portfolio.load()) {
            int counter = allBoroughs.getOrDefault(property.getNeighbourhood(), 0);
            allBoroughs.put(property.getNeighbourhood(), counter + 1);
        }
    }

    /**
     * @return ArrayList of all listings in data set
     */
    protected ArrayList<AirbnbListing> getPortfolio()
    {
        return portfolio.load();
    }
    
    /**
     * @return statistic title
     */
    protected String getStatisticTitle()
    {
        return statisticTitle;
    }
    
    /**
     * @return hashmap containg all boroughs and number of properties in them
     */
    protected HashMap<String, Integer> getAllBoroughs()
    {
        return allBoroughs;
    }
    
    /**
     * @return value of calculated statitic
     */
    abstract public Object calculateStatistic();
    
    /**
     * Set title to describe statistic
     */
    protected void setStatisticTitle(String input)
    {
        statisticTitle = input;
    }
    
}
