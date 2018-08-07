package learning.budget.views;

import static learning.budget.DataFormatter.setAmountFormat;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.util.Pair;
import learning.budget.LayoutOptions;

public class FillPanelTransactionWithTwoLabelsListener implements ActionListener{
	private PanelTransactionViewWithTwoLabels panelTransactionViewWithTwoLabels;
	private List<Pair<String, Double>> listOfPairs;
	private JPanel panelToDisplay;
	
	
	public FillPanelTransactionWithTwoLabelsListener(PanelTransactionViewWithTwoLabels panelTransactionView, List<Pair<String, Double>> pairs, JPanel panelToView) {
		panelTransactionViewWithTwoLabels = panelTransactionView;
		listOfPairs = pairs;
		panelToDisplay = panelToView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelTransactionViewWithTwoLabels.removeAll();
		panelToDisplay.removeAll();
		panelTransactionViewWithTwoLabels.fillPanel(listOfPairs);
		//	JLabel jLabelUsers = new JLabel("WHY");

			
panelToDisplay.add(panelTransactionViewWithTwoLabels);
		//	panelView.add(jLabelUsers);
		panelToDisplay.repaint();
		panelToDisplay.revalidate();
	}
}
