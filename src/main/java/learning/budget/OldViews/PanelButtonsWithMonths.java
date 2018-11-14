package learning.budget.OldViews;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import learning.budget.DataFormatter;

public class PanelButtonsWithMonths extends JPanel{
//TODO list: check how get month name in localdare
//listOfMonths = sort.sortMonthsForConcreteYearAndBudgetId(usersIncomeObjectList, budgetId,year);
	public void fillPanelWithMonthsButtons(ArrayList<Integer> listOfMonths) {
		DataFormatter dataFormatter = new DataFormatter();
		for (int i = 0; i < listOfMonths.size(); i++) {
			int month = listOfMonths.get(i);
			String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
			JButton jButtonWithMonthName = new JButton(monthName + "");
			add(jButtonWithMonthName);
		}
	}
}
