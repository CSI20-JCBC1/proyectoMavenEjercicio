package proyectoInicial.proyectoMavenEjercicio;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.conexionPostgre;
import modelo.consultasPostgre;
import modelo.dtoAlumno;
import util.variablesConexionPostgre;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final String HOST = variablesConexionPostgre.getHost();
		final String PORT = variablesConexionPostgre.getPort();
		final String DB = variablesConexionPostgre.getDb();
		final String USER = variablesConexionPostgre.getUser();
		final String PASS = variablesConexionPostgre.getPass();
		ArrayList<dtoAlumno> listAlumnos = new ArrayList();

		
		/*Se crea una instancia de la clase en la que estamos para poder generar la conexión a PostgreSQL
		*utilizando el método generaConexion
		*/
		System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada generaConexion");
		//si no se definen los métodos como estáticos se necesita instanciar la clase.
		conexionPostgre conexionPostgresql = new conexionPostgre();
		Connection conexionGenerada = conexionPostgresql.generaConexion(HOST,PORT,DB,USER,PASS);
		
		if(conexionGenerada != null) {
			//Llamar al método que ejecuta la consulta de insert
			//Cambiar el id para probar
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada insertNuevoAlumno");
			//Si los métodos se crean como estáticos no es necesario instanciar una clase.
			consultasPostgre.insertNuevoAlumno("INSERT INTO \"proyectoEclipse\".\"alumnos\" (nombre,apellidos,email)"
					+ "VALUES('Pedro','Fernández','pf@altair.es')", conexionGenerada);
			
			System.out.println();
						
			//Llamar al método que ejecuta la consulta de select
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada selectAllAlumnos");
			listAlumnos = consultasPostgre.selectAllAlumnos(conexionGenerada);
			int i = listAlumnos.size();
			for(dtoAlumno alumno: listAlumnos) {
				System.out.println(alumno.getId_alumno() + " " +
				alumno.getNombre() + " " + alumno.getApellidos() + " " + alumno.getEmail());
			}
			
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Número alumnos: "+i);
			
			System.out.println();
			
			//Llamo al metodo que ejecuta la consulta de delete
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Llamada deleteAlumnos");
			// Pedimos un id al usuario para borrar
		    System.out.print("Introduce el id del alumno a eliminar: ");
		    Scanner scan = new Scanner(System.in);
		    int id = scan.nextInt();
			consultasPostgre.deleteAlumnos("DELETE FROM \"proyectoEclipse\".\"alumnos\" WHERE id_alumno = '"+id+"'", conexionGenerada);
			
			System.out.println();
			
			//Mostramos otra vez los alumnos que hay ahora
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada selectAllAlumnos");
			listAlumnos = consultasPostgre.selectAllAlumnos(conexionGenerada);
			i=listAlumnos.size();
			for(dtoAlumno alumno: listAlumnos) {
				System.out.println(alumno.getId_alumno() + " " +
				alumno.getNombre() + " " + alumno.getApellidos() + " " + alumno.getEmail());
				
			}
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Número alumnos: "+i);
			
			System.out.println();
			
			//Llama al metodo que ejecuta el update
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Llamada updateAlumnos");
			// Pedimos un id al usuario para borrar
		    System.out.print("Introduce el id del alumno a actualizar: ");
		    id=scan.nextInt();
			consultasPostgre.updateAlumnos("UPDATE \"proyectoEclipse\".\"alumnos\" SET nombre = 'Juan Carlos', apellidos = 'Bada', email = 'jcbc@gmail.com' WHERE id_alumno = '"+id+"'", conexionGenerada);
			
			System.out.println();
			
			//Mostramos otra vez los alumnos que hay ahora
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Lamada selectAllAlumnos");
			listAlumnos = consultasPostgre.selectAllAlumnos(conexionGenerada);
			i=listAlumnos.size();
			for(dtoAlumno alumno: listAlumnos) {
				System.out.println(alumno.getId_alumno() + " " +
				alumno.getNombre() + " " + alumno.getApellidos() + " " + alumno.getEmail());
				
			}
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Número alumnos: "+i);
			
			System.out.println();
			
			//Llama al metodo que ejecuta el create
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Llamada createTable");
			consultasPostgre.createTable("CREATE TABLE IF NOT EXISTS \"proyectoEclipse\".\"profesores\" (id_profesor CHARACTER VARYING NOT NULL)", conexionGenerada);
			
			System.out.println();
			
			System.out.println("[INFORMACIÓN-controladorPortgreSQL-main] Llamada a cerrarConexion");
			conexionPostgresql.cerrarConexion(conexionGenerada);
		}		
    }
}
