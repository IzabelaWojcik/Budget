package learning.budget;

import java.sql.Connection;

public interface IDatabaseConnection {
	public Connection connectionWithDB();
}
