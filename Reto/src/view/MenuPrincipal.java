package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Cita;
import modelo.Cliente;
import repositorios.RepositorioServicio;
import modelo.Animal;

public class MenuPrincipal {

	public static void mostrar(Cliente cliente) throws SQLException {
		ArrayList<Cita> listaCitas = new ArrayList<>();
		ArrayList<Animal> listaAnimales = new ArrayList<>();
		Animal animal = new Animal();
		Cita cita = new Cita();
		int opcion = 0;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n--- ¿Que quieres hacer? ---");
			System.out.println();
			System.out.println("1. Ver servicios");
			System.out.println("2. Hacer reserva");
			System.out.println("3. Cambiar de usuario");
			System.out.println("4. Cerrar aplicacion");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				RepositorioServicio.serviciosPorSucursal(MenuOficinas.mostrar());
				break;
			case 2:
				animal = MenuAnimal.mostrar();
				listaAnimales.add(animal);
				cita = MenuReserva.mostrar(animal);
				listaCitas.add(cita);
				break;
			case 3:
				cliente=MenuInicio.mostrar();
				break;
			case 4:
				//hay que hacer un if se ha hecho una reserva mostrar carrito
				System.exit(0);
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} while (opcion!=4);
		
	}
	
}