package Tests.Helpers;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JPanel;



public class GetButtonsTextHelper {
	
	public Set<String> getButtonsText(JPanel panel){
		Set<String> buttons = new TreeSet<String>();
		Component[] components = panel.getComponents();

		for (Component component : components) {
		    if (component.getClass().equals(JButton.class)) {
		        buttons.add(((JButton) component).getText());
		    }
		}
		return buttons;
	}

}
