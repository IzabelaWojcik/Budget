package learning.budget.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import learning.budget.DatabaseConnection;
import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.GenerateComponents;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateBudgetForNewMonth extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private UserIncomnePanel panelIncomeValues;
	private UserNamesPanel panelIncomUsersNames;
	private JPanel panelIncom;
	private ErrorLabels panelIncomeErrorLabels;
	private JButton okButton;
	private IDatabaseWriter databaseWriter;
	private IDatabaseReader databaseReader;
	private GenerateComponents generateComponents;

	public static void main(String[] args) {
		try {
			IDatabaseWriter databaseWriter = DatabaseWriter.getInstance();
			DatabaseWriter.setConnection(DatabaseConnection.getInstance());
			IDatabaseReader databaseReader = DatabaseReader.getInstance();
			DatabaseReader.setConnection(DatabaseConnection.getInstance());
			CreateBudgetForNewMonth dialog = new CreateBudgetForNewMonth(databaseReader, databaseWriter);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateBudgetForNewMonth(IDatabaseReader _databaseReader, IDatabaseWriter _databaseWritter) throws DatabaseNotInitialized {
		databaseReader = _databaseReader;
		databaseWriter = _databaseWritter;
		
		generateComponents = new GenerateComponents(databaseReader, databaseWriter);
				
		setTitle("Nowy Bud\u017Cet");
		setBounds(100, 100, 496, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);

		panelIncom = new JPanel();
		panelIncom.setMaximumSize(new Dimension(500, 500));
		panelIncom.setBorder(null);

		panelIncomUsersNames = new UserNamesPanel(new Dimension(50, 50), new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelIncomUsersNames.fillWithUserNames(generateComponents.getUserNames());
		
		panelIncomeErrorLabels = new ErrorLabels();
		panelIncomeErrorLabels.fill(generateComponents.getUserNames().size());
	
		panelIncomeValues = new UserIncomnePanel();
		panelIncomeValues.fillWithUserIncomnes(generateComponents.getUserNames().size(), panelIncomeErrorLabels.getComponents(), okButton);
		
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
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
