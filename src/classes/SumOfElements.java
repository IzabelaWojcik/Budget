package classes;
import java.text.DecimalFormat;

import javax.swing.JLabel;

public class SumOfElements {
	private static double sum = 0;
	private DataFormatter dataFormatter = new DataFormatter();
	
	public double sumElements(double element){
		sum += element;
		return sum;
	}
	public double sumElements(double sum, double element){
		sum += element;
		return sum;
	}
	
	public void showSumInLabel(JLabel label, Double sum){
		String formattedAmount = dataFormatter.setAmountFormat(sum);
		label.setText(formattedAmount);
	}
	
	public void setSumToZero(){
		sum = 0;
	}

}
