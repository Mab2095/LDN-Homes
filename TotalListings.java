import java.util.ArrayList;

/**
 * Statistic that displays total number of properties on Airbnb app
 *
 * @ Lubna Taraki (K20031620)
 * @ 25/03/2021
 */
public class TotalListings extends Statistics
{
    /**
     * Sets the title, deatiling contenets of statistic
     */
    public TotalListings()
    {
        setStatisticTitle("Total Listing Available: ");
    }

    /**
     * @return number of properties in csv file
     */
    public Integer calculateStatistic()
    {
        return getPortfolio().size();
    }
    
}

