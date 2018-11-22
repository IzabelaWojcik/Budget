package learning.views;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import learning.budget.IDatabaseWriter;
import learning.budget.OldViews.ErrorLabelPropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.javatuples.Quintet;

public class PanelAddIncome extends JPanel implements INotifier{
	private JFormattedTextField  formattedTextField;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxCategory;
	private JComboBox<String> comboBoxUser;
	private JButton buttonAdd;
	public final int identifier;
	private Set<IListener> listeners;
	
	public PanelAddIncome(int id){
		identifier = id;
		listeners = new HashSet<IListener>();
		
		JLabel lblDate = new JLabel("Data:");
		JLabel lblCategory = new JLabel("Kategoria:");
		JLabel lblAmount = new JLabel("Kwota:");
		JLabel lblUser = new JLabel("Użytkownik:");
		
		dateChooser = new JDateChooser();
		comboBoxCategory = new JComboBox<String>();
		comboBoxUser = new JComboBox<String>();
		buttonAdd = new JButton("Dodaj");
		
		ErrorLabelPropertyChangeListener errorLabel = new ErrorLabelPropertyChangeListener(Color.RED, new Dimension(170, 20), JLabel.LEFT);
		
		Date date = new Date();
		dateChooser.setDate(date);

		formattedTextField = new JFormattedTextField(DecimalFormat.getInstance());
		formattedTextField.setColumns(10);
		formattedTextField.addPropertyChangeListener(errorLabel);
		
		buttonAdd.addActionListener(e -> {listeners.stream().forEach(listener -> {listener.notify(new ButtonAddTransactionData(identifier, date, (String) comboBoxCategory.getSelectedItem(), formattedTextField.getText()));});});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDate)
							.addGap(5))
						.addComponent(lblAmount)
						.addComponent(lblCategory))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxCategory, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(buttonAdd)
							.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCategory)
										.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDate)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmount)
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(buttonAdd)
					.addGap(29))
		);
		setLayout(groupLayout);
	}

	public void fillComboBox(List<String> list, JComboBox combobox) {
		for(String s: list){
			combobox.addItem(s);
	    }
	}
	
	public JComboBox getComboboxUser() {
		return comboBoxUser;
	}
	
	public JComboBox getComboboxCategory() {
		return comboBoxCategory;
	}

	@Override
	public void register(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public void deregister(IListener listener) {
		listeners.remove(listener);
	}
	
}
