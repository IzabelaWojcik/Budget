package learning.budget.views;

import static learning.budget.Constants.MAX_NUMBER_OF_USERS_IN_BUDGET;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorLabels extends JPanel {
	
	void fill(int numberOfLabels) {
		for (int i = 0; i < numberOfLabels && i < MAX_NUMBER_OF_USERS_IN_BUDGET; i++) {
			JLabel label = new JLabel();
			label.setForeground(Color.RED);
			label.setPreferredSize(new Dimension(130, 20));
			label.setHorizontalAlignment(JLabel.LEFT);
			
			add(label);
		}
		
		validate();
		repaint();
	}
}
