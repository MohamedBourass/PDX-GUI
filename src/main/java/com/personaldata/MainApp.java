package com.personaldata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.prefs.Preferences;

import com.personaldata.model.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of Persons.
     */
    //private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
    }
    
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    /*public ObservableList<Person> getPersonData() {
        return personData;
    }*/
    
    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("PDX GUI");
      this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

      try {
        // Load the root layout from the fxml file
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
        
        //showPersonOverview();
        //showPersonEdition();


        // Load the fxml file and set into the center of the main layout
        FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("view/PrivateData.fxml"));
        AnchorPane editPage = (AnchorPane) loader1.load();
        
        //rootLayout.setCenter(editPage);
        controller.mainPane.getChildren().add(editPage);

        // Give the controller access to the main app
        PrivateDataController controller1 = loader1.getController();
        controller1.setMainApp(this);
        controller1.setPerson(loadPerson());

      } catch (IOException e) {
          // Exception gets thrown if the fxml file could not be loaded
          e.printStackTrace();
      }

      // Try to load last opened person file
      /*File file = getPersonFilePath();
      if (file != null) {
        loadPersonDataFromFile(file);
      }*/
    }
    
    public static Person loadPerson() {
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
		
		return (Person) xstream.fromXML(dataJson);
		//this.setPerson(customer);
    }
    
    
    
    
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
    /*public void showPersonEdition() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PrivateData.fxml"));
            AnchorPane editPage = (AnchorPane) loader.load();
//            for(Node node : rootLayout.getChildren()) {
//            	System.out.println("Titi => " + node.getId());
//            }
            
            //FXMLLoader loader1 = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
            //RootLayoutController controller1 = loader1.getController();
            //AnchorPane main = controller1.mainPane;
            
            // dmain.setLeftAnchor(editPage, 0.0);
            
            //ObservableList<Node> nodes = rootLayout.getChildren();
            //nodes.remove(1);
            //nodes.set(1, editPage);
            //nodes.add(editPage);
            rootLayout.setCenter(editPage);

            // Give the controller access to the main app
            PrivateDataController controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }*/
    
    /*public boolean showPrivateData(Person person) {
    	  try {
    	    // Load the fxml file and create a new stage for the popup
    	    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PrivateData.fxml"));
    	    AnchorPane page = (AnchorPane) loader.load();
    	    Stage dialogStage = new Stage();
    	    dialogStage.setTitle("Edit Person");
    	    dialogStage.initModality(Modality.WINDOW_MODAL);
    	    dialogStage.initOwner(primaryStage);
    	    Scene scene = new Scene(page);
    	    dialogStage.setScene(scene);

    	    // Set the person into the controller
    	    PrivateDataController controller = loader.getController();
    	    controller.setDialogStage(dialogStage);
    	    controller.setPerson(person);

    	    // Show the dialog and wait until the user closes it
    	    dialogStage.showAndWait();

    	    return controller.isOkClicked();

    	  } catch (IOException e) {
    	    // Exception gets thrown if the fxml file could not be loaded
    	    e.printStackTrace();
    	    return false;
    	  }
    	}*/

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getPersonFilePath() {
	  Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	  String filePath = prefs.get("filePath", null);
	  if (filePath != null) {
	    return new File(filePath);
	  } else {
	    return null;
	  }
	}

	/**
	 * Sets the file path of the currently loaded file.
	 * The path is persisted in the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setPersonFilePath(File file) {
	  Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	  if (file != null) {
	    prefs.put("filePath", file.getPath());

	    // Update the stage title
	    primaryStage.setTitle("AddressApp - " + file.getName());
	  } else {
	    prefs.remove("filePath");

	    // Update the stage title
	    primaryStage.setTitle("AddressApp");
	  }
	}
	
	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	/*
	@SuppressWarnings("unchecked")
	public void loadPersonDataFromFile(File file) {
	  XStream xstream = new XStream();
	  xstream.alias("person", Person.class);

	  try {
	    String xml = FileUtil.readFile(file);

	    ArrayList<Person> personList = (ArrayList<Person>) xstream.fromXML(xml);

	    personData.clear();
	    personData.addAll(personList);

	    setPersonFilePath(file);
	  } catch (Exception e) { // catches ANY exception
//	    Dialogs.showErrorDialog(primaryStage,
//	        "Could not load data from file:\n" + file.getPath(),
//	        "Could not load data", "Error", e);
	    System.out.println(primaryStage + ", Could not load data from file:\n" + file.getPath() + ", Could not load data" + ", Error" + e.getMessage());
	    
	  }
	}

	public void savePersonDataToFile(File file) {
	  XStream xstream = new XStream();
	  xstream.alias("person", Person.class);

	  // Convert ObservableList to a normal ArrayList
	  ArrayList<Person> personList = new ArrayList<>(personData);

	  String xml = xstream.toXML(personList);
	  try {
	    FileUtil.saveFile(xml, file);

	    setPersonFilePath(file);
	  } catch (Exception e) { // catches ANY exception
//	    Dialogs.showErrorDialog(primaryStage,
//	        "Could not save data to file:\n" + file.getPath(),
//	        "Could not save data", "Error", e);
	    System.out.println(primaryStage + ", Could not save data to file:\n" + file.getPath() + ", Could not save data" + ", Error" + e.getMessage());

	  }
	}
	

    public void showBirthdayStatistics() {
      try {
        // Load the fxml file and create a new stage for the popup
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

      } catch (IOException e) {
        // Exception gets thrown if the fxml file could not be loaded
        e.printStackTrace();
      }
    }*/
}
