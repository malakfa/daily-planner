import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

// this class represnts all the tasks/meetings 
public class Meetings {
	private HashMap<Date, String> h;
	
	// Meetings constructor
	public Meetings() {
		h = new HashMap<Date,String>();
	}
	
	// show the tasks in date d/m/y
	public void showTasks(int d , int m , int y) {
		Date date = new Date(d,m,y);
		String st ;
		if( h.get(date) == null) {
			st = "meetings list is empty";
		}
		else {
			st = "meetings list : " + h.get(date);
		}
		JOptionPane.showMessageDialog(null , st); 
	 
    }
	// get tasks in date d
	public String getTasks(Date d) {
		return h.get(d);
	}
	// edit tasks in date d 
	public void editTasks(Date d , String st) {
		h.put(d, st);
	}
	
}