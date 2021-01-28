


import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) {
		
		try {
			
			NotasAlumnosDAO dao = NotasAlumnosDAO.getDao();
			System.out.println("Antes: ");
			System.out.println(dao.mostrarNotas());
			dao.restarNotaPorFaltas();
			System.out.println("Despues: ");
			System.out.println(dao.mostrarNotas());
			ConexionDB.cerrarConexion();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
