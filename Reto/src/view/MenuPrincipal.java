package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cita;
import modelo.Cliente;
import modelo.Animal;

public class MenuPrincipal {

	public static void mostrar(Cliente cliente) throws SQLException {
		ArrayList<Cita> listaCitas = new ArrayList<>();
		ArrayList<Animal> listaAnimales = new ArrayList<>();
		Cita cita = new Cita();
		int opcion = 0;
		
		do {
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
				listaAnimales = MenuAnimal.mostrar(listaAnimales);
				cita = MenuReserva.mostrar(listaAnimales.get(listaAnimales.size() - 1), listaCitas);
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
				System.out.println("Opcion invalida");
				break;
			}
		} while (true);
		
	}
	
}