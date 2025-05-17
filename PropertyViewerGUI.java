
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


/**
 * PropertyViewerGUI provides the GUI for the project. It displays the property
 * and strings, and it listens to button clicks. Modified to use JavaFX
 * 
 * Original authors: Michael Kölling, David J Barnes, and Josh Murphy 
 * 
 * @author Jeffery Raphael
 * @version 4.0
 */
public class PropertyViewerGUI {
    
    private PropertyViewer viewer;
    private Label idLabel = new Label("default");
    private Label favouriteLabel = new Label("");
    
    private TextField hostIDField;
    private TextField hostNameField;
    private TextField neighbourhoodField;
    private TextField roomTypeField;
    private TextField priceField;
    private TextField minNightsField;

    public PropertyViewerGUI(PropertyViewer viewer) {
        
        this.viewer = viewer;
        
        hostIDField = new TextField("default");
        hostIDField.setEditable(false);
        
        hostNameField = new TextField("default");
        hostNameField.setEditable(false);
        
        neighbourhoodField = new TextField("default");
        neighbourhoodField.setEditable(false);
        
        roomTypeField = new TextField("default");
        roomTypeField.setEditable(false);
        
        priceField = new TextField("default");
        priceField.setEditable(false);
        
        minNightsField = new TextField("default");   
        minNightsField.setEditable(false);
    }

    
    /**
     * This method creates the PropertyViewer main Scene
     */
    public BorderPane getMainPane() {
        BorderPane mainPane = new BorderPane();
        
        mainPane.setTop(idLabel);
        mainPane.setMargin(idLabel, new Insets(5, 5, 5, 5));

        mainPane.setBottom(favouriteLabel);
        mainPane.setMargin(favouriteLabel, new Insets(5, 5, 5, 5));

        GridPane buttonPane = getButtonPane();
        mainPane.setLeft(buttonPane);
        mainPane.setMargin(buttonPane, new Insets(5, 5, 5, 10));
        
        GridPane dataPane = getDataPane(); 
        mainPane.setCenter(dataPane);
        mainPane.setMargin(dataPane, new Insets(5, 10, 10, 5));
        
        return mainPane;
    }
    
    
    /**
     * Creates a pane containing all the buttons
     */
    private GridPane getButtonPane() {
        GridPane buttonPane = new GridPane();
        buttonPane.setVgap(10);       
        
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Previous");
        Button favButton = new Button("Toggle Favourite");
        Button nearButton = new Button("Nearest Neighbour");

        nextButton.setOnAction(viewer);
        nextButton.setMinWidth(125);
        prevButton.setOnAction(viewer);
        prevButton.setMinWidth(125);        
        favButton.setOnAction(viewer);
        favButton.setMinWidth(125);        
        nearButton.setOnAction(viewer);
        nearButton.setMinWidth(125); 
        
        buttonPane.add(nextButton, 0, 0);
        buttonPane.add(prevButton, 0, 1);
        buttonPane.add(favButton, 0, 2);
        buttonPane.add(nearButton, 0, 3);
        
        return buttonPane;
    }
    
    
    /**
     * Creates a pane containing labels & textfields ---to display
     * the property data
     */
    private GridPane getDataPane() {
        
        GridPane dataPane = new GridPane();
        dataPane.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        dataPane.setVgap(10);
        dataPane.setHgap(10);

        dataPane.add(new Label("HostID:"), 1, 1);
        dataPane.add(hostIDField, 2, 1);

        dataPane.add(new Label("Host Name:"), 1, 2);
        dataPane.add(hostNameField, 2, 2);      
        
        dataPane.add(new Label("Neighbourhood:"), 1, 3);
        dataPane.add(neighbourhoodField, 2, 3); 
        
        dataPane.add(new Label("Room Type:"), 1, 4);
        dataPane.add(roomTypeField, 2, 4); 

        dataPane.add(new Label("Price:"), 1, 5);
        dataPane.add(priceField, 2, 5);   
        
        dataPane.add(new Label("Minimum Nights:"), 1, 6);
        dataPane.add(minNightsField, 2, 6);  
    
        return dataPane;
    }
    
    
    /**
     * Updates GUI text fields
     */
    public void showProperty(Property property, int fav_total) {
        hostIDField.setText(property.getHostID());
        hostNameField.setText(property.getHostName());
        neighbourhoodField.setText(property.getNeighbourhood());
        roomTypeField.setText(property.getRoomType());
        priceField.setText("£" + property.getPrice());
        minNightsField.setText(property.getMinNights());
        
        
        idLabel.setText(property.getID());
        
        if (property.isFavourite()){
            favouriteLabel.setText("Favourite "+property.getFavouriteId()+" of "+fav_total);
        }
        else{
            favouriteLabel.setText("");
        }
    }
}
