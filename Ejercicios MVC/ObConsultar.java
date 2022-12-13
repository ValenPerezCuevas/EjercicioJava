package proyectoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ObConsultar {
	
	ObRegistro datosPersona;
	int pos;
	
	public ObConsultar(String dni){
		try{
			//Creo el Statement con la conexion realizada desde la Bd creada
			Statement stmt = Bd.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			//Ejecuto una consulta sql y la almaceno
			ResultSet resultadoConsulta = stmt.executeQuery("SELECT * FROM DatosPersonales");
			
			while(resultadoConsulta.next()){
				if(resultadoConsulta.getString("DNI").equals(dni)){
					pos = resultadoConsulta.getRow();
					String nombre = resultadoConsulta.getString("Nombre");
					String apellido = resultadoConsulta.getString("Apellido");
					int edad = resultadoConsulta.getInt("Edad");
					datosPersona = new ObRegistro(dni, nombre, apellido, edad);
					break;
				}
			}
			//Cierro la consulta y el Statement
			resultadoConsulta.close();
			stmt.close();
			
		}catch(SQLException e){
			JOptionPane panel = new JOptionPane("Error base de datos " + e.getMessage());
		}
	}
	
	public int posicionEncontrada(){
		return pos;
	}
	
	public ObRegistro dameDatos(){
		return datosPersona;
	}
}
