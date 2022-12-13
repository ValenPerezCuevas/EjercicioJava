package proyectoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ObBorrado {

	public ObBorrado(int posBuscada){
		
		try {
			//Creo el Statement con la conexion realizada desde la Bd creada
			Statement stmt = Bd.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			//Ejecuto una consulta sql y la almaceno
			ResultSet resultadoConsulta = stmt.executeQuery("SELECT * FROM DatosPersonales");
			
			//Nos situamos en la posicion del registro que queremos eliminar
			resultadoConsulta.absolute(posBuscada);
			resultadoConsulta.deleteRow();
			
			//Cierro la consulta y el statement
			resultadoConsulta.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane panel = new JOptionPane("Error base de datos " + e.getMessage());
		}
	}
}
