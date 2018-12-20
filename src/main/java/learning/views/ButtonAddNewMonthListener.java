package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import learning.budget.IDatabaseWriter;

public class ButtonAddNewMonthListener implements ActionListener{
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private IDatabaseWriter databaseWriter;
	private int budgetId;
	
	public ButtonAddNewMonthListener(int budgetId, JYearChooser yearChooser, JMonthChooser monthChooser, IDatabaseWriter databaseWriter) {
		this.yearChooser = yearChooser;
		this.monthChooser = monthChooser;
		this.databaseWriter = databaseWriter;
		this.budgetId = budgetId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		databaseWriter.writeBudgetIdYearMonthToDatabase(budgetId, yearChooser.getYear(), monthChooser.getMonth() + 1);
	}

}
