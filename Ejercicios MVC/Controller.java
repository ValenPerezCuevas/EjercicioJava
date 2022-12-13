package proyectoBD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.text.html.parser.Parser;

/**
 * Controla los eventos que ocurren al pulsar los botones de la clase View.
 * @author Borja de la Mata Pacheco
 * @version 19-5-2017
 * @see Principal, View, Bd, ObRegistro, ObListar, ObInserccion, ObModificacion, ObBorrado, ObConsultar
 * @since 19-5-2017
 */

public class Controller implements ActionListener{

	/**
	 * Atributo Vista grafica de la aplicación
	 */
	View vista;
	
	/**
	 * Constructor con un parametro.
	 * Crea un objeto View
	 * @param vista
	 */
	public Controller(View vista){
		this.vista = vista;
	}

	/**
	 * Metodo heredado de la clase ActionListener
	 * Recibe un objeto ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Estructura switch que controla lo que hace cada boton
		String fuente = e.getActionCommand();
		switch(fuente){
			case "Conectar":
				conectar();
				break;
			case "Insertar":
				insertar();
				break;
			case "Consultar":
				consultar();
				break;
			case "Eliminar":
				borrar();
				break;
			case "Modificar":
				modificar();
				break;
			case "Listar":
				vaciarTabla();
				new ObListar(vista.dtm);
				break;
			case "Salir":{
				//Si se ha realizado la conexion la podemos cerrar, en caso contrario salimos sin cerrar la conexion
				if(Bd.getConexion() != null){
					try {
						Bd.getConexion().close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(vista.ventana, "Error al cerrar la base de datos: " + e1.getSQLState());
					}
				}
				System.exit(0);
				break;
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void conectar(){
		Bd bd = new Bd();
		if(!vista.txtUsuario.getText().equals("") && !vista.txtContraseña.getText().equals("")){
			if(vista.txtUsuario.getText().equals(bd.usuario) && vista.txtContraseña.getText().equals(bd.password))
				vista.colocarVentanaPrincipal();
			else
				JOptionPane.showMessageDialog(vista.ventana, "Usuario o contraseña incorrectos");
		}else
			JOptionPane.showMessageDialog(vista.ventana, "Campos vacios, inserte datos");
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertar(){
		if(!vista.txtDni.getText().equals("") && !vista.txtNombre.equals("") && !vista.txtApellido.equals("") && !vista.txtEdad.equals("")){
			ObRegistro registro = new ObRegistro(vista.txtDni.getText(), vista.txtNombre.getText(), vista.txtApellido.getText(), Integer.parseInt(vista.txtEdad.getText()));
			if(new ObConsultar(registro.getDni()).posicionEncontrada() == 0){
				new ObInsercion(registro);
				JOptionPane.showMessageDialog(vista.ventana, "Insertado con exito");
				vaciarCamposTxt();
			}else
				JOptionPane.showMessageDialog(vista.ventana, "DNI duplicado");
		}else
			JOptionPane.showMessageDialog(vista.ventana, "Error. Faltan datos");
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void consultar(){
		if(!vista.txtDni.getText().equals("")){
			ObConsultar consulta = new ObConsultar(vista.txtDni.getText());
			if(consulta.posicionEncontrada() != 0){
				vaciarCamposTxt();
				vaciarTabla();
				ObRegistro registro = consulta.dameDatos();
				Object array[] = {registro.getDni(), registro.getNombre(), registro.getApellido(), registro.getEdad()};
				vista.dtm.addRow(array);
			}else
				JOptionPane.showMessageDialog(vista.ventana, "No hay coincidencias");
		}else{
			JOptionPane.showMessageDialog(vista.ventana, "Introduzca un DNI para consultar");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void modificar(){
		if(!vista.txtDni.getText().equals("")){
			ObConsultar consulta = new ObConsultar(vista.txtDni.getText());
			if(consulta.posicionEncontrada() != 0){
				if(!vista.txtNombre.equals("") && !vista.txtApellido.equals("") && !vista.txtEdad.equals("")){
					ObRegistro registroNuevo = new ObRegistro(vista.txtDni.getText(), vista.txtNombre.getText(), vista.txtApellido.getText(), Integer.parseInt((vista.txtEdad.getText())));
					new ObModificacion(consulta.posicionEncontrada(),registroNuevo);
					vaciarCamposTxt();
				}else
					JOptionPane.showMessageDialog(vista.ventana, "Error. Debe introducir datos");
			}else{
				JOptionPane.showMessageDialog(vista.ventana, "El DNI introducido no coincide con ninguno almacenado");
			}
		}else{
			JOptionPane.showMessageDialog(vista.ventana, "Introduzca un DNI para poder modificar un registro");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void borrar(){
		if(!vista.txtDni.getText().equals("")){
			ObConsultar consulta = new ObConsultar(vista.txtDni.getText());
			int numFila = consulta.posicionEncontrada();
			if(numFila != 0){
				new ObBorrado(numFila);
				JOptionPane.showMessageDialog(vista.ventana, "Borrado con exito");
				vaciarCamposTxt();
			}else{
				JOptionPane.showMessageDialog(vista.ventana, "El DNI introducido no coincide con ninguno almacenado");
			}
		}else{
			JOptionPane.showMessageDialog(vista.ventana, "Introduzca un DNI para poder borrar un registro");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void vaciarCamposTxt(){
		vista.txtDni.setText("");
		vista.txtNombre.setText("");
		vista.txtApellido.setText("");
		vista.txtEdad.setText("");
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void vaciarTabla(){
		for(int i = 0; i < vista.dtm.getRowCount(); i++){
			vista.dtm.removeRow(i);
			i -= 1;
		}
	}
}