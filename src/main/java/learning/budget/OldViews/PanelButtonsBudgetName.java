package learning.budget.OldViews;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButtonsBudgetName extends JPanel{
	//FIXME its only create buttons, they dont do anything, action listener needed
	// budgetCurrentId isnt use here
	public void fillPanelWithBudgetsButtons(HashMap<Integer, String> budgetIdNameMap) {
		int budgetCurrentId;
		for (Entry<Integer, String> entry : budgetIdNameMap.entrySet()) {
			budgetCurrentId = entry.getKey();
			JButton jButtonBudgetName = new JButton(entry.getValue());
			add(jButtonBudgetName);
		}
		
		revalidate();
		repaint();
	}
}
