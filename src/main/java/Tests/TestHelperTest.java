package Tests;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import org.junit.Test;

import com.toedter.calendar.JDateChooser;

import Tests.Helpers.TestHelper;
import Tests.Helpers.PanelForRemove;
import learning.budget.DatabaseNotInitialized;

public class TestHelperTest {
	
	private class Panel extends JPanel {
		public JDateChooser dateChooser; 
		public JComboBox comboBox;
		public JButton buttonAdd;
		public JFormattedTextField formattedTextField;
		
		public Panel() throws ParseException {
			dateChooser = new JDateChooser();
			comboBox = new JComboBox<String>();
			buttonAdd = new JButton("Dodaj");
			formattedTextField = new JFormattedTextField(DecimalFormat.getInstance());
			List<String> categories = new ArrayList<String>() {{add("category1"); add("category2"); add("category3");}};
			
			String dateString = "15-10-2018";
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			dateChooser.setDate(date);
			
			for(String category: categories) {
				comboBox.addItem(category);
			}
			
			comboBox.setSelectedIndex(0);
			
			formattedTextField.setText("123.80");
			
			add(dateChooser);
			add(comboBox);
			add(formattedTextField);
			add(buttonAdd);													
		}
	}
	
	@Test 
	public void getButtonsText_getButtonsFromPanel_returnButtonsTextList() throws DatabaseNotInitialized {
		PanelForRemove panelForRemove = new PanelForRemove();
		
		JButton b1 = new JButton("2018");
		JButton b2 = new JButton("2019");
		JButton b3 = new JButton("2020");
		List<JButton> expected = new ArrayList<>();
		expected.add(b1);
		expected.add(b2);
		expected.add(b3);
		
		List<String> expectedButtonsNames = expected.stream().map(b -> b.getName()).collect(Collectors.toList());
		
		List<JButton> actualForGetButtons = TestHelper.getButtons(panelForRemove);
		List<JButton> actualForGetButtons2 = TestHelper.getButtons2(panelForRemove);
		
		assertEquals(expectedButtonsNames, actualForGetButtons.stream().map(b -> b.getName()).collect(Collectors.toList()));
		assertEquals(expectedButtonsNames, actualForGetButtons2.stream().map(b -> b.getName()).collect(Collectors.toList()));
	}
	
	@Test
	public void getFormattedTextField_getFormattedTexdFieldFromPanel_returnFormattedTextField() throws ParseException {
		Panel panel = new Panel();
		
		assertEquals(TestHelper.getFormattedTextFields(panel).get(0), panel.formattedTextField);
		assertEquals(TestHelper.getFormattedTextFields(panel).size(), 1);
	}
	
	@Test
	public void getComboBoxes_getComboBoxPanel_returnComboBox() throws ParseException {
		Panel panel = new Panel();
		
		assertEquals(TestHelper.getComboBoxes(panel).get(0), panel.comboBox);
		assertEquals(TestHelper.getComboBoxes(panel).size(), 1);
	}
	
	@Test
	public void getDateChoosers_getDateChooserFromPanel_returnDateChooser() throws ParseException {
		Panel panel = new Panel();
		
		assertEquals(TestHelper.getDateChoosers(panel).get(0), panel.dateChooser);
		assertEquals(TestHelper.getDateChoosers(panel).size(), 1);
	}
}
