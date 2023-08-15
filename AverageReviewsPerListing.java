import java.util.HashMap;

/**
 * Calculates average reviews per listing 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AverageReviewsPerListing extends Statistics
{
    /**
     * Sets statitics title
     */
    public AverageReviewsPerListing()
    {
        setStatisticTitle("Average Reviews per Listing: ");
    }

    /**
     * Calculates total statistics on website then divides by number of preoperties 
     * @return average reviews per statistic
     */
    public Integer calculateStatistic()
    {
        int totalReviews = 0;
        for (AirbnbListing property: getPortfolio()){
            int numberOfReviews = property.getNumberOfReviews();
            totalReviews += numberOfReviews;
        }
        int averageReviews = totalReviews/getPortfolio().size();
        return averageReviews;
    }
        
}
