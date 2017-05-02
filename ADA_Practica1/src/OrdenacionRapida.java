

////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO: David Luque Fernández
// GRUPO: 2º ING SOFTWARE - A
////////////////////////////////////////////////////////////////////////////////////////////

public class OrdenacionRapida extends Ordenacion {
  
	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
	    ordRapidaRec(v, 0, v.length-1);
	}

	// Debe ordenar ascendentemente los primeros @n elementos del vector @v con 
	// una implementación recursiva del método de ordenación rápida.	
	public static <T extends Comparable<? super T>> void ordRapidaRec(T v[], int izq, int der) {
		if (izq < der){
			T pivote = v[izq]; 
			int s = partir(v,pivote,izq,der);
			ordRapidaRec(v,izq,s);
			ordRapidaRec(v,s+1,der);
		}
	}
	
   public static <T extends Comparable<? super T>> int partir(T v[], T pivote, int izq, int der) {
		T piv = pivote;
		int i = izq - 1;
		int j = der + 1; // @inv a[inf..i-1] <= pivote, a[j+1..sup] >= pivote
		int mov = 0;
		while (i < j) {
			do {
				j--;
			} while (v[j].compareTo(piv) > 0);
			do {
				i++;
			} while (v[i].compareTo(piv) < 0);
			if (i < j) {
				mov++;
				T aux = v[i];
				v[i] = v[j];
				v[j] = aux;
			}
		}
		return j;
   }
	 
	// Pequeños ejemplos para pruebas iniciales.
	public static void main (String args[]) {
	
		// Un vector de enteros
		Integer vEnt[] = {3,8,6,5,2,9,1,1,4};
		System.out.println(partir(vEnt,3,0,8));
		ordenar(vEnt);

		// Un vector de caracteres
		Character vCar[] = {'d','c','v','b'};
		ordenar(vCar);

	}	
    
}
