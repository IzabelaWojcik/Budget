package classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class DataFormatter {
	///////////////////
	// private long time = System.currentTimeMillis(); ///}data - }
	// private java.sql.Date sqlDate = new java.sql.Date(time); ///}dzisiejsza,
	/////////////////// da siê zapisac do bazy }razem
	////////////////

	// w metodzie:
	// dateChooser = new JDateChooser();
	// dateChooser.setDateFormatString("dd-MM-yyyy");
	// dateChooser.setDate(sqlDate); dzisiejsza data ustawiona, ale przy
	// zmienianiu roku lub miesi¹ca bez zmiany dnia nie widaæ ¿e data siê nie
	// zmieni³a

	public String setAmountFormat(double amount){
		DecimalFormat df = new DecimalFormat("#.##");//z przecinkiem dla Polski, a ja mam wszêdzie kropki
		String formattedAmount = df.format(amount);
		String formattedAmountWithDotInsteadComa = formattedAmount.replace(",", ".");
		// WA¯NE !!!:
		// String num = String.format("%.2f",amount); daje to samo co decimal format, czyli z przecinkiem dla Polski
		//System.out.println(NumberFormat.getCurrencyInstance().format(amount));  w z³
		//System.out.println(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amount)); w $
		//
		return  formattedAmountWithDotInsteadComa;
}
	
	public ArrayList<String> changeMonhNumberFromArrayListForString(ArrayList<Integer> list) {
		String month = "";
		ArrayList<String> listOfMonths = new ArrayList<>();
		for (int monthNumber : list) {
			if (monthNumber == 1)
				month = "Styczeñ";
			if (monthNumber == 2)
				month = "Luty";
			if (monthNumber == 3)
				month = "Marzec";
			if (monthNumber == 4)
				month = "Kwiecieñ";
			if (monthNumber == 5)
				month = "Maj";
			if (monthNumber == 6)
				month = "Czerwiec";
			if (monthNumber == 7)
				month = "Lipiec";
			if (monthNumber == 8)
				month = "Sierpieñ";
			if (monthNumber == 9)
				month = "Wrzesieñ";
			if (monthNumber == 10)
				month = "Paziernik";
			if (monthNumber == 11)
				month = "Listopad";
			if (monthNumber == 12)
				month = "Grudzieñ";

			if (!list.contains(month))
				listOfMonths.add(month);
		}
		return listOfMonths;
	}

	public String changeMonhNumberFromMonthName(int monthNumber) {
		String month = "";
		if (monthNumber == 1)
			month = "Styczeñ";
		if (monthNumber == 2)
			month = "Luty";
		if (monthNumber == 3)
			month = "Marzec";
		if (monthNumber == 4)
			month = "Kwiecieñ";
		if (monthNumber == 5)
			month = "Maj";
		if (monthNumber == 6)
			month = "Czerwiec";
		if (monthNumber == 7)
			month = "Lipiec";
		if (monthNumber == 8)
			month = "Sierpieñ";
		if (monthNumber == 9)
			month = "Wrzesieñ";
		if (monthNumber == 10)
			month = "Paziernik";
		if (monthNumber == 11)
			month = "Listopad";
		if (monthNumber == 12)
			month = "Grudzieñ";
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
