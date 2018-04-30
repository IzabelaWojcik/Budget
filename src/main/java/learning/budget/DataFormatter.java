package learning.budget;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class DataFormatter {

	public String setAmountFormat(double amount) {
		DecimalFormat df = new DecimalFormat("#.##");
		String formattedAmount = df.format(amount);
		String formattedAmountWithDotInsteadComa = formattedAmount.replace(",", ".");

		return formattedAmountWithDotInsteadComa;
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

	public java.sql.Date dateSqlFormatterForJDateChooser(JDateChooser dateChooser) throws ParseException {
		dateChooser.setDateFormatString("dd-MM-yyyy");
		String dateFromDateChooser = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date parsed = dateFormat.parse(dateFromDateChooser);
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		return sqlDate;
	}

	//TODO check if I need so many date formating
	public LocalDate dateLocalFormatterForJDateChooser(JDateChooser dateChooser) throws ParseException {
		dateChooser.setDateFormatString("dd-MM-yyyy");
		String dateFromDateChooser = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date parsed = dateFormat.parse(dateFromDateChooser);
		LocalDate localDate = Instant.ofEpochMilli(parsed.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		String text = localDate.format(formatters);
		LocalDate parsedDate = LocalDate.parse(text, formatters);

		return parsedDate;
	}
}
