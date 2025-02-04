package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Animal;
import modelo.Ave;
import modelo.Peludo;
import modelo.Reptil;
import repositorios.RepositorioAnimal;
import repositorios.RepositorioAve;
import repositorios.RepositorioPeludo;
import repositorios.RepositorioReptil;
import repositorios.RepositorioServicio;

public class MenuAnimal {
	
	public static Animal mostrar(ArrayList<Animal> listaAnimales) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Animal animal = new Animal();
		Ave ave = new Ave();
		Peludo peludo = new Peludo();
		Animal reptil = new Reptil();
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
				String codChip = sc.next();
				reptil.setCodigoChip(codChip);
				ave.setCodigoChip(codChip);
				peludo.setCodigoChip(codChip);
				animal.setCodigoChip(codChip);
				/*
				if (RepositorioReptil.comprobar(reptil)) {
					System.out.println("Animal encontrado");
					reptil = RepositorioAnimal.construirAnimal(reptil.getCodigoChip());
					fin = true;
				} else if (RepositorioAve.comprobar(ave)) {
					System.out.println("Animal encontrado");
					animal = RepositorioAnimal.construirAnimal(animal);
					fin = true;
				} else if (RepositorioPeludo.comprobar(peludo)) {
					System.out.println("Animal encontrado");
					animal = RepositorioAnimal.construirAnimal(animal);
					fin = true;
				} else if(RepositorioAnimal.comprobar(animal)) {
					System.out.println("Animal encontrado");
					animal = RepositorioAnimal.construirAnimal(animal);
					fin = true;
				} else {
					for (int i = 0; i < listaAnimales.size(); i++) {
						if ((codChip.equals(listaAnimales.get(i).getCodigoChip()))) {
							if(animal instanceof Reptil) {
								reptil = listaAnimales.get(i);
								animal = reptil;
							} else if (animal instanceof Ave) {
								ave = listaAnimales.get(i);
								animal = ave;
							} else if (animal instanceof Peludo) {
								peludo = listaAnimales.get(i);
								animal = peludo;
							} else if (animal instanceof Animal){
								animal = listaAnimales.get(i);
							}
						}
					}
					fin = true;
				}*/
				if (fin == false) {
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