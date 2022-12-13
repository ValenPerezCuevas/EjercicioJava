package proyectoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ObInsercion {

	public ObInsercion(ObRegistro registro){
		
		try{
			//Creo el Statement con la conexion realizada desde la Bd creada
			Statement stmt = Bd.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			//Ejecuto una consulta sql
			ResultSet resultadoConsulta = stmt.executeQuery("SELECT * FROM DatosPersonales");
			
			//Me muevo hasta la ultima fila y escribo los datos del registro proporcionados
			resultadoConsulta.moveToInsertRow();
			resultadoConsulta.updateString("DNI", registro.getDni());
			resultadoConsulta.updateString("Nombre", registro.getNombre());
			resultadoConsulta.updateString("Apellido", registro.getApellido());
			resultadoConsulta.updateInt("Edad", registro.getEdad());
			resultadoConsulta.insertRow();
			
			//Cierro la consulta y el statement
			resultadoConsulta.close();
			stmt.close();
			
		}catch(SQLException e){
			JOptionPane panel = new JOptionPane("Error base de datos " + e.getSQLState());
		}
	}
}
