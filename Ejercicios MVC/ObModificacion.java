package proyectoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ObModificacion {

	public ObModificacion(int pos, ObRegistro registro){
		
		try{
			//Creo el Statement con la conexion realizada desde la Bd creada
			Statement stmt = Bd.getConexion().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			//Ejecuto una consulta sql y la almaceno
			ResultSet resultadoConsulta = stmt.executeQuery("SELECT * FROM DatosPersonales");
			
			//Realizo un bucle while y voy directamente a la posicion proporcionada
			resultadoConsulta.absolute(pos);
			resultadoConsulta.updateString("DNI", registro.dni);
			resultadoConsulta.updateString("Nombre", registro.nombre);
			resultadoConsulta.updateString("Apellido", registro.apellido);
			resultadoConsulta.updateInt("Edad", registro.edad);
			resultadoConsulta.updateRow();

			resultadoConsulta.close();
			stmt.close();
		}catch(SQLException e){
			System.out.println("Error de base de datos " + e.getMessage());
		}
		
	}
}
