package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class consultasPostgre {

	/**
	 * selectAllAlumnos - Consulta sobre la tabla alumnos. SELECT * FROM
	 * \"proyectoEclipse\".\"alumnos\"
	 * 
	 * @author garfe
	 * @param Connection conexionGenerada
	 * @return ArrayList<dtoAlumno>
	 */
	public static ArrayList<dtoAlumno> selectAllAlumnos(Connection conexionGenerada) {

		System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Entra en selectAllAlumnos");
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<dtoAlumno> listAlumnos = new ArrayList();

		try {

			// Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			// Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".\"alumnos\"");

			// Llamada a la conversión a dtoAlumno
			listAlumnos = dtoADTO.resultsetAdtoAlumno(resultadoConsulta);
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Número alumnos: " + i);

			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Cierre declaración y resultado");
			resultadoConsulta.close();
			declaracionSQL.close();

		} catch (SQLException e) {

			System.out.println("[ERROR-conexionPostgresql-main] Error generando la declaracionSQL: " + e);
			return listAlumnos;

		}
		return listAlumnos;

	}

	
	public static void insertNuevoAlumno(String consulta, Connection conexionGenerada) {

		System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Entra en insertNuevoAlumno");
		Statement declaracionSQL = null;

		try {

			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(consulta);
			declaracionSQL.close();

			System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Cierre declaración.");

		} catch (SQLException e) {

			System.out.println("[ERROR-consultasPostgreSQL-insertNuevoAlumno] Error al insertar alumno: " + e);

		}

	}

	public static void deleteAlumnos(String consulta, Connection conexionGenerada) {

		System.out.println("[INFORMACIÓN-consultasPostgreSQL-deleteAlumnos] Entra en deleteAlumnos");
		Statement declaracionSQL = null;

		try {

			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.executeUpdate(consulta);
			declaracionSQL.close();

			System.out.println("[INFORMACIÓN-consultasPostgreSQL-deleteAlumno] Cierre declaración.");

		} catch (SQLException e) {

			System.out.println("[ERROR-consultasPostgreSQL-deleteAlumno] Error al borrar alumno: " + e);

		}
	}

	public static void updateAlumnos(String consulta, Connection conexionGenerada) {

		System.out.println("[INFORMACIÓN-consultasPostgreSQL-updateAlumnos] Entra en updateAlumnos");
		Statement declaracionSQL = null;

		try {

			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.executeUpdate(consulta);
			declaracionSQL.close();

			System.out.println("[INFORMACIÓN-consultasPostgreSQL-updateAlumno] Cierre declaración.");

		} catch (SQLException e) {

			System.out.println("[ERROR-consultasPostgreSQL-updateAlumno] Error al actualizar alumno: " + e);

		}

	}

	public static void createTable(String consulta, Connection conexionGenerada) {

		System.out.println("[INFORMACIÓN-consultasPostgreSQL-createTable] Entra en createTable");
		Statement declaracionSQL = null;

		try {

			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.executeUpdate(consulta);
			declaracionSQL.close();

			System.out.println("[INFORMACIÓN-consultasPostgreSQL-createTable] Cierre declaración.");

		} catch (SQLException e) {

			System.out.println("[ERROR-consultasPostgreSQL-createTable] Error al crear la tabla: " + e);

		}

	}

	
}
