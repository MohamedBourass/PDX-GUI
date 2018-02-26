package com.personaldata;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 */
public class RootLayoutController implements Initializable {

    // Reference to the main application
    private MainApp mainApp;
    
    //Reference to the 2 main anchor panes
    @FXML
    public AnchorPane treeviewPane;
    
    @FXML
    public AnchorPane mainPane;
    
    @FXML
    TreeView<String> treeview;
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        //mainApp.getPersonData().clear();
        //mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        /*FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }*/
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        /*File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }*/
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
    /*FileChooser fileChooser = new FileChooser();

    // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

    if (file != null) {
      // Make sure it has the correct extension
      if (!file.getPath().endsWith(".xml")) {
        file = new File(file.getPath() + ".xml");
      }
      mainApp.savePersonDataToFile(file);
    }*/
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        System.out.println(mainApp.getPrimaryStage() + ", Author: Mohamed Bourass" + ", PersonalData" + ", About");
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
      //mainApp.showBirthdayStatistics();
    }
    
    public void mouseClick(MouseEvent mouseEvent) throws IOException {
    		TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();
    		System.out.println(item.getValue());
    		if("Public Data".equals(item.getValue())) {
    	        // Load the fxml file and set into the center of the main layout
    	        FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("view/PublicData.fxml"));
    	        AnchorPane editPage = (AnchorPane) loader1.load();
    	        
    	        
    	        PublicDataController controller = loader1.getController();
    	        controller.setPerson(MainApp.loadPerson());
    	        
    	        //rootLayout.setCenter(editPage);
    	        mainPane.getChildren().add(editPage);

    		} else if("Private Data".equals(item.getValue())) {
    	        // Load the fxml file and set into the center of the main layout
    	        FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("view/PrivateData.fxml"));
    	        AnchorPane editPage = (AnchorPane) loader1.load();
    	        
    	        PrivateDataController controller = loader1.getController();
    	        controller.setPerson(MainApp.loadPerson());
    	        
    	        //rootLayout.setCenter(editPage);
    	        mainPane.getChildren().add(editPage);
    	        
    	        
    		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TreeItem<String> root = new TreeItem<>("Root");
		TreeItem<String> privateData = new TreeItem<>("Private Data");
		
		TreeItem<String> state = new TreeItem<>("State");
		TreeItem<String> bankAccount = new TreeItem<>("Bank Accounts");
		privateData.getChildren().addAll(state, bankAccount);
		privateData.setExpanded(true);
		
		//nodeA.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
		
		TreeItem<String> publicData = new TreeItem<>("Public Data");
		
		root.getChildren().addAll(privateData, publicData);
		treeview.setRoot(root);
		treeview.getRoot().setExpanded(true);
	}
}