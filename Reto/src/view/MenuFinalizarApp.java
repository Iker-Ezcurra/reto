package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Cita;
import modelo.Cliente;

public class MenuFinalizarApp {

	public static void mostrar(ArrayList<Cita> listaCitas, ArrayList<Animal> listaAnimales, Cliente cliente) throws SQLException {
		Scanner sc = new Scanner (System.in);
		boolean valido = false;
		boolean cerrar = true;
		
		if (listaCitas.size() > 0) {
			while (!valido) {
				System.out.println("Tienes citas en el carrito, si cierras la aplicación desaparecerán");
				System.out.println("¿Seguro que quieres cerrar la aplicación? (Y/N)");
				String opcion = sc.next();
				if (opcion.equalsIgnoreCase("Y")) {
					valido = true;
				} else if (opcion.equalsIgnoreCase("N")) {
					valido = true;
					cerrar = false;
					System.out.println("Abriendo carrito");
					MenuCarrito.mostrar(listaCitas, listaAnimales, cliente);
				} else {
					cerrar = false;
					System.out.println("Opción inválida");
				}
			}
		}	
		if (cerrar) {
			System.out.println("Cerrando aplicación");
			System.out.println("¡Que tenga un buen dia!");
			System.exit(0);
		}
	}
	
}