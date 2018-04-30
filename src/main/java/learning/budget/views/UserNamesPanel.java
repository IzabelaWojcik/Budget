package learning.budget.views;
import static learning.budget.Constants.MAX_NUMBER_OF_USERS_IN_BUDGET;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserNamesPanel extends JPanel {
	public UserNamesPanel(Dimension dimension, FlowLayout flowLayout) {
		super(flowLayout);
		setMaximumSize(dimension);
	}

	public void fillWithUserNames(List<String> names) {
		for (String name : names.subList(0, MAX_NUMBER_OF_USERS_IN_BUDGET)) {
			JLabel label = new JLabel(name, JLabel.RIGHT);
			label.setPreferredSize(new Dimension(90, 20));
			
			add(label);
		}
		
		validate();
		repaint();
	}
}
