package learning.budget;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JComboBox;

public class GetId {
	public static int getCategoryId(HashMap<Integer, String> categoryMap, String comboBoxChoosenValue) {
		int idCategory = 0;
		for (Entry<Integer, String> entry : categoryMap.entrySet()) {
			if (comboBoxChoosenValue.equals(entry.getValue())) {
				idCategory = entry.getKey();
			}
		}
		return idCategory;
	}
	
	public static int getUserIdFromUserMap(JComboBox<String> cbUser, HashMap<Integer, String> usersNameIdMap) {
		int idUser = 0;
		String userName = cbUser.getSelectedItem().toString();
		for (Entry<Integer, String> entry : usersNameIdMap.entrySet()) {
			if (userName.equals(entry.getValue())) {
				idUser = entry.getKey();
			}
		}
		return idUser;
	}
	
}
