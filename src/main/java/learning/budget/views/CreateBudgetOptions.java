package learning.budget.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Point;
import java.awt.Rectangle;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.TextFieldValidator;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreateBudgetOptions extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel1;
	private JPanel panel1_Users;
	private JTextField textFieldHowManyUsersToDisplay;
	private JLabel lblErrorMessageUserNumber;
	private JPanel panel3;
	private JPanel panel2;
	private JCheckBox chckbxKredyt;
	private JCheckBox chckbxPozyczka;
	private JTextField textFieldKatOplaty1;
	private JTextField textFieldKatOplaty2;
	private JTextField textFieldKatOplaty3;
	private JTextField textFieldKatOplaty4;
	private JTextField textFieldKatOplaty5;
	private JCheckBox chckbxCzynsz;
	private JCheckBox chckbxPrad;
	private JCheckBox chckbxInternet;
	private JCheckBox chckbxOplatyZaTelefon;
	private JCheckBox chckbxSplataKartyKredytowej;
	private JCheckBox chckbxZakupy;
	private JCheckBox chckbxJedzenie;
	private JCheckBox chckbxChemia;
	private JCheckBox chckbxPaliwo;
	private JCheckBox chckbxDojazdy;
	private JCheckBox chckbxZwierzeta;
	private JCheckBox chckbxFastFood;
	private JCheckBox chckbxSport;
	private JCheckBox chckbxKulturarozrywka;
	private JCheckBox chckbxSprzety;
	private JCheckBox chckbxDzieci;
	private JCheckBox chckbxOplatyZaSzkole;
	private JCheckBox chckbxPodrecznikizeszyty;
	private JCheckBox chckbxLeki;
	private JCheckBox chckbxSamochod;
	private JCheckBox chckbxSlub;
	private JCheckBox chckbxDlaDziecka;
	private JCheckBox chckbxPrezenty;
	private JCheckBox chckbxWakacje;
	private JCheckBox chckbxSprzetyElektroniczne;
	private JCheckBox chckbxMeble;
	private JCheckBox chckbxRemonty;
	private JPanel panelButton1;
	private JButton btnAnuluj1;
	private JButton btnZakoncz1;
	private JButton btnDalej1;
	private JButton btnCofnij1;
	private JPanel panelButton2;
	private JButton btnCofnij2;
	private JButton btnDalej2;
	private JButton btnAnuluj2;
	private JButton btnZakoncz2;
	private JPanel panelButton3;
	private JButton btnCofnij3;
	private JButton btnDalej3;
	private JButton btnAnuluj3;
	private JButton btnZakoncz3;
	private JPanel panelButton4;
	private JButton btnCofnij4;
	private JButton btnDalej4;
	private JButton btnAnuluj4;
	private JButton btnZakoncz4;
	private int userNumber;
	private JPanel panel4;
	CreateBudgetOptions dialog;
	private JCheckBox checkBoxKatOplaty1;
	private JCheckBox checkBoxKatOplaty2;
	private JPanel panelOplaty;
	private JPanel panelOplatyKatUzytkownika;
	private JCheckBox chckbxKatOplaty3;
	private JCheckBox chckbxKatOplaty4;
	private JCheckBox chckbxKatOplaty5;
	private JLabel lblOpaty;
	private JPanel panelZakupy;
	private JLabel lblWydatki;
	private JPanel panel;
	private JCheckBox checkBoxKatWydatki1;
	private JTextField textFieldKatWydatki1;
	private JCheckBox checkBoxKatWydatki2;
	private JTextField textFieldKatWydatki2;
	private JCheckBox checkBoxKatWydatki3;
	private JTextField textFieldKatWydatki3;
	private JCheckBox checkBoxKatWydatki4;
	private JTextField textFieldKatWydatki4;
	private JCheckBox checkBoxKatWydatki5;
	private JTextField textFieldKatWydatki5;
	private JLabel lblOszczdnoci;
	private JPanel panelOszczednosci;
	private JPanel panel_1;
	private JCheckBox checkBoxKatOszczednosci1;
	private JTextField textFieldKatOszczednosci1;
	private JCheckBox checkBoxKatOszczednosci2;
	private JTextField textFieldKatOszczednosci2;
	private JCheckBox checkBoxKatOszczednosci3;
	private JTextField textFieldKatOszczednosci3;
	private JCheckBox checkBoxKatOszczednosci4;
	private JTextField textFieldKatOszczednosci4;
	private JCheckBox checkBoxKatOszczednosci5;
	private JTextField textFieldKatOszczednosci5;
	private int maxNumberOfUsersInBudget = 8;
	private HashMap<String, String> usersMap = new HashMap<String, String>();
	private ArrayList<String> oplatyList = new ArrayList<String>();
	private JCheckBox chckbxFunduszRemontowy;
	private JLabel lblErrorPanel2;
	private String errorMessageKategorie = "Uzupennij wszystkie zaznaczone kategorie";
	private int numKatOplaty = 0;
	private HashMap<String, String> oplatyKatFromTxtFieldMap = new HashMap<String, String>();
	private ArrayList<String> wydatkiList = new ArrayList<String>();
	private HashMap<String, String> wydatkiKatFromTextFieldMap = new HashMap<String, String>();
	private int numKatWydatki = 0;
	private JLabel lblErrorPanel3;
	private HashMap<String, String> oszczednosciFromTxtFieldMap = new HashMap<String, String>();
	private ArrayList<String> oszczednosciList = new ArrayList<String>();
	private int numKatOszczednosci = 0;
	private JLabel lblErrorPanel4;
	private JComboBox<String> comboBox;
	private int dayOfNewBudgetMonth = 1;
	private JTextField textFieldBudgetName;
	private String budgetName;
	private int idBudget = 0;
	private DatabaseReader databaseReader = new DatabaseReader();
	private DatabaseWriter databaseWriter = new DatabaseWriter();
	private TextFieldValidator textFieldValidator = new TextFieldValidator();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateBudgetOptions dialog = new CreateBudgetOptions();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateBudgetOptions() {
		setTitle("Nowy projekt");
		setBounds(100, 100, 578, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			panel1 = new JPanel();

			contentPanel.add(panel1, "name_22752312806569");

			JLabel lblIloscUzytkownikow = new JLabel(
					"Ilo\u015B\u0107 u\u017Cytkownik\u00F3w gospodarstwa domowego posiadaj\u0105cych dochody:");

			panel1_Users = new JPanel();

			textFieldHowManyUsersToDisplay = new JTextField();
			textFieldHowManyUsersToDisplay.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						createUsers();
					}
				}
			});
			textFieldHowManyUsersToDisplay.setColumns(10);

			JButton btnHowManyUsersToDiplay = new JButton("Dodaj u\u017Cytkownik\u00F3w");
			btnHowManyUsersToDiplay.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						createUsers();
					}
				}
			});
			btnHowManyUsersToDiplay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createUsers();
				}
			});

			lblErrorMessageUserNumber = new JLabel("");
			lblErrorMessageUserNumber.setForeground(Color.RED);

			panelButton1 = new JPanel();
			
			JLabel lblDzienRozpoczeciaBudzetu = new JLabel("Wybierz dzie\u0144 rozpocz\u0119cia bud\u017Cetu na ka\u017Cdy nowy miesi\u0105c:");
			
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
			
			JLabel lblNazwaNowegoBudetu = new JLabel("Nazwa nowego bud\u017Cetu:");
			
			textFieldBudgetName = new JTextField();

			textFieldBudgetName.setColumns(10);

			GroupLayout gl_panel1 = new GroupLayout(panel1);
			gl_panel1.setHorizontalGroup(
				gl_panel1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel1.createSequentialGroup()
						.addGap(32)
						.addComponent(lblIloscUzytkownikow)
						.addContainerGap(31, Short.MAX_VALUE))
					.addGroup(gl_panel1.createSequentialGroup()
						.addGap(23)
						.addComponent(lblDzienRozpoczeciaBudzetu)
						.addGap(18)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(128, Short.MAX_VALUE))
					.addGroup(gl_panel1.createSequentialGroup()
						.addGap(22)
						.addComponent(textFieldHowManyUsersToDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnHowManyUsersToDiplay)
						.addContainerGap(245, Short.MAX_VALUE))
					.addGroup(gl_panel1.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNazwaNowegoBudetu)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textFieldBudgetName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(269, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING, gl_panel1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel1.createSequentialGroup()
								.addComponent(panel1_Users, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblErrorMessageUserNumber, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
							.addComponent(panelButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(17))
			);
			gl_panel1.setVerticalGroup(
				gl_panel1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel1.createSequentialGroup()
						.addGap(31)
						.addComponent(lblIloscUzytkownikow)
						.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
						.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNazwaNowegoBudetu)
							.addComponent(textFieldBudgetName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnHowManyUsersToDiplay)
							.addComponent(textFieldHowManyUsersToDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
							.addComponent(panel1_Users, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblErrorMessageUserNumber, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDzienRozpoczeciaBudzetu)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(31)
						.addComponent(panelButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			panelButton1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			btnCofnij1 = new JButton("Cofnij");
			btnCofnij1.setEnabled(false);
			btnCofnij1.setVisible(false);
			btnCofnij1.setPreferredSize(new Dimension(80, 25));
			panelButton1.add(btnCofnij1);

			btnDalej1 = new JButton("Dalej");
			btnDalej1.setEnabled(false);
			btnDalej1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						boolean budgetNameIsUnique = checkIfBudgetNameIsUniqueName();
						boolean textFieldIsEmpty = textFieldValidator.checkIfTextFieldIsEmpty(textFieldBudgetName);
						if(!budgetNameIsUnique){
							JOptionPane.showMessageDialog(null, "Podana nazwa jun istnieje");
						}if(textFieldIsEmpty){
							JOptionPane.showMessageDialog(null, "Wpisz nazwn budnetu");
						}else{
							JOptionPane.showMessageDialog(null, "Wpszystko ok");
							panel1.setVisible(false);
							panel2.setVisible(true);
							panel3.setVisible(false);
							panel4.setVisible(false);
						}
						dayOfNewBudgetMonth = Integer.parseInt((String) comboBox.getSelectedItem());
					}
				}
			});
			
			btnDalej1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean budgetNameIsUnique = checkIfBudgetNameIsUniqueName();
					boolean textFieldIsEmpty = textFieldValidator.checkIfTextFieldIsEmpty(textFieldBudgetName);
					if(textFieldIsEmpty){
						JOptionPane.showMessageDialog(null, "Wpisz nazwn budnetu");
					}if(!budgetNameIsUnique){
						JOptionPane.showMessageDialog(null, "Podana nazwa jun istnieje");
					}else{
						JOptionPane.showMessageDialog(null, "Wpszystko ok");
						panel1.setVisible(false);
						panel2.setVisible(true);
						panel3.setVisible(false);
						panel4.setVisible(false);
					}
					dayOfNewBudgetMonth = Integer.parseInt((String) comboBox.getSelectedItem());
				}
			});
			btnDalej1.setPreferredSize(new Dimension(80, 25));
			panelButton1.add(btnDalej1);

			btnAnuluj1 = new JButton("Anuluj");
			btnAnuluj1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						anuluj();
					}
				}
			});
			btnAnuluj1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anuluj();
				}
			});
			btnAnuluj1.setPreferredSize(new Dimension(80, 25));
			panelButton1.add(btnAnuluj1);

			btnZakoncz1 = new JButton("Zako\u0144cz");
			btnZakoncz1.setEnabled(false);
			btnZakoncz1.setPreferredSize(new Dimension(80, 25));
			panelButton1.add(btnZakoncz1);
			panel1_Users.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel1.setLayout(gl_panel1);
		}

		panel2 = new JPanel();

		contentPanel.add(panel2, "name_18928081506810");

		panelButton2 = new JPanel();
		panelButton2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCofnij2 = new JButton("Cofnij");
		btnCofnij2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel1.setVisible(true);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(false);
				}
			}
		});
		btnCofnij2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
			}
		});
		btnCofnij2.setPreferredSize(new Dimension(80, 25));
		panelButton2.add(btnCofnij2);

		btnDalej2 = new JButton("Dalej");
		if (oplatyList.size() > 0 || oplatyKatFromTxtFieldMap.size() > 0)
			btnDalej2.setEnabled(true);
		else
			btnDalej2.setEnabled(false);
		btnDalej2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					panel4.setVisible(false);
				}
			}
		});
		btnDalej2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCzynsz.isSelected() && !oplatyList.contains(chckbxCzynsz.getText())) {
					oplatyList.add(chckbxCzynsz.getText());
				}
				if (chckbxPrad.isSelected() && !oplatyList.contains(chckbxPrad.getText())) {
					oplatyList.add(chckbxPrad.getText());
				}
				if (chckbxFunduszRemontowy.isSelected() && !oplatyList.contains(chckbxFunduszRemontowy.getText())) {
					oplatyList.add(chckbxFunduszRemontowy.getText());
				}
				if (chckbxInternet.isSelected() && !oplatyList.contains(chckbxInternet.getText())) {
					oplatyList.add(chckbxInternet.getText());
				}
				if (chckbxOplatyZaTelefon.isSelected() && !oplatyList.contains(chckbxOplatyZaTelefon.getText())) {
					oplatyList.add(chckbxOplatyZaTelefon.getText());
				}
				if (chckbxPozyczka.isSelected() && !oplatyList.contains(chckbxPozyczka.getText())) {
					oplatyList.add(chckbxPozyczka.getText());
				}
				if (chckbxSplataKartyKredytowej.isSelected()
						&& !oplatyList.contains(chckbxSplataKartyKredytowej.getText())) {
					oplatyList.add(chckbxSplataKartyKredytowej.getText());
				}
				if (chckbxKredyt.isSelected() && !oplatyList.contains(chckbxKredyt.getText())) {
					oplatyList.add(chckbxKredyt.getText());
				}

				if (oplatyList.contains(chckbxCzynsz.getText()) && !chckbxCzynsz.isSelected()) {
					oplatyList.remove(chckbxCzynsz.getText());
				}
				if (oplatyList.contains(chckbxPrad.getText()) && !chckbxPrad.isSelected()) {
					oplatyList.remove(chckbxPrad.getText());
				}
				if (oplatyList.contains(chckbxFunduszRemontowy.getText()) && !chckbxFunduszRemontowy.isSelected()) {
					oplatyList.remove(chckbxFunduszRemontowy.getText());
				}
				if (oplatyList.contains(chckbxInternet.getText()) && !chckbxInternet.isSelected()) {
					oplatyList.remove(chckbxInternet.getText());
				}
				if (oplatyList.contains(chckbxOplatyZaTelefon.getText()) && !chckbxOplatyZaTelefon.isSelected()) {
					oplatyList.remove(chckbxOplatyZaTelefon.getText());
				}
				if (oplatyList.contains(chckbxPozyczka.getText()) && !chckbxPozyczka.isSelected()) {
					oplatyList.remove(chckbxPozyczka.getText());
				}
				if (oplatyList.contains(chckbxSplataKartyKredytowej.getText())
						&& !chckbxSplataKartyKredytowej.isSelected()) {
					oplatyList.remove(chckbxSplataKartyKredytowej.getText());
				}
				if (oplatyList.contains(chckbxKredyt.getText()) && !chckbxKredyt.isSelected()) {
					oplatyList.remove(chckbxKredyt.getText());
				}
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);

			}
		});
		btnDalej2.setPreferredSize(new Dimension(80, 25));
		panelButton2.add(btnDalej2);

		btnAnuluj2 = new JButton("Anuluj");
		btnAnuluj2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					anuluj();
				}
			}
		});
		btnAnuluj2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anuluj();
			}
		});
		btnAnuluj2.setPreferredSize(new Dimension(80, 25));
		panelButton2.add(btnAnuluj2);

		btnZakoncz2 = new JButton("Zako\u0144cz");
		btnZakoncz2.setEnabled(false);
		btnZakoncz2.setPreferredSize(new Dimension(80, 25));
		panelButton2.add(btnZakoncz2);

		panelOplaty = new JPanel();
		panelOplaty.setBounds(new Rectangle(20, 20, 20, 0));
		panelOplaty.setLocation(new Point(10, 10));
		panelOplaty.setBorder(
				new TitledBorder(null, "Wybierz op\u0142aty:", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelOplatyKatUzytkownika = new JPanel();
		panelOplatyKatUzytkownika.setBorder(new TitledBorder(null, "Wpisz w\u0142asne kategorie", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		lblOpaty = new JLabel("OP\u0141ATY");

		lblErrorPanel2 = new JLabel("");
		lblErrorPanel2.setForeground(Color.RED);
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(gl_panel2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel2
						.createSequentialGroup().addGap(33).addGroup(gl_panel2
								.createParallelGroup(Alignment.LEADING).addGroup(gl_panel2.createSequentialGroup()
										.addComponent(panelOplaty, GroupLayout.PREFERRED_SIZE, 186,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
												.addComponent(lblErrorPanel2).addComponent(panelOplatyKatUzytkownika,
														GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblOpaty))
						.addContainerGap(39, Short.MAX_VALUE))
				.addGroup(gl_panel2.createSequentialGroup().addContainerGap(193, Short.MAX_VALUE)
						.addComponent(panelButton2, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup().addGap(22).addComponent(lblOpaty)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel2.createParallelGroup(Alignment.BASELINE)
								.addComponent(panelOplaty, GroupLayout.PREFERRED_SIZE, 249,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel2.createSequentialGroup()
										.addComponent(panelOplatyKatUzytkownika, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
										.addComponent(lblErrorPanel2).addGap(15)))
						.addGap(162)
						.addComponent(panelButton2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		checkBoxKatOplaty1 = new JCheckBox("Kategoria 1:");
		checkBoxKatOplaty1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldKatOplaty1.setEnabled(true);
					btnDalej2.setEnabled(false);
					checkBoxKatOplaty2.setEnabled(true);
					numKatOplaty++;
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
					setButtlonDalej2Enabled();
				} else {
					numKatOplaty--;
					oplatyKatFromTxtFieldMap.clear();
					textFieldKatOplaty1.setEnabled(false);
					textFieldKatOplaty1.setText("");
					if (checkBoxKatOplaty2.isEnabled()) {
						checkBoxKatOplaty2.setEnabled(false);
						checkBoxKatOplaty2.setSelected(false);
					}
					if (chckbxKatOplaty3.isEnabled()) {
						chckbxKatOplaty3.setEnabled(false);
						chckbxKatOplaty3.setSelected(false);
					}
					if (chckbxKatOplaty4.isEnabled()) {
						chckbxKatOplaty4.setEnabled(false);
						chckbxKatOplaty4.setSelected(false);
					}
					if (chckbxKatOplaty5.isEnabled()) {
						chckbxKatOplaty5.setEnabled(false);
						chckbxKatOplaty5.setSelected(false);
					}
					setButtlonDalej2Enabled();
					lblErrorPanel2.setText("");
				}
			}
		});

		textFieldKatOplaty1 = new JTextField();
		textFieldKatOplaty1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value = textFieldKatOplaty1.getText().toString();
				String key = checkBoxKatOplaty1.getText().toString();
				oplatyKatFromTxtFieldMap.put(key, value);
				lblErrorPanel2.setText("");
				if (oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
					btnDalej2.setEnabled(false);
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					btnDalej2.setEnabled(true);
				}
			}
		});
		textFieldKatOplaty1.setEnabled(false);
		textFieldKatOplaty1.setColumns(10);

		checkBoxKatOplaty2 = new JCheckBox("Kategoria 2:");
		checkBoxKatOplaty2.setEnabled(false);
		checkBoxKatOplaty2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldKatOplaty2.setEnabled(true);
					btnDalej2.setEnabled(false);
					chckbxKatOplaty3.setEnabled(true);
					numKatOplaty++;
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				}else {
					numKatOplaty--;
					oplatyKatFromTxtFieldMap.remove("Kategoria 2:");
					textFieldKatOplaty2.setEnabled(false);
					textFieldKatOplaty2.setText("");
					if (chckbxKatOplaty3.isEnabled()) {
						chckbxKatOplaty3.setEnabled(false);
						chckbxKatOplaty3.setSelected(false);
						oplatyKatFromTxtFieldMap.remove("Kategoria 3:");
					}
					if (chckbxKatOplaty4.isEnabled()) {
						chckbxKatOplaty4.setEnabled(false);
						chckbxKatOplaty4.setSelected(false);
						oplatyKatFromTxtFieldMap.remove("Kategoria 4:");
					}
					if (chckbxKatOplaty5.isEnabled()) {
						chckbxKatOplaty5.setEnabled(false);
						chckbxKatOplaty5.setSelected(false);
						oplatyKatFromTxtFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});

		textFieldKatOplaty2 = new JTextField();
		textFieldKatOplaty2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value = textFieldKatOplaty2.getText().toString();
				String key = checkBoxKatOplaty2.getText().toString();
				oplatyKatFromTxtFieldMap.put(key, value);
				lblErrorPanel2.setText("");
				if (oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
					btnDalej2.setEnabled(false);
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					btnDalej2.setEnabled(true);
				}
			}
		});
		textFieldKatOplaty2.setEnabled(false);
		textFieldKatOplaty2.setColumns(10);

		chckbxKatOplaty3 = new JCheckBox("Kategoria 3:");
		chckbxKatOplaty3.setEnabled(false);
		chckbxKatOplaty3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldKatOplaty3.setEnabled(true);
					btnDalej2.setEnabled(false);
					chckbxKatOplaty4.setEnabled(true);
					numKatOplaty++;
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					numKatOplaty--;
					oplatyKatFromTxtFieldMap.remove("Kategoria 3:");
					textFieldKatOplaty3.setEnabled(false);
					textFieldKatOplaty3.setText("");
					if (chckbxKatOplaty4.isEnabled()) {
						chckbxKatOplaty4.setEnabled(false);
						chckbxKatOplaty4.setSelected(false);
						oplatyKatFromTxtFieldMap.remove("Kategoria 4:");
					}
					if (chckbxKatOplaty5.isEnabled()) {
						chckbxKatOplaty5.setEnabled(false);
						chckbxKatOplaty5.setSelected(false);
						oplatyKatFromTxtFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});

		textFieldKatOplaty3 = new JTextField();
		textFieldKatOplaty3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value = textFieldKatOplaty3.getText().toString();
				String key = chckbxKatOplaty3.getText().toString();
				oplatyKatFromTxtFieldMap.put(key, value);
				lblErrorPanel2.setText("");
				if (oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
					btnDalej2.setEnabled(false);
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					btnDalej2.setEnabled(true);
				}
			}
		});
		textFieldKatOplaty3.setEnabled(false);
		textFieldKatOplaty3.setColumns(10);

		chckbxKatOplaty4 = new JCheckBox("Kategoria 4:");
		chckbxKatOplaty4.setEnabled(false);
		chckbxKatOplaty4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldKatOplaty4.setEnabled(true);
					btnDalej2.setEnabled(false);
					chckbxKatOplaty5.setEnabled(true);
					numKatOplaty++;
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					numKatOplaty--;
					oplatyKatFromTxtFieldMap.remove("Kategoria 4:");
					textFieldKatOplaty4.setEnabled(false);
					textFieldKatOplaty4.setText("");
					if (chckbxKatOplaty5.isEnabled()) {
						chckbxKatOplaty5.setEnabled(false);
						chckbxKatOplaty5.setSelected(false);
						oplatyKatFromTxtFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});

		textFieldKatOplaty4 = new JTextField();
		textFieldKatOplaty4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value = textFieldKatOplaty4.getText().toString();
				String key = chckbxKatOplaty4.getText().toString();
				oplatyKatFromTxtFieldMap.put(key, value);
				lblErrorPanel2.setText("");
				if (oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
					btnDalej2.setEnabled(false);
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					btnDalej2.setEnabled(true);
				}
			}
		});
		textFieldKatOplaty4.setEnabled(false);
		textFieldKatOplaty4.setColumns(10);

		chckbxKatOplaty5 = new JCheckBox("Kategoria 5:");
		chckbxKatOplaty5.setEnabled(false);
		chckbxKatOplaty5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldKatOplaty5.setEnabled(true);
					btnDalej2.setEnabled(false);
					numKatOplaty++;
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					numKatOplaty--;
					oplatyKatFromTxtFieldMap.remove("Kategoria 5:");
					textFieldKatOplaty5.setEnabled(false);
					textFieldKatOplaty5.setText("");
				}
			}
		});
		
		textFieldKatOplaty5 = new JTextField();
		textFieldKatOplaty5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value = textFieldKatOplaty5.getText().toString();
				String key = chckbxKatOplaty5.getText().toString();
				oplatyKatFromTxtFieldMap.put(key, value);
				btnDalej2.setEnabled(true);
				lblErrorPanel2.setText("");
				if (oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
					btnDalej2.setEnabled(false);
					if (lblErrorPanel2.getText().isEmpty() && oplatyKatFromTxtFieldMap.size() != numKatOplaty) {
						lblErrorPanel2.setText(errorMessageKategorie);
					}
				} else {
					btnDalej2.setEnabled(true);
				}
			}
		});
		textFieldKatOplaty5.setEnabled(false);
		textFieldKatOplaty5.setColumns(10);
		
		GroupLayout gl_panelOplatyKatUzytkownika = new GroupLayout(panelOplatyKatUzytkownika);
		gl_panelOplatyKatUzytkownika.setHorizontalGroup(gl_panelOplatyKatUzytkownika
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup()
										.addComponent(checkBoxKatOplaty1).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldKatOplaty1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup()
										.addComponent(checkBoxKatOplaty2).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldKatOplaty2, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup()
										.addComponent(chckbxKatOplaty3).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldKatOplaty3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup()
										.addComponent(chckbxKatOplaty4).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldKatOplaty4, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup()
										.addComponent(chckbxKatOplaty5).addPreferredGap(ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldKatOplaty5, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(45, Short.MAX_VALUE)));
		gl_panelOplatyKatUzytkownika
				.setVerticalGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOplatyKatUzytkownika.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkBoxKatOplaty1).addComponent(textFieldKatOplaty1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkBoxKatOplaty2)
										.addComponent(textFieldKatOplaty2, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldKatOplaty3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(chckbxKatOplaty3))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.BASELINE)
										.addComponent(chckbxKatOplaty4).addComponent(textFieldKatOplaty4,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelOplatyKatUzytkownika.createParallelGroup(Alignment.BASELINE)
										.addComponent(chckbxKatOplaty5).addComponent(textFieldKatOplaty5,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(18, Short.MAX_VALUE)));
		panelOplatyKatUzytkownika.setLayout(gl_panelOplatyKatUzytkownika);

		chckbxCzynsz = new JCheckBox("Czynsz");
		chckbxCzynsz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		chckbxPrad = new JCheckBox("Pr\u0105d");
		chckbxPrad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		chckbxInternet = new JCheckBox("Internet");
		chckbxInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		chckbxOplatyZaTelefon = new JCheckBox("Op\u0142aty za telefon");
		chckbxOplatyZaTelefon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		chckbxPozyczka = new JCheckBox("Po\u017Cyczka");
		chckbxPozyczka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		chckbxSplataKartyKredytowej = new JCheckBox("Sp\u0142ata karty kredytowej");
		chckbxSplataKartyKredytowej.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});
		chckbxSplataKartyKredytowej.setHorizontalAlignment(SwingConstants.TRAILING);

		chckbxKredyt = new JCheckBox("Kredyt");
		chckbxKredyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		chckbxFunduszRemontowy = new JCheckBox("Fundusz remontowy");
		chckbxFunduszRemontowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej2Enabled();
			}
		});

		GroupLayout gl_panelOplaty = new GroupLayout(panelOplaty);
		gl_panelOplaty.setHorizontalGroup(gl_panelOplaty.createParallelGroup(Alignment.LEADING).addGroup(gl_panelOplaty
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelOplaty.createParallelGroup(Alignment.LEADING).addComponent(chckbxCzynsz)
						.addComponent(chckbxPrad).addComponent(chckbxInternet).addComponent(chckbxOplatyZaTelefon)
						.addComponent(chckbxPozyczka).addComponent(chckbxSplataKartyKredytowej)
						.addComponent(chckbxKredyt).addComponent(chckbxFunduszRemontowy))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panelOplaty.setVerticalGroup(gl_panelOplaty.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOplaty.createSequentialGroup().addContainerGap().addComponent(chckbxCzynsz)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxPrad)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxFunduszRemontowy)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxInternet)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxOplatyZaTelefon)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxPozyczka)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSplataKartyKredytowej)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxKredyt)
						.addContainerGap(15, Short.MAX_VALUE)));

		panelOplaty.setLayout(gl_panelOplaty);
		panel2.setLayout(gl_panel2);

		panel3 = new JPanel();
		contentPanel.add(panel3, "name_16965443428304");

		panelButton3 = new JPanel();
		panelButton3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCofnij3 = new JButton("Cofnij");
		btnCofnij3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
					panel4.setVisible(false);
				}
			}
		});
		btnCofnij3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
			}
		});
		btnCofnij3.setPreferredSize(new Dimension(80, 25));
		panelButton3.add(btnCofnij3);

		btnDalej3 = new JButton("Dalej");
		btnDalej3.setEnabled(false);
		btnDalej3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(false);
					panel4.setVisible(true);
				}
			}
		});
		btnDalej3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(true);
			}
		});
		btnDalej3.setPreferredSize(new Dimension(80, 25));
		panelButton3.add(btnDalej3);

		btnAnuluj3 = new JButton("Anuluj");
		btnAnuluj3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					anuluj();
				}
			}
		});
		btnAnuluj3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anuluj();
			}
		});
		btnAnuluj3.setPreferredSize(new Dimension(80, 25));
		panelButton3.add(btnAnuluj3);

		btnZakoncz3 = new JButton("Zako\u0144cz");
		btnZakoncz3.setEnabled(false);
		btnZakoncz3.setPreferredSize(new Dimension(80, 25));
		panelButton3.add(btnZakoncz3);

		panelZakupy = new JPanel();
		panelZakupy.setBounds(new Rectangle(20, 20, 20, 0));
		panelZakupy.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wybierz wydatki:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblErrorPanel3 = new JLabel("");
		lblErrorPanel3.setForeground(Color.RED);

		chckbxZakupy = new JCheckBox("Zakupy");
		chckbxZakupy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxZakupy.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxZakupy.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxJedzenie = new JCheckBox("Jedzenie");
		chckbxJedzenie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxJedzenie.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxJedzenie.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxChemia = new JCheckBox("Chemia");
		chckbxChemia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxChemia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxChemia.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxPaliwo = new JCheckBox("Paliwo");
		chckbxPaliwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxPaliwo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxPaliwo.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxDojazdy = new JCheckBox("Dojazdy");
		chckbxDojazdy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxDojazdy.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxDojazdy.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxZwierzeta = new JCheckBox("Zwierz\u0119ta");
		chckbxZwierzeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxZwierzeta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxZwierzeta.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxFastFood = new JCheckBox("Fast Food");
		chckbxFastFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxFastFood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxFastFood.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxSport = new JCheckBox("Sport");
		chckbxSport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxSport.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxSport.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxKulturarozrywka = new JCheckBox("Kultura/rozrywka");
		chckbxKulturarozrywka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxKulturarozrywka.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxKulturarozrywka.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxSprzety = new JCheckBox("Sprz\u0119ty");
		chckbxSprzety.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxSprzety.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxSprzety.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxDzieci = new JCheckBox("Dzieci");
		chckbxDzieci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxDzieci.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxDzieci.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxOplatyZaSzkole = new JCheckBox("Op\u0142aty za szko\u0142\u0119");
		chckbxOplatyZaSzkole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxOplatyZaSzkole.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxOplatyZaSzkole.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxPodrecznikizeszyty = new JCheckBox("Podr\u0119czniki/zeszyty");
		chckbxPodrecznikizeszyty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxPodrecznikizeszyty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxPodrecznikizeszyty.getText().toString();
				addWydatkiToList(text, e);
			}
		});

		chckbxLeki = new JCheckBox("Leki");
		chckbxLeki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtlonDalej3Enabled();
			}
		});
		chckbxLeki.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxLeki.getText().toString();
				addWydatkiToList(text, e);
			}
		});
		GroupLayout gl_panelZakupy = new GroupLayout(panelZakupy);
		gl_panelZakupy.setHorizontalGroup(gl_panelZakupy.createParallelGroup(Alignment.LEADING).addGroup(gl_panelZakupy
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelZakupy.createParallelGroup(Alignment.LEADING).addComponent(chckbxZakupy)
						.addComponent(chckbxJedzenie).addComponent(chckbxChemia).addComponent(chckbxPaliwo)
						.addComponent(chckbxDojazdy).addComponent(chckbxZwierzeta).addComponent(chckbxFastFood)
						.addComponent(chckbxSport).addComponent(chckbxKulturarozrywka).addComponent(chckbxSprzety)
						.addComponent(chckbxDzieci).addComponent(chckbxOplatyZaSzkole)
						.addComponent(chckbxPodrecznikizeszyty).addComponent(chckbxLeki))
				.addContainerGap(27, Short.MAX_VALUE)));
		gl_panelZakupy.setVerticalGroup(gl_panelZakupy.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelZakupy.createSequentialGroup().addComponent(chckbxZakupy)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxJedzenie)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxChemia)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxPaliwo)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxDojazdy)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxZwierzeta)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxFastFood)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSport)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxKulturarozrywka)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSprzety)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxDzieci)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxOplatyZaSzkole)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxPodrecznikizeszyty)
						.addPreferredGap(ComponentPlacement.RELATED, 2, Short.MAX_VALUE).addComponent(chckbxLeki)));
		panelZakupy.setLayout(gl_panelZakupy);

		lblWydatki = new JLabel("WYDATKI");

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Wpisz w\u0142asne kategorie", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		checkBoxKatWydatki1 = new JCheckBox("Kategoria 1:");
		checkBoxKatWydatki1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatWydatki1.setEnabled(true);
					btnDalej3.setEnabled(false);
					checkBoxKatWydatki2.setEnabled(true);
					numKatWydatki++;
					if (lblErrorPanel3.getText().isEmpty() && wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
						lblErrorPanel3.setText(errorMessageKategorie);
					}
					setButtlonDalej3Enabled();
				}else{
					wydatkiKatFromTextFieldMap.clear();
					textFieldKatWydatki1.setEnabled(false);
					textFieldKatWydatki1.setText("");
					numKatWydatki--;
					if(checkBoxKatWydatki2.isEnabled()){
						checkBoxKatWydatki2.setEnabled(false);
						checkBoxKatWydatki2.setSelected(false);
					}
					if(checkBoxKatWydatki3.isEnabled()){
						checkBoxKatWydatki3.setEnabled(false);
						checkBoxKatWydatki3.setSelected(false);
					}
					if(checkBoxKatWydatki4.isEnabled()){
						checkBoxKatWydatki4.setEnabled(false);
						checkBoxKatWydatki4.setSelected(false);
					}
					if(checkBoxKatWydatki5.isEnabled()){
						checkBoxKatWydatki5.setEnabled(false);
						checkBoxKatWydatki5.setSelected(false);
					}
					setButtlonDalej3Enabled();
					lblErrorPanel3.setText("");
				}
			}
		});
		
		textFieldKatWydatki1 = new JTextField();
		textFieldKatWydatki1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatWydatki1.getText().toString();
				String value = textFieldKatWydatki1.getText().toString();
				addWydatkiToMap(key, value);
			}
		});
		textFieldKatWydatki1.setEnabled(false);
		textFieldKatWydatki1.setColumns(10);

		checkBoxKatWydatki2 = new JCheckBox("Kategoria 2:");
		checkBoxKatWydatki2.setEnabled(false);
		checkBoxKatWydatki2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatWydatki2.setEnabled(true);
					btnDalej3.setEnabled(false);
					checkBoxKatWydatki3.setEnabled(true);
					numKatWydatki++;
					if (lblErrorPanel3.getText().isEmpty() && wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
						lblErrorPanel3.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatWydatki2.setEnabled(false);
					textFieldKatWydatki2.setText("");
					wydatkiKatFromTextFieldMap.remove("Kategoria 2:");
					numKatWydatki--;
					if(checkBoxKatWydatki3.isEnabled()){
						checkBoxKatWydatki3.setEnabled(false);
						checkBoxKatWydatki3.setSelected(false);
						wydatkiKatFromTextFieldMap.remove("Kategoria 3:");
					}
					if(checkBoxKatWydatki4.isEnabled()){
						checkBoxKatWydatki4.setEnabled(false);
						checkBoxKatWydatki4.setSelected(false);
						wydatkiKatFromTextFieldMap.remove("Kategoria 4:");
					}
					if(checkBoxKatWydatki5.isEnabled()){
						checkBoxKatWydatki5.setEnabled(false);
						checkBoxKatWydatki5.setSelected(false);
						wydatkiKatFromTextFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});


		textFieldKatWydatki2 = new JTextField();
		textFieldKatWydatki2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatWydatki2.getText().toString();
				String value = textFieldKatWydatki2.getText().toString();
				addWydatkiToMap(key, value);
			}
		});
		textFieldKatWydatki2.setEnabled(false);
		textFieldKatWydatki2.setColumns(10);

		checkBoxKatWydatki3 = new JCheckBox("Kategoria 3:");
		checkBoxKatWydatki3.setEnabled(false);
		checkBoxKatWydatki3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatWydatki3.setEnabled(true);
					btnDalej3.setEnabled(false);
					checkBoxKatWydatki4.setEnabled(true);
					numKatWydatki++;
					if (lblErrorPanel3.getText().isEmpty() && wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
						lblErrorPanel3.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatWydatki3.setEnabled(false);
					textFieldKatWydatki3.setText("");
					wydatkiKatFromTextFieldMap.remove("Kategoria 3:");
					numKatWydatki--;
					if(checkBoxKatWydatki4.isEnabled()){
						checkBoxKatWydatki4.setEnabled(false);
						checkBoxKatWydatki4.setSelected(false);
						wydatkiKatFromTextFieldMap.remove("Kategoria 4:");
					}
					if(checkBoxKatWydatki5.isEnabled()){
						checkBoxKatWydatki5.setEnabled(false);
						checkBoxKatWydatki5.setSelected(false);
						wydatkiKatFromTextFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});

		textFieldKatWydatki3 = new JTextField();
		textFieldKatWydatki3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatWydatki3.getText().toString();
				String value = textFieldKatWydatki3.getText().toString();
				addWydatkiToMap(key, value);
			}
		});
		textFieldKatWydatki3.setEnabled(false);
		textFieldKatWydatki3.setColumns(10);

		checkBoxKatWydatki4 = new JCheckBox("Kategoria 4:");
		checkBoxKatWydatki4.setEnabled(false);
		checkBoxKatWydatki4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatWydatki4.setEnabled(true);
					btnDalej3.setEnabled(false);
					checkBoxKatWydatki5.setEnabled(true);
					numKatWydatki++;
					if (lblErrorPanel3.getText().isEmpty() && wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
						lblErrorPanel3.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatWydatki4.setEnabled(false);
					textFieldKatWydatki4.setText("");
					wydatkiKatFromTextFieldMap.remove("Kategoria 4:");
					numKatWydatki--;
					if(checkBoxKatWydatki5.isEnabled()){
						checkBoxKatWydatki5.setEnabled(false);
						checkBoxKatWydatki5.setSelected(false);
						wydatkiKatFromTextFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});

		textFieldKatWydatki4 = new JTextField();
		textFieldKatWydatki4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatWydatki4.getText().toString();
				String value = textFieldKatWydatki4.getText().toString();
				addWydatkiToMap(key, value);
			}
		});
		textFieldKatWydatki4.setEnabled(false);
		textFieldKatWydatki4.setColumns(10);

		checkBoxKatWydatki5 = new JCheckBox("Kategoria 5:");
		checkBoxKatWydatki5.setEnabled(false);
		checkBoxKatWydatki5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatWydatki5.setEnabled(true);
					btnDalej3.setEnabled(false);
					numKatWydatki++;
					if (lblErrorPanel3.getText().isEmpty() && wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
						lblErrorPanel3.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatWydatki5.setEnabled(false);
					textFieldKatWydatki5.setText("");
					wydatkiKatFromTextFieldMap.remove("Kategoria 5:");
					numKatWydatki--;
				}
				
			}
		});

		textFieldKatWydatki5 = new JTextField();
		textFieldKatWydatki5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatWydatki5.getText().toString();
				String value = textFieldKatWydatki5.getText().toString();
				addWydatkiToMap(key, value);
			}
		});
		textFieldKatWydatki5.setEnabled(false);
		textFieldKatWydatki5.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 266, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup().addComponent(checkBoxKatWydatki1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldKatWydatki1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(checkBoxKatWydatki2)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldKatWydatki2,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(checkBoxKatWydatki3)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldKatWydatki3,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(checkBoxKatWydatki4)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldKatWydatki4,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(checkBoxKatWydatki5)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(textFieldKatWydatki5, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 200, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(checkBoxKatWydatki1)
								.addComponent(textFieldKatWydatki1, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(checkBoxKatWydatki2)
								.addComponent(textFieldKatWydatki2, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldKatWydatki3, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkBoxKatWydatki3))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(checkBoxKatWydatki4)
								.addComponent(textFieldKatWydatki4, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(checkBoxKatWydatki5)
								.addComponent(textFieldKatWydatki5, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		
		
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
					.addContainerGap(193, Short.MAX_VALUE)
					.addComponent(panelButton3, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel3.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel3.createSequentialGroup()
							.addComponent(panelZakupy, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
								.addComponent(lblErrorPanel3)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblWydatki))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel3.setVerticalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
					.addGap(20)
					.addComponent(lblWydatki)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE)
						.addComponent(panelZakupy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel3.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblErrorPanel3)))
					.addGap(36)
					.addComponent(panelButton3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel3.setLayout(gl_panel3);

		panel4 = new JPanel();
		contentPanel.add(panel4, "name_19686198582858");

		panelButton4 = new JPanel();
		panelButton4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCofnij4 = new JButton("Cofnij");
		btnCofnij4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					panel4.setVisible(false);
				}
			}
		});
		btnCofnij4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
			}
		});
		btnCofnij4.setPreferredSize(new Dimension(80, 25));
		panelButton4.add(btnCofnij4);

		btnDalej4 = new JButton("Dalej");
		btnDalej4.setEnabled(false);
		btnDalej4.setPreferredSize(new Dimension(80, 25));
		panelButton4.add(btnDalej4);

		btnAnuluj4 = new JButton("Anuluj");
		btnAnuluj4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					anuluj();
				}
			}
		});
		btnAnuluj4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anuluj();
			}
		});
		btnAnuluj4.setPreferredSize(new Dimension(80, 25));
		panelButton4.add(btnAnuluj4);

		btnZakoncz4 = new JButton("Zako\u0144cz");
		btnZakoncz4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeDataToDatabase();
				dispose();
			}
		});
		btnZakoncz4.setEnabled(false);
		btnZakoncz4.setPreferredSize(new Dimension(80, 25));
		panelButton4.add(btnZakoncz4);

		lblOszczdnoci = new JLabel("OSZCZ\u0118DNO\u015ACI");

		panelOszczednosci = new JPanel();
		panelOszczednosci.setBounds(new Rectangle(20, 20, 20, 0));
		panelOszczednosci.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Wybierz oszcz\u0119dno\u015Bci:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		chckbxRemonty = new JCheckBox("Remonty");
		chckbxRemonty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxRemonty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxRemonty.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxMeble = new JCheckBox("Meble");
		chckbxMeble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxMeble.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxMeble.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxSprzetyElektroniczne = new JCheckBox("Sprz\u0119ty elektroniczne");
		chckbxSprzetyElektroniczne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxSprzetyElektroniczne.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxSprzetyElektroniczne.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxWakacje = new JCheckBox("Wakacje");
		chckbxWakacje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxWakacje.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxWakacje.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxPrezenty = new JCheckBox("Prezenty");
		chckbxPrezenty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxPrezenty.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxPrezenty.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxDlaDziecka = new JCheckBox("Dla dziecka");
		chckbxDlaDziecka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxDlaDziecka.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxDlaDziecka.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxSlub = new JCheckBox("\u015Alub");
		chckbxSlub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxSlub.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxSlub.getText().toString();
				addOszczednosciToList(text, e);
			}
		});

		chckbxSamochod = new JCheckBox("Samoch\u00F3d");
		chckbxSamochod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonZakonczEnabled();
			}
		});
		chckbxSamochod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String text = chckbxSamochod.getText().toString();
				addOszczednosciToList(text, e);
			}
		});
		
		GroupLayout gl_panelOszczednosci = new GroupLayout(panelOszczednosci);
		gl_panelOszczednosci.setHorizontalGroup(gl_panelOszczednosci.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOszczednosci.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOszczednosci.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxRemonty).addComponent(chckbxMeble)
								.addComponent(chckbxSprzetyElektroniczne).addComponent(chckbxWakacje)
								.addComponent(chckbxPrezenty).addComponent(chckbxDlaDziecka).addComponent(chckbxSlub)
								.addComponent(chckbxSamochod))
						.addContainerGap(15, Short.MAX_VALUE)));
		gl_panelOszczednosci.setVerticalGroup(gl_panelOszczednosci.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOszczednosci.createSequentialGroup().addContainerGap().addComponent(chckbxRemonty)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxMeble)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSprzetyElektroniczne)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxWakacje)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxPrezenty)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxDlaDziecka)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSlub)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSamochod)
						.addContainerGap(143, Short.MAX_VALUE)));
		panelOszczednosci.setLayout(gl_panelOszczednosci);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Wpisz w\u0142asne kategorie", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		checkBoxKatOszczednosci1 = new JCheckBox("Kategoria 1:");
		checkBoxKatOszczednosci1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatOszczednosci1.setEnabled(true);
					btnZakoncz4.setEnabled(false);
					checkBoxKatOszczednosci2.setEnabled(true);
					numKatOszczednosci++;
					if (lblErrorPanel4.getText().isEmpty() && oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
						lblErrorPanel4.setText(errorMessageKategorie);
					}
					setButtonZakonczEnabled();
				}else{
					oszczednosciFromTxtFieldMap.clear();
					textFieldKatOszczednosci1.setEnabled(false);
					textFieldKatOszczednosci1.setText("");
					numKatOszczednosci--;
					if(checkBoxKatOszczednosci2.isEnabled()){
						checkBoxKatOszczednosci2.setEnabled(false);
						checkBoxKatOszczednosci2.setSelected(false);
					}
					if(checkBoxKatOszczednosci3.isEnabled()){
						checkBoxKatOszczednosci3.setEnabled(false);
						checkBoxKatOszczednosci3.setSelected(false);
					}
					if(checkBoxKatOszczednosci4.isEnabled()){
						checkBoxKatOszczednosci4.setEnabled(false);
						checkBoxKatOszczednosci4.setSelected(false);
					}
					if(checkBoxKatOszczednosci5.isEnabled()){
						checkBoxKatOszczednosci5.setEnabled(false);
						checkBoxKatOszczednosci5.setSelected(false);
					}
					lblErrorPanel4.setText("");
					setButtonZakonczEnabled();
				}
			}
		});

		textFieldKatOszczednosci1 = new JTextField();
		textFieldKatOszczednosci1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatOszczednosci1.getText().toString();
				String value = textFieldKatOszczednosci1.getText().toString();
				addOszczednosciToMap(key, value);
			}
		});
		textFieldKatOszczednosci1.setEnabled(false);
		textFieldKatOszczednosci1.setColumns(10);

		checkBoxKatOszczednosci2 = new JCheckBox("Kategoria 2:");
		checkBoxKatOszczednosci2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatOszczednosci2.setEnabled(true);
					btnZakoncz4.setEnabled(false);
					checkBoxKatOszczednosci3.setEnabled(true);
					numKatOszczednosci++;
					if (lblErrorPanel4.getText().isEmpty() && oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
						lblErrorPanel4.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatOszczednosci2.setEnabled(false);
					textFieldKatOszczednosci2.setText("");
					oszczednosciFromTxtFieldMap.remove("Kategoria 2:");
					numKatOszczednosci--;
					if(checkBoxKatOszczednosci3.isEnabled()){
						checkBoxKatOszczednosci3.setEnabled(false);
						checkBoxKatOszczednosci3.setSelected(false);
						oszczednosciFromTxtFieldMap.remove("Kategoria 3:");
					}
					if(checkBoxKatOszczednosci4.isEnabled()){
						checkBoxKatOszczednosci4.setEnabled(false);
						checkBoxKatOszczednosci4.setSelected(false);
						oszczednosciFromTxtFieldMap.remove("Kategoria 4:");
					}
					if(checkBoxKatOszczednosci5.isEnabled()){
						checkBoxKatOszczednosci5.setEnabled(false);
						checkBoxKatOszczednosci5.setSelected(false);
						oszczednosciFromTxtFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});

		checkBoxKatOszczednosci2.setEnabled(false);

		textFieldKatOszczednosci2 = new JTextField();
		textFieldKatOszczednosci2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatOszczednosci2.getText().toString();
				String value = textFieldKatOszczednosci2.getText().toString();
				addOszczednosciToMap(key, value);
			}
		});
		textFieldKatOszczednosci2.setEnabled(false);
		textFieldKatOszczednosci2.setColumns(10);

		checkBoxKatOszczednosci3 = new JCheckBox("Kategoria 3:");
		checkBoxKatOszczednosci3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatOszczednosci3.setEnabled(true);
					btnZakoncz4.setEnabled(false);
					checkBoxKatOszczednosci4.setEnabled(true);
					numKatOszczednosci++;
					if (lblErrorPanel4.getText().isEmpty() && oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
						lblErrorPanel4.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatOszczednosci3.setEnabled(false);
					textFieldKatOszczednosci3.setText("");
					oszczednosciFromTxtFieldMap.remove("Kategoria 3:");
					numKatOszczednosci--;
					if(checkBoxKatOszczednosci4.isEnabled()){
						checkBoxKatOszczednosci4.setEnabled(false);
						checkBoxKatOszczednosci4.setSelected(false);
						oszczednosciFromTxtFieldMap.remove("Kategoria 4:");
					}
					if(checkBoxKatOszczednosci5.isEnabled()){
						checkBoxKatOszczednosci5.setEnabled(false);
						checkBoxKatOszczednosci5.setSelected(false);
						oszczednosciFromTxtFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});
		checkBoxKatOszczednosci3.setEnabled(false);

		textFieldKatOszczednosci3 = new JTextField();
		textFieldKatOszczednosci3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatOszczednosci3.getText().toString();
				String value = textFieldKatOszczednosci3.getText().toString();
				addOszczednosciToMap(key, value);
			}
		});
		textFieldKatOszczednosci3.setEnabled(false);
		textFieldKatOszczednosci3.setColumns(10);

		checkBoxKatOszczednosci4 = new JCheckBox("Kategoria 4:");
		checkBoxKatOszczednosci4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatOszczednosci4.setEnabled(true);
					btnZakoncz4.setEnabled(false);
					checkBoxKatOszczednosci5.setEnabled(true);
					numKatOszczednosci++;
					if (lblErrorPanel4.getText().isEmpty() && oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
						lblErrorPanel4.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatOszczednosci4.setEnabled(false);
					textFieldKatOszczednosci4.setText("");
					oszczednosciFromTxtFieldMap.remove("Kategoria 4:");
					numKatOszczednosci--;
					if(checkBoxKatOszczednosci5.isEnabled()){
						checkBoxKatOszczednosci5.setEnabled(false);
						checkBoxKatOszczednosci5.setSelected(false);
						oszczednosciFromTxtFieldMap.remove("Kategoria 5:");
					}
				}
			}
		});
		checkBoxKatOszczednosci4.setEnabled(false);

		textFieldKatOszczednosci4 = new JTextField();
		textFieldKatOszczednosci4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatOszczednosci4.getText().toString();
				String value = textFieldKatOszczednosci4.getText().toString();
				addOszczednosciToMap(key, value);
			}
		});
		textFieldKatOszczednosci4.setEnabled(false);
		textFieldKatOszczednosci4.setColumns(10);

		checkBoxKatOszczednosci5 = new JCheckBox("Kategoria 5:");
		checkBoxKatOszczednosci5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					textFieldKatOszczednosci5.setEnabled(true);
					btnZakoncz4.setEnabled(false);
					checkBoxKatOszczednosci5.setEnabled(true);
					numKatOszczednosci++;
					if (lblErrorPanel4.getText().isEmpty() && oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
						lblErrorPanel4.setText(errorMessageKategorie);
					}
				}else{
					textFieldKatOszczednosci5.setEnabled(false);
					textFieldKatOszczednosci5.setText("");
					oszczednosciFromTxtFieldMap.remove("Kategoria 5:");
					numKatOszczednosci--;
				}
			}
		});
		checkBoxKatOszczednosci5.setEnabled(false);

		textFieldKatOszczednosci5 = new JTextField();
		textFieldKatOszczednosci5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = checkBoxKatOszczednosci5.getText().toString();
				String value = textFieldKatOszczednosci5.getText().toString();
				addOszczednosciToMap(key, value);
			}
		});
		textFieldKatOszczednosci5.setEnabled(false);
		textFieldKatOszczednosci5.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 266, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(checkBoxKatOszczednosci1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(
												textFieldKatOszczednosci1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(checkBoxKatOszczednosci2)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(
												textFieldKatOszczednosci2, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(checkBoxKatOszczednosci3)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(
												textFieldKatOszczednosci3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(checkBoxKatOszczednosci4)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(
												textFieldKatOszczednosci4, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(checkBoxKatOszczednosci5)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(textFieldKatOszczednosci5, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 200, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxKatOszczednosci1).addComponent(textFieldKatOszczednosci1,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxKatOszczednosci2).addComponent(textFieldKatOszczednosci2,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldKatOszczednosci3, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkBoxKatOszczednosci3))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxKatOszczednosci4).addComponent(textFieldKatOszczednosci4,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBoxKatOszczednosci5).addComponent(textFieldKatOszczednosci5,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		
		lblErrorPanel4 = new JLabel("");
		lblErrorPanel4.setForeground(Color.RED);
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
					.addContainerGap(193, Short.MAX_VALUE)
					.addComponent(panelButton4, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel4.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel4.createSequentialGroup()
							.addComponent(panelOszczednosci, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
								.addComponent(lblErrorPanel4)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblOszczdnoci))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel4.setVerticalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
					.addGap(26)
					.addComponent(lblOszczdnoci)
					.addGap(18)
					.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelOszczednosci, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel4.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblErrorPanel4)))
					.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
					.addComponent(panelButton4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel4.setLayout(gl_panel4);
	}

	private void createUsers() {
		String s = textFieldHowManyUsersToDisplay.getText();
		int num = 0;
		JTextField jTextFields[];
		int repetiviveUniqueNames = 0;
		try {
			panel1_Users.removeAll();
			num = Integer.parseInt(s);
			lblErrorMessageUserNumber.setText("");
			JLabel jLabels[] = new JLabel[num];
			jTextFields = new JTextField[num];
			int displayUserNumber = 1;
			userNumber = 0;

			for (int i = 0; i < jLabels.length; i++) {
				jLabels[i] = new JLabel("Uzytkownik " + displayUserNumber + ":");
				usersMap.clear();
				jTextFields[i] = new JTextField(10);
				JTextField currentField = new JTextField(10);
				jTextFields[i] = currentField;
				String currentLabel = jLabels[i].getText();
				panel1_Users.add(jLabels[i]);
				panel1_Users.add(jTextFields[i]);
				displayUserNumber++;
				userNumber++;

				jTextFields[i].addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						String key = currentLabel;
						String value = currentField.getText().toString();
						boolean currentFieldIsEmptyOrHasOnlySpaces = textFieldValidator.checkIfTextFieldIsEmpty(currentField);

						int userNameIsUnique = checkIfUserNameIsUniqueName(currentField.getText());
						if(userNameIsUnique != 1){
							JOptionPane.showMessageDialog(null, "Nazwa unytkownika musi byn unikalna");
						}
						
						if(userNameIsUnique == 1 && currentFieldIsEmptyOrHasOnlySpaces == false){
							usersMap.put(key, value);
						}else{
							btnDalej1.setEnabled(false);
						}
						
						 //usersMap.put(key, value);
						// System.out.println(usersMap);
						if (usersMap.size() == userNumber && currentFieldIsEmptyOrHasOnlySpaces == false) {
							btnDalej1.setEnabled(true);
						} else {
							btnDalej1.setEnabled(false);
						}
					}

				});
			}

			if (num > maxNumberOfUsersInBudget) {
				lblErrorMessageUserNumber.setText("Budzet mone mien maksymalnie 8 uzytkowniknw");
				panel1_Users.removeAll();
				btnDalej1.setEnabled(false);
			}

		} catch (NumberFormatException nfe) {
			lblErrorMessageUserNumber.setText("Wpisz liczbn cankowitn");
			panel1_Users.removeAll();
			btnDalej1.setEnabled(false);
		} catch (NegativeArraySizeException excpt) {
			lblErrorMessageUserNumber.setText("Wpisz liczbn cankowitn winkszn od zera");
			panel1_Users.removeAll();
			btnDalej1.setEnabled(false);
		}

		panel1_Users.validate();
		panel1_Users.repaint();
	}

	private void anuluj() {
		CreateBudgetOptions.this.dispose();
	}

	private void setButtlonDalej2Enabled() {
		if (chckbxCzynsz.isSelected() || chckbxSplataKartyKredytowej.isSelected() || chckbxPrad.isSelected()
				|| chckbxInternet.isSelected() || chckbxFunduszRemontowy.isSelected() || chckbxKredyt.isSelected()
				|| chckbxPozyczka.isSelected() || chckbxOplatyZaTelefon.isSelected()
				|| oplatyKatFromTxtFieldMap.size() > 0) {
			if (numKatOplaty == oplatyKatFromTxtFieldMap.size()) {
				btnDalej2.setEnabled(true);
			} else {
				btnDalej2.setEnabled(false);
			}
		} else
			btnDalej2.setEnabled(false);
	}
	
	private void setButtlonDalej3Enabled() {
		if (chckbxZakupy.isSelected() || chckbxJedzenie.isSelected() || chckbxChemia.isSelected()
				|| chckbxPaliwo.isSelected() || chckbxDojazdy.isSelected() || chckbxZwierzeta.isSelected()
				|| chckbxFastFood.isSelected() || chckbxSport.isSelected() || chckbxKulturarozrywka.isSelected()
				|| chckbxSprzety.isSelected() || chckbxDzieci.isSelected() || chckbxOplatyZaSzkole.isSelected()
				|| chckbxPodrecznikizeszyty.isSelected() || chckbxLeki.isSelected()
				|| wydatkiKatFromTextFieldMap.size() > 0) {
			if (numKatWydatki == wydatkiKatFromTextFieldMap.size()) {
				btnDalej3.setEnabled(true);
			} else {
				btnDalej3.setEnabled(false);
			}
		} else
			btnDalej3.setEnabled(false);
	}
	
	private void addWydatkiToList(String text, ItemEvent e){
		if(e.getStateChange() == ItemEvent.SELECTED){
			wydatkiList.add(text);
		}
		if(e.getStateChange() == ItemEvent.DESELECTED && wydatkiList.contains(text)){
			wydatkiList.remove(text);
		}
	}
	
	private void addWydatkiToMap(String key, String value){
		wydatkiKatFromTextFieldMap.put(key, value);
		lblErrorPanel3.setText("");
		if (wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
			btnDalej3.setEnabled(false);
			if (lblErrorPanel3.getText().isEmpty() && wydatkiKatFromTextFieldMap.size() != numKatWydatki) {
				lblErrorPanel3.setText(errorMessageKategorie);
			}
		} else {
			btnDalej3.setEnabled(true);
		}
	}
	
	private void addOszczednosciToMap(String key, String value){
		oszczednosciFromTxtFieldMap.put(key, value);
		lblErrorPanel4.setText("");
		if (oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
			btnZakoncz4.setEnabled(false);
			if (lblErrorPanel4.getText().isEmpty() && oszczednosciFromTxtFieldMap.size() != numKatOszczednosci) {
				lblErrorPanel4.setText(errorMessageKategorie);
			}
		} else {
			btnZakoncz4.setEnabled(true);
		}
	}
	
	private void addOszczednosciToList(String text, ItemEvent e){
		if(e.getStateChange() == ItemEvent.SELECTED){
			oszczednosciList.add(text);
		}
		if(e.getStateChange() == ItemEvent.DESELECTED && oszczednosciList.contains(text)){
			oszczednosciList.remove(text);
		}
	}
	
	private void setButtonZakonczEnabled() {
		if (chckbxRemonty.isSelected() || chckbxMeble.isSelected() || chckbxSprzetyElektroniczne.isSelected()
				|| chckbxWakacje.isSelected() || chckbxPrezenty.isSelected() || chckbxDlaDziecka.isSelected()
				|| chckbxSlub.isSelected() || chckbxSamochod.isSelected() 
				|| oszczednosciFromTxtFieldMap.size() > 0) {
			if (numKatOszczednosci == oszczednosciFromTxtFieldMap.size()) {
				btnZakoncz4.setEnabled(true);
			} else {
				btnZakoncz4.setEnabled(false);
			}
		} else
			btnZakoncz4.setEnabled(false);
	}
	
	private boolean checkIfBudgetNameIsUniqueName(){
		budgetName = textFieldBudgetName.getText();
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		for(Entry<Integer, String> entry: budgetIdNameMap.entrySet()){
			if(budgetName.equals(entry.getValue())){
				return false;
			}
		}
		return true;
	}
	////////
	private int checkIfUserNameIsUniqueName(String userName){
		HashMap<Integer, String> userIdNameMap = databaseReader.readUsersFromDatabasetoHashMap();
		for(Entry<Integer, String> entry: userIdNameMap.entrySet()){
			if(userName.equals(entry.getValue())){
			//	return false;
				return 0;
			}
		}
		return 1;
		//return true;
	}
	
	private void writeDataToDatabase(){
		budgetName = textFieldBudgetName.getText();
		databaseWriter.writeBudgetNameToDatabase(budgetName);
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		
		for(Entry<Integer, String> entry: budgetIdNameMap.entrySet()){
			if(entry.getValue().equals(budgetName)) idBudget = entry.getKey();
		}
		
		databaseWriter.writeCategoryMapToDatabase(usersMap,   idBudget, "Users");
		databaseWriter.writeCategoryListTodatabase(oplatyList, idBudget, "Dues_category");
		databaseWriter.writeCategoryMapToDatabase(oplatyKatFromTxtFieldMap, idBudget, "Dues_category");
		databaseWriter.writeCategoryListTodatabase(wydatkiList, idBudget, "Expenditure_category");
		databaseWriter.writeCategoryMapToDatabase(wydatkiKatFromTextFieldMap,  idBudget, "Expenditure_category");
		databaseWriter.writeCategoryListTodatabase(oszczednosciList, idBudget, "Savings_category");
		databaseWriter.writeCategoryMapToDatabase(oszczednosciFromTxtFieldMap,  idBudget, "Savings_category");
		databaseWriter.writeDayOfBeginingNewBudgetMonthToDatabase(dayOfNewBudgetMonth, idBudget);
	}



	public int getMaxNumberOfUsers(){
		return maxNumberOfUsersInBudget;
	}
}
