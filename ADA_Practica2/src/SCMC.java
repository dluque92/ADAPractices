// ALUMNO:
// GRUPO y TITULACION:

/**
 *
 * Clase para resolucion del problema de la
 * Supersecuencia Comun mas Corta para dos cadenas
 * 
 */

import java.util.Random;

public class SCMC {
	int DIAGONAL = 0;
	int SUPERIOR = 1;
	int IZQUIERDA = -1;
	
	private enum enumerados{
		DIAGONAL,SUPERIOR,IZQUIERDA
	};
	
	private String r, t; // Las dos cadenas de la instancia
	private String sigma; // El alfabeto de la instancia
	private int m[][]; // la matriz para resolucion por Prog. Dinamica
	private static Random rnd = new Random(); // generador de aleatorio
	private enumerados proc[][];
	
	/**
	 * Crea una instancia del problema
	 * @param sigma : alfabeto para la instancia
	 * @param r     : primera cadena
	 * @param t     : segunda cadena
	 */
	public SCMC(String sigma, String r, String t) {
		this.r = r;
		this.t = t;
		this.sigma = sigma;
		m = new int[1+r.length()][1+t.length()];
		proc = new enumerados[r.length()+1][t.length()+1];
	}
		
	/**
	 * Crea una instancia aleatoria del problema
	 * @param longMax  : longitud maxima de las cadenas
	 * @param tamSigma : tamaño del alfabeto
	 */
	public SCMC(int longMax, int tamSigma) {		
		this.sigma = Utils.alfabeto(tamSigma);
		r = Utils.cadenaAleatoria(rnd.nextInt(1+longMax),sigma);
		t = Utils.cadenaAleatoria(rnd.nextInt(1+longMax),sigma);
		m = new int[1+r.length()][1+t.length()];

	}

	public String r(){
		return r;
	}

	public String t(){
		return t;
	}
	
	public String sigma(){
		return sigma;
	}

	public int m(int i, int j){
		if (i<m.length && j<m[0].length) {
			return m[i][j];
		} else {
			return -1;
		}
	}

		
	/**
	 * Soluciona la instancia por Prog Dinamica, es decir, rellena
	 * la tabla @m
	 */
	public void solucionaPD(){
		char[] aux1 = r.toCharArray(); // Eje x
		char[] aux2 = t.toCharArray();// Eje y
		solucionaPD2(m, proc, aux1, aux2);// Eje x, Eje y
	}

	private void solucionaPD2(int[][] m, enumerados[][] proc, char[] aux1, char[] aux2) {// x,y
		// Rellenamos la tabla con las condiciones inciales
		for (int i = 0; i <= r.length(); i++) {
			m[i][0] = i;
			proc[i][0] = enumerados.SUPERIOR;
		}
		for (int j = 0; j <= t.length(); j++) {
			m[0][j] = j;
			proc[0][j] = enumerados.IZQUIERDA;
		}
		for (int i = 1; i <= r.length(); i++) {
			for (int j = 1; j <= t.length(); j++) {
				if (aux1[i - 1] == aux2[j - 1]) {
					m[i][j] = 1 + m[i - 1][j - 1];
					proc[i][j] = enumerados.DIAGONAL;
				} else if (m[i - 1][j] >= m[i][j - 1]) {
					m[i][j] = 1 + m[i][j - 1];
					proc[i][j] = enumerados.IZQUIERDA;
				} else {
					m[i][j] = 1 + m[i - 1][j];
					proc[i][j] = enumerados.SUPERIOR;
				}
			}
		}
	}
	
	/**
	 * @return : devuelve la longitud de la solucion
	 *           a la instancia, es decir, la longitud 
	 *           de la supersecuencia comun más corta de @r y @t
	 *           a partir de la tabla obtenida por Prog Dinamica
	 */
	public int longitudDeSolucionPD(){
		return m[r.length()][t.length()];
	}

	/**
	 * @return Devuelve una solucion optima de la instancia, es decir
	 *         una supersecuencia comun mas corta de @r y @t
	 */
	public String unaSolucionPD(){
		char[] aux2 = t.toCharArray();
		char[] aux1 = r.toCharArray();
		int i = r.length(), j = t.length();
		int dis = longitudDeSolucionPD();
		char[] solucion = new char[longitudDeSolucionPD()];
		// solucionaPD2(m,proc,aux1,aux2);
		while ((i != 0 || j != 0) && dis >= 0) {
			if (proc[i][j] == enumerados.DIAGONAL) {
				if (i == 0) {
					solucion[dis - 1] = aux2[j - 1];
					j--;
				} else if (j == 0) {
					solucion[dis - 1] = aux1[i - 1];
					i--;
				} else {
					solucion[dis - 1] = aux1[i - 1];
					i--;
					j--;
				}
			} else if (proc[i][j] == enumerados.IZQUIERDA) {
				if (j == 0) {
					solucion[dis - 1] = aux1[i - 1];
					i--;
				} else {
					solucion[dis - 1] = aux2[j - 1];
					j--;
				}
			} else {
				if (i == 0) {
					solucion[dis - 1] = aux2[j - 1];
					j--;
				} else {
					solucion[dis - 1] = aux1[i - 1];
					i--;
				}
			}
			dis--;
		}
		String cadena = new String(solucion);
		return cadena;
	}
	

		
	// representacion como String de la instancia
	public String toString(){
		return "Sigma="+Utils.entreComillas(sigma)
		        +", r="+Utils.entreComillas(r)
		        +", s="+Utils.entreComillas(t);
	}



	// Obtiene una solucion al problema por "fuerza bruta"
	public String unaSolucionFB() {
		int l = Math.max(r.length(),t.length());
		String res = null;
		for(l=Math.max(r.length(),t.length()); res==null; l++)
		  res = unaSolucionFB("",l);
		return res;
	}

	// método auxiliar recursivo
	private String unaSolucionFB(String s, int l) {
		String str = null;
		if(l==0) {
			if(Utils.esSupersecuencia(s,r) && Utils.esSupersecuencia(s,t))
				str = s;
		}	
		else
			for(int i=0; i<sigma.length(); i++) {
				str = unaSolucionFB(s+sigma.charAt(i),l-1);
				if(str!=null) break;
			}
		return str;
	}
	
	
}
