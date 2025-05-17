import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. Modified, GUI uses JavaFX instead of Swing.
 * 
 * Original authors: Michael Kölling and Josh Murphy
 *
 * @author Jeffery Raphael
 * @version 2.0
 */
public class PropertyViewer extends Application implements EventHandler<ActionEvent>{

    private Portfolio portfolio;
    private PropertyViewerGUI gui;
    private int index;
    private int fav_total;
    
    
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        index = 0;
        
        gui.showProperty(portfolio.getProperty(index),fav_total);
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene mainScene = new Scene(gui.getMainPane(), 500, 350); //width, height
        
        stage.setTitle("Property Viewer");
        stage.setScene(mainScene);
        stage.setWidth(500);
        stage.setHeight(325);
        stage.setResizable(false);
        
        // Show the Stage (window)
        stage.show();
    }    
    
    
    /**
     * Handles click events, i.e., executed when button is clicked.
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String text = ((Button)actionEvent.getSource()).getText();
        
        switch(text) {
            case "Next":
                nextClick();
                break;
            case "Previous":
                prevClick();
                break;
            case "Toggle Favourite":
                favClick();
            break;
            case "Nearest Neighbour":
                nearClick();
            break;
            default:
                System.out.println("Unknown Button Press");
        }
    }
    
    
    /**
     * Method used to control what happens when the next button is clicked.
     * Displays the next property for the user with its relevant data.
     */
    private void nextClick() {
        index ++;
        
        if (index>portfolio.numberOfProperties()-1){
            index = 0;            
        }
        
        gui.showProperty(portfolio.getProperty(index),fav_total);
        
    }

    
    /**
     * Method to control the functionality when the previous button is clicked
     * Displays the previous property for the user with its relevant data.
     */    
    private void prevClick() {
        index --;

        if (index<0){
            index = portfolio.numberOfProperties() - 1; //Assigns index such that it points to the last property.
        }
        
        gui.showProperty(portfolio.getProperty(index),fav_total);        
        
    }

    
    /**
     * Used to toggle properties as favourited or not, displaying them to the user.
     */    
    private void favClick() {
        Property current_property = portfolio.getProperty(index); 
        current_property.toggleFavourite();
        
        if (current_property.isFavourite()){
            fav_total += 1;
            current_property.setFavouriteId(fav_total);
        }
        
        else{
            for (int i = 0;i<portfolio.numberOfProperties();i++){
                int fav_id = portfolio.getProperty(i).getFavouriteId();
                if (fav_id>current_property.getFavouriteId()){
                    portfolio.getProperty(i).setFavouriteId(fav_id - 1);
                }
            }
            current_property.setFavouriteId(0);
            fav_total -- ;  
        }
        
        
        gui.showProperty(portfolio.getProperty(index),fav_total);
        
    }
    
    /**
     * Displays the nearest property to the one previously shown to the user.
     */    
    private void nearClick() {
        
        ArrayList <Double> property_vector = portfolio.getProperty(index).getVector();
        double smallest_distance = Double.MAX_VALUE;
        int smallest_index = 0;
        
        for (int j = 0; j<portfolio.numberOfProperties(); j++){
            if (j != index){
                ArrayList <Double> next_prop_vector = portfolio.getProperty(j).getVector(); 
                double distance = find_euclid_dist(property_vector,next_prop_vector);
                if (distance<smallest_distance){
                    smallest_distance = distance;
                    smallest_index = j;
                }
                
            }
        }
        
        index = smallest_index;
        gui.showProperty(portfolio.getProperty(index),fav_total);
        
    }
    
    /**
     * Returns the euclidean distance between two vectors.
     */
    private double find_euclid_dist(ArrayList <Double> vec_prop1, ArrayList <Double> vec_prop2){
        double euclid_dist = 0;
        
        for (int vec_i=0;vec_i<vec_prop1.size();vec_i++){
            euclid_dist += Math.pow((vec_prop1.get(vec_i)-vec_prop2.get(vec_i)),2);
        }
        euclid_dist = Math.sqrt(euclid_dist);
        
        return euclid_dist;
    }

}