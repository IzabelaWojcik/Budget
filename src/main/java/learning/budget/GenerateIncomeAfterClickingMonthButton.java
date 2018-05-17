package learning.budget;

import static learning.budget.DataFormatter.setAmountFormat;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.util.Pair;

public class GenerateIncomeAfterClickingMonthButton implements ActionListener{
	private JPanel panelIncome;
	private JPanel panelUser;
	private JLabel labelSum;
	private List<Pair<String, Double>> listOfUsersAndTheirsamountOfIncomes;
	
	public GenerateIncomeAfterClickingMonthButton(JPanel jpanelIncome, JPanel jpanelUser, JLabel jlabelSum, List<Pair<String, Double>> userNameIncomeAmountList){
		panelIncome = jpanelIncome;
		panelUser = jpanelUser;
		labelSum = jlabelSum;
		listOfUsersAndTheirsamountOfIncomes = userNameIncomeAmountList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double sum = 0;
		List<Container> components = Arrays.asList(panelIncome, panelUser);
		components.stream().forEach(c -> {c.removeAll();});
		
		
		
		for (Pair<String, Double> pair: listOfUsersAndTheirsamountOfIncomes) {
			JLabel jLabelUsers = new JLabel(pair.getKey());
			JLabel jLabelIncomeAmount = new JLabel(String.valueOf(pair.getValue()));

			panelUser.add(jLabelUsers);
			panelIncome.add(jLabelIncomeAmount);
			
			sum += pair.getValue();
		}
		labelSum.setText(setAmountFormat(sum));
		components.stream().forEach(c -> {c.revalidate(); c.repaint();});
	}
}
