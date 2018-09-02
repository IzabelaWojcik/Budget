package learning.budget.views;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import learning.budget.IDatabaseWriter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.javatuples.Quintet;

public class PanelAddTransactionWithComboBoxCategory extends JPanel{
	private JFormattedTextField  textFieldAmount;
	
	public PanelAddTransactionWithComboBoxCategory(HashMap<Integer, String> categoryHashMap, IDatabaseWriter databaseWriter, int clickedBudgetId, String tablename) {
		JLabel lblDate = new JLabel("Data:");
		JLabel lblCategory = new JLabel("Kategoria:");
		JLabel lblAmount = new JLabel("Kwota:");
		JDateChooser dateChooser = new JDateChooser();
		JComboBox<String> comboBoxCategory = new JComboBox<String>();
		JButton btnAdd = new JButton("Dodaj");
		ErrorLabelPropertyChangeListener errorLabel = new ErrorLabelPropertyChangeListener(Color.RED, new Dimension(170, 20), JLabel.LEFT);

		Date date = new Date();
		dateChooser.setDate(date);

		textFieldAmount = new JFormattedTextField(DecimalFormat.getInstance());
		textFieldAmount.setColumns(10);
		textFieldAmount.addPropertyChangeListener(errorLabel);
		
		for(Entry<Integer, String> e: categoryHashMap.entrySet()) {
			comboBoxCategory.addItem(e.getValue());
	    }
		
		Quintet<JFormattedTextField, JDateChooser, JComboBox<String>, Integer, String> quintet = new Quintet<JFormattedTextField, JDateChooser, JComboBox<String>, Integer, String>(textFieldAmount, dateChooser, comboBoxCategory, clickedBudgetId, tablename);
		AddTransactionToDatabaseListener addTransactionToDatabaseListener = new AddTransactionToDatabaseListener(databaseWriter, categoryHashMap, quintet);
		btnAdd.addActionListener(addTransactionToDatabaseListener);
			
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
							.addComponent(btnAdd)
							.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAdd)
					.addGap(29))
		);
		setLayout(groupLayout);
	}
}
