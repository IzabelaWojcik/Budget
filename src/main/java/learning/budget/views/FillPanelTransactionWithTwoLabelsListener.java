package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javafx.util.Pair;

public class FillPanelTransactionWithTwoLabelsListener implements ActionListener {
	private PanelTransactionViewWithTwoLabels panelTransactionViewWithTwoLabels;
	private List<Pair<String, Double>> listOfPairs;
	private JPanel panelToView;

	public FillPanelTransactionWithTwoLabelsListener(PanelTransactionViewWithTwoLabels panelTransactionView,
			List<Pair<String, Double>> incomePairs, JPanel panelToDisplay) {
		panelTransactionViewWithTwoLabels = panelTransactionView;
		listOfPairs = incomePairs;
		panelToView = panelToDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelToView.removeAll();
		panelTransactionViewWithTwoLabels.fillPanel(listOfPairs);
		panelToView.add(panelTransactionViewWithTwoLabels);
		panelToView.repaint();
		panelToView.revalidate();
	}
}
