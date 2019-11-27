package earthquakes.osm;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Place {
    private static Logger logger = LoggerFactory.getLogger(Place.class);
    public long place_id;
    public double lat;
    public double lon;
    public String display_name;
    public String type;
    
     /**
     * Create a List of Place object from json representation
     * 
     * @param json String of json returned by API endpoint {@code /classes/search}
     * @return a new List of Place object
     * @see <a href=
     *      "https://tools.ietf.org/html/rfc7946">https://tools.ietf.org/html/rfc7946</a>
     */
    public static List<Place> listFromJSON(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<Place> place = objectMapper.readValue(json, new TypeReference<List<Place>>(){});
            return place;
        } catch (JsonProcessingException jpe) {
            logger.error("JsonProcessingException:" + jpe);
            return null;
        } catch (Exception e) {
            logger.error("Exception:" + e);
            return null;
        }
    }
    
}
