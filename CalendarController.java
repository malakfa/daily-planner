import java.io.IOException;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

 // controller class
public class CalendarController {

	final int ROWS = 6;
	final int COLUMNS = 7;
	
	private Button[][] btns;

    @FXML
    private Text monthYear;

    @FXML
    private GridPane grid;
    
    @FXML
    private ComboBox<Integer> monthC;

    @FXML
    private ComboBox<Integer> yearC;
    
    private Meetings meetings;
    
    private Date date;
    
    // get meetings
    public Meetings getMeetings() {
    	 return meetings;
    }
   
    // get current date
    public Date getCurrentDate() {
    	return date;
    }
    
    // after edit pressed -> switch screeen 
    @FXML
    public void editPressed(ActionEvent event ) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTasks.fxml")); 
    	Parent root = loader.load();
    	
    	SceneController c = loader.getController();
    	c.setParentController(this);
    	c.setDateEvent(meetings.getTasks(date));
    	c.setTasks(meetings.getTasks(date));
    	
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    // copy controller c
    public void copyController(CalendarController c) {
    	meetings = c.meetings;
    	meetings.editTasks(c.date,c.meetings.getTasks(c.date));
    	date = c.date;
    	grid = c.grid;
    	monthYear.setText(c.monthYear.getText()); 

    	Calendar cal = getDate(c.date.getMonth(),c.date.getYear());
    	initBtons(cal);
    	
    }
    
    
    
    public void handleButton(ActionEvent event) {
    	date.setDay(Integer.parseInt(((Button)event.getSource()).getText()));
    	meetings.showTasks(date.getDay(),date.getMonth(),date.getYear());
    }
    
 // After selecting the month and year set the title and initialize the buttons according to the selected year and month
    @FXML
    void okPressed(ActionEvent event) {
    	cleanButons();
    	int m = monthC.getValue();
    	int y = yearC.getValue();
    	monthYear.setText(monthName(m) + " - " + y);
    	date = new Date(0,m,y);
    	Calendar c = getDate(m,y);
    	initBtons(c);
    }
    
   // Graphics initialization 
    @FXML
    public void initialize(){
    	meetings = new Meetings();	
    	btns = new Button[ROWS][COLUMNS];
    	double w = grid.getPrefWidth() / ROWS ;
    	double h = grid.getPrefHeight()/ COLUMNS;
    	for(int i = 0; i <  ROWS ;i++){
    		for(int j = 0 ; j < COLUMNS ;j++) {
    			btns[i][j] = new Button();
        		btns[i][j].setPrefSize(w,h);
        		grid.add(btns[i][j], j,i );	
        		
        		btns[i][j].setOnAction(new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent event) {
        				handleButton(event);
        			}
        		});
    		}
    		initMonthComboBox();
    		initYearComboBox();
    	}
    }
    
 // initializing the options in the MonthComboBox
    private void initMonthComboBox() {
    	for(int i = 1;  i <= 12 ; i++) {
    		monthC.getItems().add(i);
    	}
    }
    
 // initializing the options in the YearComboBox
    private void initYearComboBox() {
    	for(int i = 2000;  i <= 2022 ; i++) {
    		yearC.getItems().add(i);
    	}
    }  
    
    // get date according to month and year
    public Calendar getDate(int m , int y) {
    	Calendar c = Calendar.getInstance();
    	c.set(y, m-1, 1);
    	return c;
    	
    }
    
    // initialize the buttons depending on the date c
    private void initBtons(Calendar c) {
    	int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    	int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    	int j ;
    	int countDays = 1;
    	boolean first = true;
    	for(int i = 0 ;i<ROWS && countDays<=days ; i++){
    		if(first == true) {
    			j = dayOfWeek-1;
    			first = false;
    		}
    		else {
    			j=0;
    		}
    		for(; j<COLUMNS && countDays<=days ;j++) {
    			btns[i][j].setText("" + countDays);
    			countDays++;
    			
    		}
    			
    		
    	}
    }
    
    // clean the buttons
    private void cleanButons() {
    	for(int i = 0; i <  ROWS ;i++){
    		for(int j = 0 ; j < COLUMNS ;j++) {
    			btns[i][j].setText(null);

    		}
    	}
    }
    
    // return the name of the month m
    private String monthName(int m) {
    	String[] str = {"January",      
    			   "February",
    			   "March",        
    			   "April",        
    			   "May",          
    			   "June",         
    			   "July",         
    			   "August",       
    			   "September",    
    			   "October",      
    			   "November",     
    			   "December"};
    	return str[m-1];
    }
   
        
   
    

}