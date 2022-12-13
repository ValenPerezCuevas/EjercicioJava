package proyectoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class ObListar {
		
		public ObListar(DefaultTableModel dtm){
			try{
				//Creo el Statement con la conexion realizada desde la Bd creada
				Statement stmt = Bd.getConexion().createStatement();
				
				//Ejecuto una consulta sql y la almaceno
				ResultSet resultadoConsulta = stmt.executeQuery("SELECT * FROM DatosPersonales");
				
				//Realizo un bucle while y voy almacenando y escribiendo en pantalla los datos
				while(resultadoConsulta.next()){
					String dni = resultadoConsulta.getString("DNI");
					String nombre = resultadoConsulta.getString("Nombre");
					String apellido = resultadoConsulta.getString("Apellido");
					int edad = resultadoConsulta.getInt("Edad");
					Object array[] = {dni,nombre,apellido,edad};
					dtm.addRow(array);
				}
				
				resultadoConsulta.close();
				stmt.close();
			}catch(SQLException e){
				System.out.println("Error de base de datos " + e.getMessage());
			}
		}
}
