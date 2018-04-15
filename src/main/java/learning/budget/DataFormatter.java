package learning.budget;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class DataFormatter {

	public String setAmountFormat(double amount){
		DecimalFormat df = new DecimalFormat("#.##");
		String formattedAmount = df.format(amount);
		String formattedAmountWithDotInsteadComa = formattedAmount.replace(",", ".");

		return  formattedAmountWithDotInsteadComa;
}
	
	public ArrayList<String> changeMonhNumberFromArrayListForString(ArrayList<Integer> list) {
		String month = "";
		ArrayList<String> listOfMonths = new ArrayList<String>();
		for (int monthNumber : list) {
			if (monthNumber == 1)
				month = "Styczeń";
			if (monthNumber == 2)
				month = "Luty";
			if (monthNumber == 3)
				month = "Marzec";
			if (monthNumber == 4)
				month = "Kwiecień";
			if (monthNumber == 5)
				month = "Maj";
			if (monthNumber == 6)
				month = "Czerwiec";
			if (monthNumber == 7)
				month = "Lipiec";
			if (monthNumber == 8)
				month = "Sierpień";
			if (monthNumber == 9)
				month = "Wrzesień";
			if (monthNumber == 10)
				month = "Paziernik";
			if (monthNumber == 11)
				month = "Listopad";
			if (monthNumber == 12)
				month = "Grudzień";

			if (!list.contains(month))
				listOfMonths.add(month);
		}
		return listOfMonths;
	}

	public String changeMonhNumberFromMonthName(int monthNumber) {
		String month = "";
		if (monthNumber == 1)
			month = "Styczeń";
		if (monthNumber == 2)
			month = "Luty";
		if (monthNumber == 3)
			month = "Marzec";
		if (monthNumber == 4)
			month = "Kwiecień";
		if (monthNumber == 5)
			month = "Maj";
		if (monthNumber == 6)
			month = "Czerwiec";
		if (monthNumber == 7)
			month = "Lipiec";
		if (monthNumber == 8)
			month = "Sierpień";
		if (monthNumber == 9)
			month = "Wrzesień";
		if (monthNumber == 10)
			month = "Paziernik";
		if (monthNumber == 11)
			month = "Listopad";
		if (monthNumber == 12)
			month = "Grudzień";
		return month;
	}

	public java.sql.Date dateFormatterForJDateChooser(JDateChooser dateChooser) throws ParseException {
		dateChooser.setDateFormatString("dd-MM-yyyy");
		String dateFromDateChooser = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date parsed = dateFormat.parse(dateFromDateChooser);
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		return sqlDate;
	}
}
