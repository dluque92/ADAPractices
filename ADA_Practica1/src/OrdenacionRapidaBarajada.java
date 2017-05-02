

import java.util.Random;

////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO: David Luque Fernández
// GRUPO: 2º ING SOFTWARE - A
////////////////////////////////////////////////////////////////////////////////////////////

public class OrdenacionRapidaBarajada extends OrdenacionRapida {
	
	// Implementación de QuickSort con reordenación aleatoria inicial (para comparar tiempos experimentalmente)
	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
		barajar(v);
		//System.out.println(vectorAString(v)); Para probar que el array se baraja
		int izq = 0;
		int der = v.length-1;
		ordRapidaRec(v, izq, der);
    }

	// reordena aleatoriamente los datos de un vector
    private static <T> void barajar(T v[]) {
    	Random rnd = new Random();
    	int alea;
    	for(int i = 0; i < v.length; i++){
    		alea = rnd.nextInt(v.length-1);
    		T aux = v[i];
    		v[i] = v[alea];
    		v[alea] = aux;
    	}
    }	
	

 // Pequeños ejemplos para pruebas iniciales.
 	public static void main (String args[]) {
 	
 		// Un vector de enteros
 		Integer vEnt[] = {3,8,6,5,2,9,1,1,4};
 		ordenar(vEnt);
 		

 		// Un vector de caracteres
 		Character vCar[] = {'d','c','v','b'};
 		ordenar(vCar);
 		

 	}	
}
