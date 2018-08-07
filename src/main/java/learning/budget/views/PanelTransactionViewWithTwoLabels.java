package learning.budget.views;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.util.Pair;

public class PanelTransactionViewWithTwoLabels extends JPanel{
	JLabel lblName, lblSalary;
	JPanel panel;
	
	public void fillPanel(List<Pair<String, Double>> pairs) {
		removeAll();
		for(Pair<String, Double> p: pairs) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(130, 20));
			lblName = new JLabel(p.getKey(), JLabel.LEFT);
			lblName.setPreferredSize(new Dimension(60, 20));
			lblSalary = new JLabel(p.getValue().toString(), JLabel.LEFT);
			lblSalary.setPreferredSize(new Dimension(50, 20));
			
			panel.add(lblName);
			panel.add(lblSalary);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			add(panel);
		}
	}
}


