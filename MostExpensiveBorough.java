import java.util.HashMap;

/**
 * Calculates most expensive borough to rent in; minimum nights taken into account
 * as well as price per night
 *
 * @author Lubna Taraki(K20031620)
 * @version 25/03/2021
 */
public class MostExpensiveBorough extends Statistics
{
    /**
     * Sets title for statistic 
     */
    public MostExpensiveBorough()
    {
        setStatisticTitle("Most Expensive Borough in London is: ");
    }
    
    /**
     * 
     */
    public String calculateStatistic()
    {
        HashMap<String, Integer> averagePriceBorough = new HashMap<>();
        for (AirbnbListing property : getPortfolio()){// make portflio.loadProperties() a variable in this class
            for (String borough : getAllBoroughs().keySet()){
                if (property.getNeighbourhood().equals(borough)){
                    int price = averagePriceBorough.getOrDefault(property.getNeighbourhood(), 0);//calculation all wrong
                    averagePriceBorough.put(property.getNeighbourhood(), price += property.getPrice() * property.getMinimumNights());

                }
            }
        }
        String maxEntry = null;

        for (String key : averagePriceBorough.keySet())
        {
            if (maxEntry == null || averagePriceBorough.get(key) > averagePriceBorough.get(maxEntry))
            {
                maxEntry = key;
            }
        }
        return maxEntry;
    }
    
}
