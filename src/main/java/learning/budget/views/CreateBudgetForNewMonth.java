package learning.budget.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import learning.budget.DataFormatter;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.GenerateComponents;
import learning.budget.TextFieldValidator;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class CreateBudgetForNewMonth extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelIncomeValues;
	private JPanel panelIncomUsersNames;
	private JPanel panelIncom;
	private JPanel panelIncomeErrorLabels;
	private JButton okButton;
	private JButton cancelButton;
	private HashMap<Integer, Double> incomeMap = new HashMap<Integer, Double>();
	private DatabaseWriter databaseWriter = new DatabaseWriter();
	private DatabaseReader databaseReader = new DatabaseReader();
	private GenerateComponents generateComponents = new GenerateComponents();
	
	private DataFormatter dataFormatter = new DataFormatter();
	///////////////////
	//private long time = System.currentTimeMillis();            ///}data -								}
	//private java.sql.Date sqlDate = new java.sql.Date(time);   ///}dzisiejsza, da si� zapisac do bazy }razem 
	////////////////
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateBudgetForNewMonth dialog = new CreateBudgetForNewMonth();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateBudgetForNewMonth() {
		setTitle("Nowy Bud\u017Cet");
		setBounds(100, 100, 496, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);

		panelIncom = new JPanel();
		panelIncom.setMaximumSize(new Dimension(500, 500));
		panelIncom.setBorder(null);

		panelIncomUsersNames = new JPanel();
		panelIncomUsersNames.setMaximumSize(new Dimension(50, 50));
		panelIncomUsersNames.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelIncomeValues = new JPanel();

		panelIncomeErrorLabels = new JPanel();
		GroupLayout gl_panelIncom = new GroupLayout(panelIncom);
		gl_panelIncom.setHorizontalGroup(
			gl_panelIncom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIncom.createSequentialGroup()
					.addComponent(panelIncomUsersNames, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelIncomeValues, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelIncomeErrorLabels, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		gl_panelIncom.setVerticalGroup(
			gl_panelIncom.createParallelGroup(Alignment.LEADING)
				.addComponent(panelIncomUsersNames, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
				.addComponent(panelIncomeValues, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
				.addComponent(panelIncomeErrorLabels, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
		);
		panelIncom.setLayout(gl_panelIncom);
		
		JLabel lblWpiszPrzychody = new JLabel("Wpisz przychody:");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(38)
							.addComponent(lblWpiszPrzychody))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(panelIncom, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblWpiszPrzychody)
					.addGap(18)
					.addComponent(panelIncom, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
					.addContainerGap())
		);

		
		generateComponents.generateUsersAndIncomeTextFields(panelIncomUsersNames, panelIncomeValues, panelIncomeErrorLabels, okButton);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						try{
//							/////
//							int budgetId =;
//							int idOfIncome = 1;
//							int dateOfBegginingNewBudgetMonth = databaseReader.readDateOfBegginingNewBudgerMonthFromDatabase(budgetId);
//							int month = 
//							int year = 		
//							int sqlDate = dateOfBegginingNewBudgetMonth + "-" + month + "-" + year;
//							for(Entry<Integer, Double> s : incomeMap.entrySet()){
//								int idUser = s.getKey();
//								double amount = s.getValue();
//								databaseWriter.writeIncomeToDatabase(amount, sqlDate, idUser, idOfIncome, budgetId);
//							}
//							dispose();
//						}catch(NullPointerException | ParseException except){
//							JOptionPane.showMessageDialog(null, "Wybierz dat�");
//						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
//			{
//				cancelButton = new JButton("Cancel");
//				cancelButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						dispose();
//					}
//				});
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
		}
	}

	

}
