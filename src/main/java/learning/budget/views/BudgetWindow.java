package learning.budget.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class BudgetWindow {

	private JFrame frmBudetDomowy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetWindow window = new BudgetWindow();
					window.frmBudetDomowy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BudgetWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBudetDomowy = new JFrame();
		frmBudetDomowy.setTitle("Budżet domowy");
		frmBudetDomowy.setBounds(100, 100, 1043, 760);
		frmBudetDomowy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelButtons = new JPanel();
		
		JPanel panelCardLeyout = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmBudetDomowy.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelCardLeyout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
						.addComponent(panelButtons, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelCardLeyout, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
					.addContainerGap())
		);
		GroupLayout gl_panelButtons = new GroupLayout(panelButtons);
		gl_panelButtons.setHorizontalGroup(
			gl_panelButtons.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1001, Short.MAX_VALUE)
		);
		gl_panelButtons.setVerticalGroup(
			gl_panelButtons.createParallelGroup(Alignment.LEADING)
				.addGap(0, 164, Short.MAX_VALUE)
		);
		panelButtons.setLayout(gl_panelButtons);
		panelCardLeyout.setLayout(new CardLayout(0, 0));
		
		JPanel panelBudgetNotEmpty = new JPanel();
		panelCardLeyout.add(panelBudgetNotEmpty, "name_33162151016161");
		frmBudetDomowy.getContentPane().setLayout(groupLayout);
		
		
	}

}
