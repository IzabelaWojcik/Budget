package Tests.Helpers;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

import javax.swing.JButton;


import learning.budget.views.PanelWithButtons;

public class PanelWithButtonsTestable extends PanelWithButtons {

	public PanelWithButtonsTestable(String identifier, SortedSet<String> buttonsNames) {
		super(identifier, buttonsNames);
	}

	public Map<String, JButton> getButton(){
		Map<String, JButton> buttonNameToButton = new HashMap<>();
		for(Component component : getComponents()) {
			if(component.getClass().equals(JButton.class)) {
				 JButton button = (JButton)component;
				 buttonNameToButton.put(button.getText(), button);
			}
		}
		
		return buttonNameToButton;
	}
}
