package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Cita;
import modelo.Cliente;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioAnimal;
import repositorios.RepositorioAve;
import repositorios.RepositorioCita;
import repositorios.RepositorioPeludo;
import repositorios.RepositorioReptil;

public class MenuCarrito {

	//Dado un ArrayList de citas, un ArrayList de animales y un cliente se imprimen por pantalla los ArrayList mostrando las citas que lleva hechas y los animales para las que son. También se da la opción de cancelar una y de confirmarlas para meterlas en la base de datos
	public static void mostrar(ArrayList<Cita> listaCitas, ArrayList<Animal> listaAnimales, Cliente cliente) throws SQLException {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean fin = false;
		boolean finalizado = false;
		
		Cita cita;
		Peludo peludo = new Peludo();
		Ave ave = new Ave();
		Reptil reptil = new Reptil();
		Animal animal = new Animal();
		
		do {
			System.out.println("\n--- Carrito ---");
			System.out.println();
			
			if (listaCitas.size() == 0) {
				System.out.println("Todavia no hay ninguna reserva");
				System.out.println();
				System.out.println("1. Atras");
				if (sc.nextInt() == 1) {
					finalizado = true;
				} else {
					System.out.println("Opción inválida");
				}
			} else {
				int coste = 0;
				for (int i = 0; i<listaCitas.size(); i++) {
					System.out.print((i+1) +". "+ listaCitas.get(i).toString() + " \t "+ listaAnimales.get(i).toString() +"\n \n");
					
					coste = coste + listaCitas.get(i).getCosteTotal();
				}
				System.out.println("Coste total: " + coste + "€");
				System.out.println();
				System.out.println("1. Cancelar cita");
				System.out.println("2. Confirmar reservas");
				System.out.println("3. Atras");
				opcion = sc.nextInt();
				switch (opcion){
					case 1:
						do {
							System.out.println("Que cita quieres cancelar");
							int borrar = sc.nextInt();
							System.out.println("¿Estas seguro? (Y/N)");
							char eleccion = sc.next().charAt(0);
							if (eleccion == 'Y' || eleccion == 'y') {
								listaCitas.remove(borrar-1);
								listaAnimales.remove(borrar-1);
								System.out.println("La cita se ha eliminado con éxito.");
								fin = true;
							} else if (eleccion == 'N' || eleccion == 'n') {
								System.out.println("Se ha cancelado la operación de borrar cita");
								fin = true;
							} else {
								System.out.println("Opción inválida");
							}
						} while (!fin);
						break;
					case 2: 
						do {
							System.out.println("¿Confirmar reservas? (Y/N)");
							char eleccion = sc.next().charAt(0);
							if (eleccion == 'Y' || eleccion == 'y') {
								int posicion = 0;
								while (listaCitas.size() != 0) {
									animal = listaAnimales.get(posicion);
									if(!(RepositorioAnimal.comprobar(animal.getCodigoChip()))) {
										RepositorioAnimal.insertar(animal, cliente);
										if (animal instanceof Peludo) {
											peludo = (Peludo) animal;
											RepositorioPeludo.insertar(peludo);
										} else if (animal instanceof Ave) {
											ave = (Ave) animal;
											RepositorioAve.insertar(ave);
										} else if (animal instanceof Reptil) {
											reptil = (Reptil) animal;
											RepositorioReptil.insertar(reptil);
										}
									}
									cita = listaCitas.get(posicion);
									RepositorioCita.insertar(cita);
									listaCitas.remove(posicion);
									listaAnimales.remove(posicion);
								}
								System.out.println("Reservas confirmadas");
								System.out.println("Carrito vaciado");
								fin = true;
							} else if (eleccion == 'N' || eleccion == 'n') {
								System.out.println("Se ha cancelado la operación de confirmar citas");
								fin = true;
							} else {
								System.out.println("Opción inválida");
							}
						} while (!fin);
						break;
					case 3:
						finalizado = true;
						break;
					default:
						System.out.println("Opción inválida");
				}
			}
		} while(!finalizado);
	}
	
}