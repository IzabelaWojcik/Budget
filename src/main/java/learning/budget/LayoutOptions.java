package learning.budget;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayoutOptions {
	private int gridy = 0;
	public void gridBagLayoutSettings(JPanel panel, JLabel label, int gridx, int gridy){
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = gridx;
		c.gridy = gridy;
		panel.add(label, c);
	}
	
	public void gridBagLayoutOptionsForPanelsWithThreeLabels(JPanel panel, JLabel label1, JLabel label2, JLabel label3){
		gridBagLayoutSettings(panel, label1, 0, gridy);
		gridBagLayoutSettings(panel, label2, 1, gridy);
		gridBagLayoutSettings(panel, label3, 2, gridy);
		gridy++;
	}
	
	public int getGridy(){
		return gridy;
	}
	
	public void setGridy(int gridY){
		gridy = gridY;
	}
}
