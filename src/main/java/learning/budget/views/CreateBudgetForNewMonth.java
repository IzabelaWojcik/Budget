package learning.budget.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import learning.budget.DatabaseConnection;
import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.IDatabaseReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CreateBudgetForNewMonth extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelIncom;
	private JButton okButton;
	private IDatabaseReader databaseReader;

	public static void main(String[] args) {
		try {
			DatabaseWriter.setConnection(DatabaseConnection.getInstance());
			IDatabaseReader databaseReader = DatabaseReader.getInstance();
			DatabaseReader.setConnection(DatabaseConnection.getInstance());
			CreateBudgetForNewMonth dialog = new CreateBudgetForNewMonth(databaseReader);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateBudgetForNewMonth(IDatabaseReader _databaseReader) throws DatabaseNotInitialized {
		databaseReader = _databaseReader;
		
		setTitle("Nowy Bud\u017Cet");
		setBounds(100, 100, 496, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);

		panelIncom = new JPanel();
		panelIncom.setMaximumSize(new Dimension(500, 500));
		panelIncom.setBorder(null);
		okButton = new JButton("OK");

		PropertyChangeListener listener = new UserIncomeInputFiledListener(okButton);
		List<String> userNames = new ArrayList<>(databaseReader.readUsersFromDatabasetoHashMap().values());

		for(String name : userNames){
			UserInputPanel inputPanel = new UserInputPanel(name);
			inputPanel.addPropertyChangeListener(listener);
			panelIncom.add(inputPanel);
		}
		
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
