package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cita;
import modelo.Cliente;
import modelo.Animal;

public class MenuPrincipal {

	//Dado un cliente Imprime en pantalla el menú de opciones entre las que se encuentran: ver servicios, hacer reserva, cambiar de usuario, carito y cerrar aplicación. En cada una se llama a su método
	public static void mostrar(Cliente cliente) throws SQLException {
		ArrayList<Cita> listaCitas = new ArrayList<>();
		ArrayList<Animal> listaAnimales = new ArrayList<>();
		Cita cita = new Cita();
		int opcion = 0;
		
		do {
			if (cliente.isAdmin()) {
				MenuAdmin.mostrar(cliente);
			} else {
				Scanner sc = new Scanner(System.in);
				System.out.println("\n--- ¿Que quieres hacer? ---");
				System.out.println();
				System.out.println("1. Ver servicios");
				System.out.println("2. Hacer reserva");
				System.out.println("3. Cambiar de usuario");
				System.out.println("4. Carrito");
				System.out.println("5. Cerrar aplicacion");
				opcion = sc.nextInt();
				switch (opcion) {
					case 1:
						MenuOficinas.mostrar();
						break;
					case 2:
						listaAnimales = MenuAnimal.mostrar(listaAnimales, cliente);
						cita = MenuReserva.mostrar(listaAnimales.get(listaAnimales.size() - 1), listaAnimales, listaCitas, cliente);
						listaCitas.add(cita);
						break;
					case 3:
						cliente = MenuInicio.mostrar();
						break;
					case 4:
						MenuCarrito.mostrar(listaCitas, listaAnimales, cliente);
						break;
					case 5:
						MenuFinalizarApp.mostrar(listaCitas, listaAnimales, cliente);
						break;
					default:
						System.out.println();
						System.out.println("Opción inválida");
						break;
					}
			}
		} while (true);
		
	}
	
}