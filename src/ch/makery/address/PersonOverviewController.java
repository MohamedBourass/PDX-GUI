package ch.makery.address;

import ch.makery.address.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */    
    @FXML
    private void initialize() {
      // Initialize the person table
      firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
      lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
      // Auto resize columns
      personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      // clear person
      showPersonDetails(null);

      // Listen for selection changes
      personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

        @Override
        public void changed(ObservableValue<? extends Person> observable,
            Person oldValue, Person newValue) {
          showPersonDetails(newValue);
        }
      });
    }
    
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {

    // use setText(...) on all labels with info from the person object
    // use setText("") on all labels if the person is null
	    	if(null != person) {
	    		firstNameLabel.setText(person.getFirstName());
	    		lastNameLabel.setText(person.getLastName());
	    		streetLabel.setText(person.getStreet());
	    		postalCodeLabel.setText(String.valueOf(person.getPostalCode()));
	    	    cityLabel.setText(person.getCity());
	    	    birthdayLabel.setText(person.getBirthday().toString());
	    	} /*else {
	    		firstNameLabel.setText("");
	    		lastNameLabel.setText("");
	    		streetLabel.setText("");
	    		postalCodeLabel.setText("");
	    	    cityLabel.setText("");
	    	    birthdayLabel.setText("");
	    	}*/

    		
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
      int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
      personTable.getItems().remove(selectedIndex);
    }
}
