package proyectoBD;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class View {

	JFrame ventana;
	
	Container contenedor;
	
	JLabel lbDni;
	JLabel lbNombre;
	JLabel lbApellido;
	JLabel lbEdad;
	JLabel lbUsuario;
	JLabel lbContraseña;
	
	JPanel panelDatos;
	JPanel panelTabla;
	JPanel panelBotones;
	JPanel panelConexion;
	
	JButton butInsertar;
	JButton butConsultar;
	JButton butEliminar;
	JButton butModificar;
	JButton butListar;
	JButton butSalir;
	JButton butConexion;
	
	JTextField txtDni;
	JTextField txtNombre;
	JTextField txtApellido;
	JTextField txtEdad;
	JTextField txtUsuario;
	JPasswordField txtContraseña;
	
	String cabecera[] = {"DNI","Nombre","Apellido","Edad"};
	Object datos[][];
	DefaultTableModel dtm;
	JTable tabla;
	JScrollPane scroll;
	
	Color amarilloClaro = new Color(253,255,143,150);
	
	public View(){
		crearComponentes();
		colocarComponentes();
	}
	
	public void crearComponentes(){
		
		ventana = new JFrame("Proyecto Base de Datos");
		ventana.setResizable(true);
		ventana.setSize(300, 200);
		
		contenedor = ventana.getContentPane();
		
		panelDatos = new JPanel();
		panelTabla = new JPanel();
		panelBotones = new JPanel();
		panelConexion = new JPanel();
		
		lbDni = new JLabel("DNI:");
		lbNombre = new JLabel("Nombre:");
		lbApellido = new JLabel("Apellido:");
		lbEdad = new JLabel("Edad:");
		lbUsuario = new JLabel("Usuario:");
		lbContraseña = new JLabel("Contraseña:");
		
		butInsertar = new JButton("Insertar");
		butConsultar = new JButton("Consultar");
		butEliminar = new JButton("Eliminar");
		butModificar = new JButton("Modificar");
		butListar = new JButton("Listar");
		butSalir = new JButton("Salir");
		butConexion = new JButton("Conectar");
			
		txtDni = new JTextField(20);
		txtNombre = new JTextField(20);
		txtApellido = new JTextField(20);
		txtEdad = new JTextField(20);
		txtUsuario = new JTextField(20);
		txtContraseña = new JPasswordField(20);
		
		dtm = new DefaultTableModel(datos,cabecera);
		tabla = new JTable(dtm);
		scroll = new JScrollPane(tabla);
	}
	
	public void colocarComponentes(){
		panelConexion.setLayout(new GridLayout(3,2,10,20));
		panelConexion.add(lbUsuario);
		panelConexion.add(txtUsuario);
		panelConexion.add(lbContraseña);
		panelConexion.add(txtContraseña);
		panelConexion.add(butConexion);
		panelConexion.add(butSalir);
		contenedor.add(panelConexion, "Center");
		ventana.setLocation(850,400);
		ventana.setVisible(true);
	}
	
	public void colocarVentanaPrincipal(){
		contenedor.remove(panelConexion);
		
		ventana.setResizable(true);
		ventana.setMinimumSize(new Dimension(570,600));
		ventana.setLocation(400,200);
		ventana.setSize(1000, 600);
		
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.add(lbDni);
		panelDatos.add(txtDni);
		panelDatos.add(lbNombre);
		panelDatos.add(txtNombre);
		panelDatos.add(lbApellido);
		panelDatos.add(txtApellido);
		panelDatos.add(lbEdad);
		panelDatos.add(txtEdad);
		
		panelTabla.add(scroll);
		
		panelBotones.add(butInsertar);
		panelBotones.add(butEliminar);
		panelBotones.add(butConsultar);
		panelBotones.add(butModificar);
		panelBotones.add(butListar);
		panelBotones.add(butSalir);
		
		contenedor.add(panelDatos,"North");
		contenedor.add(panelTabla,"Center");
		contenedor.add(panelBotones,"South");
	}
	
	public void conectaControlador(Controller controlador){
		butInsertar.addActionListener(controlador);
        butConsultar.addActionListener(controlador);
        butEliminar.addActionListener(controlador);
        butListar.addActionListener(controlador);
        butModificar.addActionListener(controlador);
        butSalir.addActionListener(controlador);
        butConexion.addActionListener(controlador);
	}
	
}