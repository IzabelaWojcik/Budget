package learning.budget;

import java.util.Date;

public class DateOptions {
	
	public int getYearFromDate(java.sql.Date sqlDate) {
		String year = sqlDate.toString().substring(0, 4);
		int yearInt = Integer.parseInt(year);
		return yearInt;
	}

	public int getMonthFromDate(java.sql.Date sqlDate) {
		String month = sqlDate.toString().substring(5, 7);
		int monthInt = Integer.parseInt(month);
		return monthInt;
	}
	
	public int getDayFromDate(Date date) {
		String month = date.toString().substring(8, 10);
		int monthInt = Integer.parseInt(month);
		return monthInt;
	}
}
