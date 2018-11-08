package Tests.Helpers;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public Set<String> getButtonsText2(JPanel panel){
		Component[] components = panel.getComponents();
		
		Stream<Component> componentsStream = Arrays.stream(components);
		Set<String> buttons = componentsStream
				.filter(c -> c.getClass().equals(JButton.class))
				.map(c -> ((JButton) c).getText())
				.collect(Collectors.toSet());
		
		return buttons;
	}
}
