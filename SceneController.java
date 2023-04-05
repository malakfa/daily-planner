import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

// controller to the second  screen 
public class SceneController {

	
	private String dateEvents;
	private CalendarController parentController;
	
	
	@FXML
   private TextArea tasks;
	
	// edit the meetings in the current date and switch screen 
	public void savePressed(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MyCalendar.fxml"));
		
		Parent root = loader.load();
		CalendarController returnToController = loader.getController();
		
		parentController.getMeetings().editTasks(parentController.getCurrentDate(), tasks.getText());
		returnToController.copyController(parentController);

		
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// switch screen without edit the meetings
	public void cancelPressed(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MyCalendar.fxml"));
		Parent root = loader.load();
		CalendarController returnToController = loader.getController();
		returnToController.copyController(parentController);
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	// set tasks
	public void setTasks(String st) {
		tasks.setText(st);
	}
	// set parent controller
	public void setParentController(CalendarController c){
		parentController = c;
	}
	// set date
	public void setDateEvent(String date) {
		dateEvents = date;
	}
	
	
}