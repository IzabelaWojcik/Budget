package Tests.Helpers;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import learning.views.PanelAddTransaction;



public class TestHelper {
	
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
	
	public static List<JFormattedTextField> getFormattedTextFields(JPanel panel) {
		Component[] components = panel.getComponents();
		
		Stream<Component> componentsStream = Arrays.stream(components);
		List<JFormattedTextField> formattedTextFields = componentsStream
				.filter(c -> c.getClass().equals(JFormattedTextField.class))
				.map(c -> (JFormattedTextField) c)
				.collect(Collectors.toList());
		
		return formattedTextFields;
	}
	
	public static List<JComboBox> getComboBoxes(JPanel panel) {
		Component[] components = panel.getComponents();
		
		Stream<Component> componentsStream = Arrays.stream(components);
		List<JComboBox> comboBoxes = componentsStream
				.filter(c -> c.getClass().equals(JComboBox.class))
				.map(c -> (JComboBox) c)
				.collect(Collectors.toList());
		
		return comboBoxes;
	}
	
	public static List<JDateChooser> getDateChoosers(JPanel panel) {
		Component[] components = panel.getComponents();
		
		Stream<Component> componentsStream = Arrays.stream(components);
		List<JDateChooser> dateChoosers = componentsStream
				.filter(c -> c.getClass().equals(JDateChooser.class))
				.map(c -> (JDateChooser) c)
				.collect(Collectors.toList());
		
		return dateChoosers;
	}
}
