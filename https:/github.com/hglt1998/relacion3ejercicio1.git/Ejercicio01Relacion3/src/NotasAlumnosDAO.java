import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NotasAlumnosDAO {

	private Connection conexion = null;
	private static NotasAlumnosDAO dao = null;

	private NotasAlumnosDAO() throws SQLException, ClassNotFoundException {
		this.conexion = ConexionDB.getConexion();

	}

	public static NotasAlumnosDAO getDao() throws ClassNotFoundException, SQLException {
		if (dao == null) {
			dao = new NotasAlumnosDAO();
		}

		return dao;
	}

	public void restarNotaPorFaltas() throws SQLException {
		String dni;
		ResultSet resultAlumnos;

		try (Statement sentenciaAlumnos = conexion.createStatement();
				Statement sentenciaActualizar = conexion.createStatement();) {

			resultAlumnos = sentenciaAlumnos.executeQuery("select * from alumnos where faltas > 20");

			while (resultAlumnos.next()) {
				dni = resultAlumnos.getString("dni");

				sentenciaActualizar
						.executeUpdate("update notas set nota = nota-1 where dni = '" + dni + "' and nota > 0");
			}

		}
	}

	public String mostrarNotas() throws SQLException {
		StringBuilder datos = new StringBuilder();
		ResultSet result;

		try (Statement sentencia = conexion.createStatement();) {
			result = sentencia.executeQuery("select * from notas;");

			while (result.next()) {
				datos.append("Alumno: " + result.getString("dni") + "\tAsignatura: " + result.getString("asig")
						+ "\t\tNota: " + result.getInt("nota") + "\n");
			}

		}

		return datos.toString();

	}

}
