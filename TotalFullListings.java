
/**
 * Calculates total number of full rooms available in data set 
 *
 * @author Lubna Taraki (K20031620)
 * @version 25/03/2021
 */
public class TotalFullListings extends Statistics
{

    /**
     * Sets title for statistic
     */
    public TotalFullListings()
    {
        setStatisticTitle("Total full property listings: ");
    }

    /**
     * Checks the room type of listing and adds all listings that are full rooms
     * @return number of full rooms
     */
    public Integer calculateStatistic()
    {
        int totalNumber = 0;
        for (AirbnbListing property: getPortfolio()){
            if (property.getRoom_type().equals("Entire home/apt")){
                totalNumber ++;
            }
        }
        return totalNumber;
    }
}

