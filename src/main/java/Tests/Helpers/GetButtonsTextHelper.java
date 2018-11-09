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
	
	public static List<JButton> getButtons(JPanel panel){
		List<JButton> buttons = new ArrayList<>();
		Component[] components = panel.getComponents();

		for (Component component : components) {
		    if (component.getClass().equals(JButton.class)) {
		        buttons.add(((JButton) component));
		    }
		}
		return buttons;
	}

	public static List<JButton> getButtons2(JPanel panel){
		Component[] components = panel.getComponents();
		
		Stream<Component> componentsStream = Arrays.stream(components);
		List<JButton> buttons = componentsStream
				.filter(c -> c.getClass().equals(JButton.class))
				.map(c -> (JButton) c)
				.collect(Collectors.toList());
		
		return buttons;
	}
}
