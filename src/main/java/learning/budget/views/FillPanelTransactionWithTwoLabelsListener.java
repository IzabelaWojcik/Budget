package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javafx.util.Pair;

public class FillPanelTransactionWithTwoLabelsListener implements ActionListener {
	private PanelTransactionViewWithTwoLabels panelTransactionView;
	private List<Pair<String, Double>> listOfPairs;
	private JPanel panelToView;

	public FillPanelTransactionWithTwoLabelsListener(PanelTransactionViewWithTwoLabels panelTransactionViewWithTwoLabels,
			List<Pair<String, Double>> incomePairs, JPanel panelToDisplay) {
		panelTransactionView = panelTransactionViewWithTwoLabels;
		listOfPairs = incomePairs;
		panelToView = panelToDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelToView.removeAll();
		panelTransactionView.fillPanel(listOfPairs);
		panelToView.add(panelTransactionView);
		panelToView.repaint();
		panelToView.revalidate();
	}
}
