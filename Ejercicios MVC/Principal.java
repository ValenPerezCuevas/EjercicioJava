package proyectoBD;

public class Principal {

	public static void main(String[] args) {
		View vista = new View();
		Controller controlador = new Controller(vista);
		vista.conectaControlador(controlador);
	}
}
