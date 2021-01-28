

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	private static final String URL = "jdbc:sqlite:db/BDEjercicio1.db";
	private static final String DRIVER = "org.sqlite.JDBC";
	
	private static Connection conexion;
	
	private ConexionDB() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conexion = DriverManager.getConnection(URL);
	}
	
	public static Connection getConexion() throws ClassNotFoundException, SQLException {
		if (conexion == null) {
			new ConexionDB();
		}
		
		return conexion;
	}
	
	public static void cerrarConexion() throws SQLException {
		if (conexion != null)
			conexion.close();
	}

}
