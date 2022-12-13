package proyectoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Permite realizar la conexion a la base de datos (creada con anterioridad en MySQL).
 * Esta clase tiene un constructor que conecta con la base de datos y un metodo que devuelve la conexion de la base de datos.
 * 
 */

public class Bd {

	/**
	 * Atributo nombre de la maquina
	 */
	private String  url = "jdbc:mysql://localhost:3306/DATOS";
	
	/**
	 * Atributo nombre del usuario de la base de datos
	 */
	String  usuario = "luis";
	
	/**
	 * Atributo contraseña del usuario de la base de datos
	 */
	String  password = "Vilamos2005@";
	
	
    /**
	 * Atributo con la conexion de la base de datos
	 */
    private static Connection conexion = null;
    
    /**
     * Constructor  sin parametros.
     * Carga el driver proporcionado y crea la conexion a la base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    
    public Bd(){
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no encontrado " + e.getMessage());
			System.exit(-1);
		}
		try {
			conexion = DriverManager.getConnection(
					   url, usuario, password);
		} catch (SQLException e) {
			JOptionPane panel = new JOptionPane("Error base de datos");
		}
    }
	
    /**
     * Devuelve la conexion
     * @return Connection
     * */
	public static Connection getConexion(){
		return conexion;
	}
}
