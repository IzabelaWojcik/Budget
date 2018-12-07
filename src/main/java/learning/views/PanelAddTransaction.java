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

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelAddTransaction extends JPanel implements INotifier{
	private JFormattedTextField  formattedTextField;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBox;
	private JButton buttonAdd;
	public final int identifier;
	private Set<IListener> listeners;
	
	final static DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
	final static String groupSeparator = "" + formatter.getDecimalFormatSymbols().getGroupingSeparator();
	
	public PanelAddTransaction(int id){
		setVisible(false);
		setEnabled(false);
		identifier = id;
		listeners = new HashSet<IListener>();
		
		JLabel lblDate = new JLabel("Data:");
		JLabel lblCategory = new JLabel("Kategoria:");
		JLabel lblAmount = new JLabel("Kwota:");
		
		dateChooser = new JDateChooser();
		comboBox = new JComboBox<String>();
		buttonAdd = new JButton("Dodaj");
		buttonAdd.setEnabled(false);
		
		ErrorLabelKeyRelisedListener errorLabel = new ErrorLabelKeyRelisedListener(buttonAdd, Color.RED, new Dimension(170, 20), JLabel.LEFT);
		errorLabel.setVisible(false);
		errorLabel.setEnabled(false);
		
		Date date = new Date();
		dateChooser.setDate(date);

		formattedTextField = new JFormattedTextField(DecimalFormat.getInstance());
		formattedTextField.setColumns(10);
		formattedTextField.addKeyListener(errorLabel);
		
		buttonAdd.addActionListener(e -> {listeners.stream().forEach(listener -> {
				listener.notify(new ButtonAddTransactionData(identifier, dateChooser.getDate(), (String) comboBox.getSelectedItem(), formattedTextField.getText().replaceAll(groupSeparator, "")));});
				formattedTextField.setText("");
				buttonAdd.setEnabled(false);});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(errorLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(buttonAdd)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDate)
									.addGap(5))
								.addComponent(lblAmount)
								.addComponent(lblCategory))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(formattedTextField, Alignment.LEADING)
								.addComponent(dateChooser, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, Alignment.LEADING, 0, 130, Short.MAX_VALUE))))
					.addGap(195))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCategory)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDate)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmount)
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(buttonAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		setLayout(groupLayout);
	}

	public void fillComboBox(List<String> categories) {
		for(String category: categories){
			comboBox.addItem(category);
	    }
	}

	public void clearComboBox() {
		comboBox.removeAllItems();
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
