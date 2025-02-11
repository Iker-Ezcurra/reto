package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Cita;
import modelo.Cliente;

public class MenuFinalizarApp {

	//Dado un ArrayList de citas, un ArrayList de animales, y un cliente, si el ArrayList de citas no está vacío se advierte al cliente que si se cierra la aplicación sin confirmar las citas se perderán y se le da la opción de cerrarla igualmente o abrir el carrito
	public static void mostrar(ArrayList<Cita> listaCitas, ArrayList<Animal> listaAnimales, Cliente cliente) throws SQLException {
		Scanner sc = new Scanner (System.in);
		boolean valido = false;
		boolean cerrar = true;
		
		if (listaCitas.size() > 0) {
			while (!valido) {
				System.out.println("\nTienes citas en el carrito, si cierras la aplicación desaparecerán");
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
			System.out.println("\nCerrando aplicación...");
			System.out.println("¡MAIPet le desea un buen día!");
			System.exit(0);
		}
	}
	
}