package learning.budget.OldViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButtonsWithYears extends JPanel{

	//TODO sorted list:
	//HashMap<Integer, ArrayList<Integer>> mapOfYearsInConcreteBudgetId = sort
	//.sortYearsInConcredeBudgetId(usersIncomeObjectList);
	public void fillPanelWithYearButtons(HashMap<Integer, ArrayList<Integer>> mapOfYearsInConcreteBudgetId, int budgetId) {
		for (Entry<Integer, ArrayList<Integer>> entry : mapOfYearsInConcreteBudgetId.entrySet()) {
			Integer idBudget = entry.getKey();
			int sizeOfListOfYears = entry.getValue().size();
			if (idBudget == budgetId) {
				for (int i = 0; i < sizeOfListOfYears; i++) {
					int year = entry.getValue().get(i);
					JButton jButtonWithYear = new JButton(String.valueOf(year));
					add(jButtonWithYear);
				}
			}
		}
	}
}
