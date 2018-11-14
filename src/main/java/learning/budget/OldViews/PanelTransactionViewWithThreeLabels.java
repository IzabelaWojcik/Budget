package learning.budget.OldViews;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTransactionViewWithThreeLabels extends JPanel{
	
	public PanelTransactionViewWithThreeLabels(String data1, String data2, String data3) {
			setPreferredSize(new Dimension(210, 20));
			
			JLabel lblData1 = new JLabel(data1, JLabel.LEFT);
			lblData1.setPreferredSize(new Dimension(80, 20));
			
			JLabel lblData2 = new JLabel(data2, JLabel.LEFT);
			lblData2.setPreferredSize(new Dimension(80,  20));
			
			JLabel lblData3 = new JLabel(data3, JLabel.LEFT);
			lblData3.setPreferredSize(new Dimension(50, 20));
			
			add(lblData1);
			add(lblData2);
			add(lblData3);
	}
}

	
	