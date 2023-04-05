import java.util.Objects;

// this class represents a date 
public class Date {
	private int day;
	private int month ;
	private int year;
	
	// Date constructor
	public Date(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}
	// get day 
	public int getDay() {
		return day;
	}
	// get month
	public int getMonth() {
		return month;
	}
	// get year 
	public int getYear() {
		return year;
	}
	// set day
	public void setDay(int day) {
		this.day = day;
	}
	// set month
	public void setMonth(int month) {
		this.month = month;
	}
	// set year
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}
	
	//this function returns true if the two objects are equal 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		return day == other.day && month == other.month && year == other.year;
	}
	
	// print the date
	public String toString() {
		return day + " ," + month + " ," + year ;
	}
	
	
}
