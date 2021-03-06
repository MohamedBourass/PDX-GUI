package com.personaldata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.personaldata.model.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PersonOverviewController {
    //@FXML
    //private TableView<Person> personTable;
    //@FXML
    //private TableColumn<Person, String> firstNameColumn;
    //@FXML
    //private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label postalAddressLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label ipAddressLabel;
    @FXML
    private Label macAddressLabel;

    
    
//    @FXML
//    private Label streetLabel;
//    @FXML
//    private Label postalCodeLabel;
//    @FXML
//    private Label cityLabel;
//    @FXML
//    private Label birthdayLabel;

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
//      firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
//      lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
//      // Auto resize columns
//      personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      // clear person
    		XStream xstream = new XStream(new JettisonMappedXmlDriver());
    		

    		BufferedReader br = null;
    		FileReader fr = null;
    		String dataJson = "";
    		
    		try {

    			//br = new BufferedReader(new FileReader(FILENAME));
    			fr = new FileReader("src/main/resources/com/personaldata/mbourass.json");
    			br = new BufferedReader(fr);

    			String sCurrentLine;
    			
    			while ((sCurrentLine = br.readLine()) != null) {
    				dataJson += sCurrentLine;
    				System.out.println(sCurrentLine);
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				if (br != null)
    					br.close();

    				if (fr != null)
    					fr.close();

    			} catch (IOException ex) {

    				ex.printStackTrace();
    			}
    		}
    		
    		//System.out.println("Toto => " + dataJson);
    		Person customer = (Person) xstream.fromXML(dataJson);
    		showPersonDetails(customer);

      // Listen for selection changes
//      personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
//
//        @Override
//        public void changed(ObservableValue<? extends Person> observable,
//            Person oldValue, Person newValue) {
//          showPersonDetails(newValue);
//        }
//      });
    }
    
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {

	    	if(null != person) {
	    		firstNameLabel.setText(person.getPrivateData().getFirstName());
	    		lastNameLabel.setText(person.getPrivateData().getLastName());
	    	    postalAddressLabel.setText(person.getPrivateData().getPostalAddress());
	    	    phoneNumberLabel.setText(person.getPrivateData().getPhoneNumber());
	    	    emailLabel.setText(person.getPrivateData().getEmail());
	    	    ipAddressLabel.setText(person.getPrivateData().getIpAddress());
	    	    macAddressLabel.setText(person.getPrivateData().getMacAddress());
	    	    
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
//      int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//      personTable.getItems().remove(selectedIndex);
    }
    
    /**
     * Called when the user clicks the new button.
     * Opens a dialog to edit details for a new person.
     */
    /*@FXML
    private void handleNewPerson() {
      Person tempPerson = new Person();
      boolean okClicked = mainApp.showPrivateData(tempPerson);
      if (okClicked) {
        mainApp.getPersonData().add(tempPerson);
      }
    }*/

    /**
     * Called when the user clicks the edit button.
     * Opens a dialog to edit details for the selected person.
     */
   /* @FXML
    private void handleEditPerson() {
      //Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
      Person selectedPerson = new Person();
      if (selectedPerson != null) {
        boolean okClicked = mainApp.showPrivateData(selectedPerson);
        if (okClicked) {
          refreshPersonTable();
          showPersonDetails(selectedPerson);
        }

      } else {
        // Nothing selected
//        Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
//            "Please select a person in the table.",
//            "No Person Selected", "No Selection");
        
        System.out.println(mainApp.getPrimaryStage() + "No Person selected..");
      }
    }*/
    

    /**
     * Refreshes the table. This is only necessary if an item that is already in
     * the table is changed. New and deleted items are refreshed automatically.
     * 
     * This is a workaround because otherwise we would need to use property
     * bindings in the model class and add a *property() method for each
     * property. Maybe this will not be necessary in future versions of JavaFX
     * (see http://javafx-jira.kenai.com/browse/RT-22599)
     */
    private void refreshPersonTable() {
//      int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//      personTable.setItems(null);
//      personTable.layout();
//      personTable.setItems(mainApp.getPersonData());
//      // Must set the selected index again (see http://javafx-jira.kenai.com/browse/RT-26291)
//      personTable.getSelectionModel().select(selectedIndex);
    }
}