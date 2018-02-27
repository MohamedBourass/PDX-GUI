package com.personaldata;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.personaldata.model.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

/**
 * Dialog to edit details of a person.
 * 
 */
public class PublicDataController {
	
	
    @FXML
    private TextField genderField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField birthLocationField;
    @FXML
    private TextField nativeLanguageField;
    @FXML
    private TextField religionField;
    @FXML
    private TextField iqField;
    @FXML
    private TextField heightField;
    
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }
    
    private MainApp mainApp;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
        
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
		
		Person customer = (Person) xstream.fromXML(dataJson);
		this.setPerson(customer);
        
    }

    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    /*public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }*/

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
        

        genderField.setText(person.getPublicData().getGender());
        birthDateField.setText(person.getPublicData().getBirthDate());
        birthLocationField.setText(person.getPublicData().getBirthLocation());
        nativeLanguageField.setText(person.getPublicData().getNativeLanguage());
        religionField.setText(person.getPublicData().getReligion());
        iqField.setText(person.getPublicData().getIQ());
        heightField.setText(person.getPublicData().getHeight());
        
        
        

//        birthdayField.setText(CalendarUtil.format(person.getBirthday()));
//        birthdayField.setPromptText("yyyy-mm-dd");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	
        	
            genderField.setText(person.getPublicData().getGender());
            birthDateField.setText(person.getPublicData().getBirthDate());
            birthLocationField.setText(person.getPublicData().getBirthLocation());
            nativeLanguageField.setText(person.getPublicData().getNativeLanguage());
            religionField.setText(person.getPublicData().getReligion());
            iqField.setText(person.getPublicData().getIQ());
            heightField.setText(person.getPublicData().getHeight());
        	
        	
            person.getPublicData().setGender(genderField.getText());
            person.getPublicData().setBirthDate(birthDateField.getText());
            person.getPublicData().setBirthLocation(birthLocationField.getText());
            person.getPublicData().setNativeLanguage(nativeLanguageField.getText());
            person.getPublicData().setReligion(religionField.getText());
            person.getPublicData().setIQ(iqField.getText());
            person.getPublicData().setHeight(heightField.getText());

            okClicked = true;
            
            XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
            String dataJson = xstream.toXML(person);
            //System.out.println(dataJson);

            Writer writer = null;

            try {
	            	writer = new BufferedWriter(new OutputStreamWriter(
	            			new FileOutputStream("src/main/resources/com/personaldata/mbourass.json"), "utf-8"));
	            	writer.write(dataJson);
            } catch (IOException ex) {
	            	// report
	            } finally {
	            	try {writer.close();} catch (Exception ex) {/*ignore*/
	            		
	            	}
            }
            
            
            //dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

//        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
//            errorMessage += "No valid first name!\n"; 
//        }
//        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
//            errorMessage += "No valid last name!\n"; 
//        }
        
//        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
//            errorMessage += "No valid postal code!\n"; 
//        } else {
//            // try to parse the postal code into an int
//            try {
//                Integer.parseInt(postalCodeField.getText());
//            } catch (NumberFormatException e) {
//                errorMessage += "No valid postal code (must be an integer)!\n"; 
//            }
//        }
//
//        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
//            errorMessage += "No valid birthday!\n";
//        } else {
//            if (!CalendarUtil.validString(birthdayField.getText())) {
//                errorMessage += "No valid birthday. Use the format yyyy-mm-dd!\n";
//            }
//        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            //Dialogs.showErrorDialog(dialogStage, errorMessage,
            //        "Please correct invalid fields", "Invalid Fields");
            
            System.out.println("DialogStage : " + dialogStage + ", ErrorMessage : " + errorMessage + ", Please correct invalid fields : " + "Invalid Fields");
            return false;
        }
    }
}