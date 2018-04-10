package learning.budget;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DateOptions {
	
	public int getYearFromDate(Date date) {
		String year = date.toString().substring(0, 4);
		int yearInt = Integer.parseInt(year);
		return yearInt;
	}

	public int getMonthFromDate(Date date) {
		String month = date.toString().substring(5, 7);
		int monthInt = Integer.parseInt(month);
		return monthInt;
	}
	
	public int getDayFromDate(Date date) {
		String month = date.toString().substring(8, 10);
		int monthInt = Integer.parseInt(month);
		return monthInt;
	}
}
