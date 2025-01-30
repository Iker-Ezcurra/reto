package view;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.Animal;
import repositorios.Conector;
import repositorios.RepositorioAnimal;

public class MenuAnimal {
	
	public static Animal mostrar() throws SQLException {
		Scanner sc = new Scanner(System.in);
		Animal animal = new Animal();
		boolean fin = false;
		while (!fin) {
			System.out.println("\n--- Reservando cita---");
			System.out.println();
			System.out.println("¿Su animal ya está registrado?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int opcion = sc.nextInt();
			if (opcion == 1) {
				System.out.println("Introduce el codigo de su chip");
				animal.setCodigoChip(sc.next());
				if(RepositorioAnimal.comprobar(animal)) {
					System.out.println("Animal encontrado");
					animal = RepositorioAnimal.contruirAnimal(animal);
					fin = true;
				} else {
					System.out.println("Este chip no pertenece a ningun animal");
				}
				
			} else if (opcion == 2){
				System.out.println("Registra a tu animal");
				animal = InstanciarPorTeclado.Animal();
				if(RepositorioAnimal.comprobar(animal)) {
					System.out.println("Este animal ya esta registrado");
				} else {
					System.out.println("Animal registrado correctamente");
					fin = true;
				}
			} else {
				System.out.println("Opcion incorrecta");
			}
		}
		
		return animal;
	}
	
}