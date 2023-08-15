import java.util.HashMap;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;
/**
 * Calculates greenest borugh in London by dividing total number of trees in borough
 * by the area of borough. Number of tress calculated using data set below 
 * https://data.london.gov.uk/dataset/local-authority-maintained-trees#:~:text=This%20dataset%20includes%20the%20locations,over%20880%2C000%20of%20London's%20trees.
 *
 * @author Lubna Taraki(K20031620)
 * @version 25/03/2021
 */
public class GreenestBorough extends Statistics
{

    /**
     * Constructor for objects of class GreenestBorough
     */
    public GreenestBorough()
    {
        setStatisticTitle("Greenest Borough in London is: ");
    }

    /**
     * Calculates total number of trees in each borough using secondary data set
     * Divides by area of borough also derived from secondary data set; compares borough to 
     * find borough with most trees
     * @return greenes borough
     */
    public String calculateStatistic()
    {
            HashMap<String, Integer> treesInBorough = new HashMap<>();
            HashMap<String, Double> areaOfBorough = new HashMap<>();
            try{
                URL url = getClass().getResource("Borough_tree_list_2021.csv");
                CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
                String [] line;
                //skip the first row (column headers)
                reader.readNext();
                while ((line = reader.readNext()) != null) {
                    int counter = treesInBorough.getOrDefault(line[1],0);
                    treesInBorough.put(line[1], counter + 1);
                }
        } catch(IOException | URISyntaxException e){
            System.out.println("Failure! Something went wrong when loading the Trees In Borough file");
            e.printStackTrace();
        }
        
            try{
                URL url = getClass().getResource("london_borough_area.csv");
                CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
                String [] line;
                //skip the first row (column headers
                reader.readNext();
                while ((line = reader.readNext()) != null) {
                    String name = line[0];
                    double area = convertDouble(line [1]);
                    areaOfBorough.put(name,area);
                }
        } catch(IOException | URISyntaxException e){
            System.out.println("Failure! Something went wrong when loading the Trees In Borough file");
            e.printStackTrace();
        }
        
        for (String key : treesInBorough.keySet())
            for (String keytwo : areaOfBorough.keySet())
            if (key.equals(keytwo)){
                int averageNum = (int) Math.round(treesInBorough.get(key)/areaOfBorough.get(key));
                treesInBorough.put(key, averageNum);
            }
            
        String maxEntry = null;
        for (String key : treesInBorough.keySet())   
        {
            if (maxEntry == null || treesInBorough.get(key) > treesInBorough.get(maxEntry))
            {
                maxEntry = key;
            }
        }
        return maxEntry;        
    }
    
    /**
     *
     * @param doubleString the string to be converted to Double type
     * @return the Double value of the string, or -1.0 if the string is 
     * either empty or just whitespace
     */
    private Double convertDouble(String doubleString){
        if(doubleString != null && !doubleString.trim().equals("")){
            return Double.parseDouble(doubleString);
        }
        return -1.0;
    }
}

