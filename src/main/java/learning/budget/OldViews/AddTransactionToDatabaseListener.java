package learning.budget.OldViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import org.javatuples.Quintet;
import com.toedter.calendar.JDateChooser;
import learning.budget.DataFormatter;
import learning.budget.IDatabaseWriter;

public class AddTransactionToDatabaseListener implements ActionListener{
	private IDatabaseWriter _databaseWriter;
	private Quintet<JFormattedTextField, JDateChooser, JComboBox<String>, Integer, String> quintet;
	private HashMap<Integer, String> categoryMap;
	
	public AddTransactionToDatabaseListener(IDatabaseWriter databaseWriter, HashMap<Integer, String> categoryHashMap, Quintet<JFormattedTextField, JDateChooser, JComboBox<String>, Integer, String> quintetElementsToDatabaseWriter) {
		_databaseWriter = databaseWriter;
		quintet = quintetElementsToDatabaseWriter;
		categoryMap = categoryHashMap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalDate localDate;
		int categoryId = 0;
		
		for(Entry<Integer, String> entry: categoryMap.entrySet()) {
			if(entry.getValue().equals(quintet.getValue2().getSelectedItem())) {
				categoryId = entry.getKey();
			}
		}
		
		try {
			localDate = DataFormatter.dateLocalFormatterForJDateChooser(quintet.getValue1());
			if (localDate == null) {
				throw new NullPointerException("local date not init");
			}
				
			if(_databaseWriter == null){
				throw new NullPointerException("database not init");
			}
			
			_databaseWriter.writeExpenditureOrSavingsToDatabase(Double.parseDouble(quintet.getValue0().getText().replace(',', '.')), localDate, categoryId, quintet.getValue3(), quintet.getValue4());

		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Wybierz datę");
		}catch (java.lang.NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "poprawny format #.##");
		}
		
		quintet.getValue0().setText("");
	}
}