


////////////////////////////////////////////////////////////////////////////////////////////
// ALUMNO: David Luque Fernández 
// GRUPO: 2º ING. SOFTWARE - A
////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

import javax.naming.PartialResultException;

public final class BuscaElem{
	
	public static <T extends Comparable<? super T>> T kesimo(T v[], int k) {
		return kesimoRec(v,0,v.length-1,k);
	}

	
	public static <T extends Comparable<? super T>> T kesimoRec(T v[], int izq, int der, int k) {
		if(izq < der){
			int pivote = partir(v,v[izq], izq, der);
			if(pivote > izq + k){
				return kesimoRec(v, izq, pivote-1, k);
			}else if(pivote < izq + k){
				return kesimoRec(v, pivote+1, der, k-pivote+izq-1);
			}else{
				return v[pivote];
			}
		}else{
			return v[der];
		}
	}
	
	public static <T extends Comparable<? super T>> int partir(T a[], T piv, int inf, int sup) {

		T pivote = a[sup]; // el array se parte en función de pivote 
		int i = inf-1; // a[inf..i] <= pivote 
		for (int j = inf; j< sup; j++){ // a[i+1..j-1] > pivote 
			if (a[j].compareTo(pivote) <= 0){ // se encuentra un elem <= pivote 
				i++; // se intercambia con el primero de a[i+1..j-1] 
				T aux = a[i]; 
				a[i] = a[j]; 
				a[j] = aux;
			} 
		}
		T aux = a[i+1]; // al final el pivote se intercambia por el 
		a[i+1] = a[sup]; // el primer elemento de a[i+1..j-1] 
		a[sup] = aux; // y se devuelve esa posición
		return i+1;
	}
	
	/*
	public static <T extends Comparable<? super T>> T kesimoRec(T v[], int izq, int der, int k) {
	 
		T h[] = v.clone();
		OrdenacionRapida ord = new OrdenacionRapida();
		ord.ordRapidaRec(h, izq, der);
		return h[k];
    }
	*/
	
	
	public static <T> String vectorAString(T v[]) {
		String res = "[";
		for(int i=0; i<v.length; i++)
			res += v[i].toString()+(i==v.length-1 ? ']' : ',');
		return res;	
	}
	
	/*
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
   */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maxvector;
		int i,k;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el numero de posiciones del vector: ");
		maxvector=sc.nextInt();
		Integer v[]=new Integer[maxvector];

		System.out.print("Introduce "+maxvector+" enteros separados por espacios: ");
		for (i=0;i<maxvector;i++) v[i]=sc.nextInt();
		System.out.print("Introduce la posicion k deseada (de 1-"+maxvector+"): ");k=sc.nextInt();
		Integer elem=kesimo(v,k-1);
		System.out.println("El elemento "+k+"-esimo es: "+elem);
		System.out.println("El vecvtor V es " + vectorAString(v));
	}

}
