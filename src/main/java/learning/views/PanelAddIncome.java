package learning.views;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import learning.budget.DatabaseNotInitialized;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelAddIncome extends JPanel implements INotifier{
	public final int identifier;
	
	private JFormattedTextField  formattedTextField;
	private JDateChooser dateChooser;
	private JButton buttonAdd;
	private Set<IListener> listeners;
	private JComboBox<String> comboBoxUser;
	private JComboBox<String> comboBoxCategory;
	
	final static DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
	final static String groupSeparator = "" + formatter.getDecimalFormatSymbols().getGroupingSeparator();
	
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
		buttonAdd.setEnabled(false);
		
		ErrorLabelKeyRelisedListener errorLabel = new ErrorLabelKeyRelisedListener(buttonAdd, Color.RED, new Dimension(170, 20), JLabel.LEFT);
		Date date = new Date();
		dateChooser.setDate(date);

		formattedTextField = new JFormattedTextField(DecimalFormat.getInstance());
		formattedTextField.setColumns(10);
		formattedTextField.addKeyListener(errorLabel);
		
		updateListeners();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCategory)
								.addComponent(lblAmount))
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDate)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxUser, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxCategory, 0, 137, Short.MAX_VALUE)
								.addComponent(formattedTextField, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
								.addComponent(buttonAdd, Alignment.TRAILING))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(9))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategory))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonAdd)
					.addGap(12)
					.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	private void updateListeners() {
		buttonAdd.addActionListener(e -> {listeners.stream().forEach(
				listener -> {
					try {
						listener.notify(new ButtonAddIncomeData(identifier, dateChooser.getDate(), (String) comboBoxCategory.getSelectedItem(), (String) comboBoxUser.getSelectedItem(), formattedTextField.getText().replaceAll(groupSeparator, "")));
					} catch (DatabaseNotInitialized | BudgetNotFoundException e1) {
						e1.printStackTrace();
					}}); 
					formattedTextField.setText("");
					buttonAdd.setEnabled(false);
					});
	}

	public void fillComboBoxCategory(List<String> list) {
		for(String s: list){
			comboBoxCategory.addItem(s);
	    }
	}
	
	public void fillComboBoxUser(List<String> list) {
		for(String s: list){
			comboBoxUser.addItem(s);
	    }
	}

	public void clearComboBoxCategory() {
		comboBoxCategory.removeAllItems();
	} 
	
	public void clearComboBoxUser() {
		comboBoxUser.removeAllItems();
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
