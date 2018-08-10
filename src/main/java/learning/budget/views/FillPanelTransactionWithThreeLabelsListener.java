package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.javatuples.Triplet;

public class FillPanelTransactionWithThreeLabelsListener implements ActionListener {
	private List<Triplet<String, String, String>> list;
	private JPanel panelView;

	public FillPanelTransactionWithThreeLabelsListener(List<Triplet<String, String, String>> listOfTriplet, JPanel panelToDisplay) {
		list = listOfTriplet;
		panelView = panelToDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelView.removeAll();
		panelView.setLayout(new BoxLayout(panelView, BoxLayout.Y_AXIS));
		
		for(Triplet<String, String, String> t: list) {
			PanelTransactionViewWithThreeLabels panelTransaction = new PanelTransactionViewWithThreeLabels(t.getValue0().toString(), t.getValue1().toString(), t.getValue2().toString());
			panelView.add(panelTransaction);
		}
		
		panelView.repaint();
		panelView.revalidate();
	}
}
