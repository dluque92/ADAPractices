public class vectorMaximo {
	
	public static int[] subvectorMasLargo(int [] v, int inf, int sup){
		int med=(inf+sup)/2;
		if(inf > sup){
			return v;
		}
		int [] arrayMasLargoDesdeElMedio = calcularArray(v,med);
		int [] arrayMasLargoPorLaIzquier = subvectorMasLargo(v,inf,med-1);
		int [] arrayMasLargoPorLaDerecha = subvectorMasLargo(v,med+1,sup);
		if(arrayMasLargoDesdeElMedio.length > arrayMasLargoPorLaIzquier.length && arrayMasLargoDesdeElMedio.length > arrayMasLargoPorLaDerecha.length){
			return arrayMasLargoDesdeElMedio;
		}else if(arrayMasLargoPorLaIzquier.length > arrayMasLargoDesdeElMedio.length && arrayMasLargoPorLaIzquier.length > arrayMasLargoPorLaDerecha.length){
			return arrayMasLargoPorLaIzquier;
		}else{
			return arrayMasLargoPorLaDerecha;
		}
	}
	
	private static int[] calcularArray(int[] v, int med) {
		int [] a = v.clone();
		int i = 0;
		int j = med;
		while(v[j]<v[j+1]){
			a[i]=v[med];
			i++;
			j++;
		}
		return a;
	}
	
	public static void main(String[] args) {
		int[] v = {1,1,1,1,1,1,1,2,3,4};
		subvectorMasLargo(v, 0, v.length);
	}
}
