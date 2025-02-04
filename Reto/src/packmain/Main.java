package packmain;

import java.sql.SQLException;

import repositorios.Conector;
import view.MenuInicio;
import view.MenuPrincipal;

public class Main {

	public static void main(String[] args) throws SQLException {
		Conector.conectar();
		
		MenuPrincipal.mostrar(MenuInicio.mostrar());
	}

}