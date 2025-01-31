package view;

import java.util.ArrayList;

import modelo.Animal;
import modelo.Cita;

public class MenuCarrito {

	public static void mostrar(ArrayList<Cita> listaCitas, ArrayList<Animal> listaAnimales) {
		System.out.println("--- Carrito ---");
		System.out.println();
		for (int i = 0; i<listaCitas.size(); i++) {
			System.out.println((i+1) + listaCitas.get(i).toString() +" "+listaAnimales.get(i).toString()+"\n");
		}
	}
}
