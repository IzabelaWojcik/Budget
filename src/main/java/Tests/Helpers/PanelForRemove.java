package Tests.Helpers;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelForRemove extends JPanel{
	
	public PanelForRemove() {
		JButton b1 = new JButton("2018");
		JButton b2 = new JButton("2019");
		JButton b3 = new JButton("2020");
		add(b1);
		add(b2);
		add(b3);
	}
}
