package learning.budget;

import java.text.DecimalFormat;
import java.util.List;

public final class SumAmount {
	
	public static String sumAmount(List<Transaction> transactionList) {
		double sum = 0;
		for(Transaction t: transactionList) {
			sum += t.getAmount();
		}
		
		return DataFormatter.setAmountFormat(sum);
	}
}
