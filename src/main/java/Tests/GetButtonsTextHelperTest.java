package Tests;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;

import org.junit.Test;

import Tests.Helpers.GetButtonsTextHelper;
import Tests.Helpers.PanelForRemove;
import learning.budget.DatabaseNotInitialized;

public class GetButtonsTextHelperTest {
	@Test 
	public void getButtonsText_getButtonsFromPanel_returnButtonsTextList() throws DatabaseNotInitialized {
		GetButtonsTextHelper getButtonsHelper = new GetButtonsTextHelper();
		PanelForRemove panelForRemove = new PanelForRemove();
		
		JButton b1 = new JButton("2018");
		JButton b2 = new JButton("2019");
		JButton b3 = new JButton("2020");
		Set<String> setExpected = new TreeSet<String>();
		setExpected.add(b1.getText());
		setExpected.add(b2.getText());
		setExpected.add(b3.getText());
		
		Set<String> setActualForgetButtonsText = getButtonsHelper.getButtonsText(panelForRemove);
		Set<String> setActualForgetButtonsText2 = getButtonsHelper.getButtonsText2(panelForRemove);
		
		assertEquals(setExpected, setActualForgetButtonsText2);
	}
}
