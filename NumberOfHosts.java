import java.util.HashMap;

/**
 * Calculates number of hosts on the server; not equal to total properties as some hosts have 
 * more than one 
 *
 * @author Lubna Taraki(K20031620)
 * @version 25/03/2021
 */
public class NumberOfHosts extends Statistics
{
    /**
     * Sets title of statistic
     */
    public NumberOfHosts()
    {
        setStatisticTitle("Number of Hosts on server: ");
    }

    /**
     * Creates hashmap connects properties with host
     * Uses size of 5hashmap to find number of hosts
     * @return number of hosts in data set
     */
    public Integer calculateStatistic()
    {
        HashMap<String, Integer> allHosts = new HashMap<>();
        for (AirbnbListing property : getPortfolio()){
            int listings = allHosts.getOrDefault(property.getHost_id(),0);
            allHosts.put(property.getHost_id(), listings += property.getCalculatedHostListingsCount());
        }
        
        int totalHosts = allHosts.size();
        return totalHosts;
    }
}
