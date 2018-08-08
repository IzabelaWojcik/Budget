package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import javafx.util.Pair;

public class FillPanelTransactionWithTwoLabelsListener implements ActionListener {
	private List<Pair<String, Double>> listOfPairs;
	private JPanel panelView;

	public FillPanelTransactionWithTwoLabelsListener(List<Pair<String, Double>> incomePairs, JPanel panelToDisplay) {
		listOfPairs = incomePairs;
		panelView = panelToDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelView.removeAll();
		panelView.setLayout(new BoxLayout(panelView, BoxLayout.Y_AXIS));
		for(Pair<String, Double> p: listOfPairs) {
			PanelTransactionViewWithTwoLabels panelTransactionView = new PanelTransactionViewWithTwoLabels(p.getKey(), p.getValue());
			panelView.add(panelTransactionView);
		}
		
		panelView.repaint();
		panelView.revalidate();
	}
}
