import java.util.ArrayList;

/**
 * Property is a class that defines a property for display.
 * 
 * @author  Michael Kölling and Josh Murphy
 * @version 2.0
 */

public class Property {

    private String id;
    private String description;
    private String hostID;
    private String hostName;
    private String neighbourhood;
    private double latitude;
    private double longitude;
    private String roomType;
    private int price;
    private int minimumNights;
    private int availability365;
    private boolean isFavourite;
    private int favouriteId = 0;
    

    public Property(String id, String name, String hostID, String hostName, 
            String neighbourhood, double latitude, double longitude, String roomType, 
            int price, int minimumNights, int availability365) {
        this.id = id;
        this.description = name;
        this.hostID = hostID;
        this.hostName = hostName;
        this.neighbourhood = neighbourhood;
        this.latitude = latitude;
        this.longitude  = longitude;
        this.roomType = roomType;
        this.price = price;
        this.minimumNights = minimumNights;
        this.availability365 = availability365;
        
        isFavourite = false;
    }
    
    
    /**
     * Return the Id of this property.
     */
    public String getID() {
        return id;
    }
    
    
    /**
     * Return the hostId of this property.
     */
    public String getHostID() {
        return hostID;
    }
    
    
    /**
     * Return the latitude of this property.
     */
    public double getLatitude() {
        return latitude;
    }
    
    
    /**
     * Return the longitude of this property.
     */
    public double getLongitude() {
        return longitude;
    }
    
    
    /**
     * Return the price of this property.
     */
    public int getPrice() {
        return price;
    }
    
    
    /**
     * Returns true if this property is currently marked as a favourite, false otherwise.
     */
    public boolean isFavourite() {
        return isFavourite;
    }
    
    
    /**
     * Return the host name of this property.
     */
    public String getHostName() {
        return hostName;
    }
    
    
    /**
     * Return the neighbourhood of this property.
     */
    public String getNeighbourhood() {
        return neighbourhood;
    }
    
    
    /**
     * Return the room type of this property.
     */
    public String getRoomType() {
        return roomType;
    }
    
    
    /**
     * Return the minimum number of nights this property can be booked for.
     */
    public String getMinNights() {
        return "" + minimumNights;
    }
    
    
    /**
     * Return the description of this property.
     */
    public String getDescription(){
        return description;
    }
    
    
    /**
     * Toggles whether this property is marked as a favourite or not.
     */
    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }

    
    /**
     * Sets the favouriteId
     */
    public void setFavouriteId(int id){
        favouriteId = id;
    }
    

    /**
     * Return the favourite id of this property.
     */
    public int getFavouriteId(){
        return favouriteId;
    }
    
    
    /**
     * Vectorises a property object
     */
    public ArrayList getVector() {
        
        ArrayList<Double> vector = new ArrayList<Double>();
    
        vector.add(this.latitude);
        vector.add(this.longitude);
        vector.add((double)this.price);
        vector.add((double)this.minimumNights);
        vector.add((double)this.availability365);
        
        return vector;
    }
}
