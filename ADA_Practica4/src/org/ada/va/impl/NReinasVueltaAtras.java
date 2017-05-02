package org.ada.va.impl;

/**
 * Implementación del algoritmo de las n-reinas con la técnica de vuelta atrás.
 * Tiene dos métodos de entrada a la funcionalidad proporcionados por la clase que implementa:
 * - init: devuelve la primera solución para el problema
 * - initTodas: devuelve todas las soluciones para el problema
 * El modificador verbose nos permite controlar el nivel de salida de la clase
 * @author Unknown
 */
public class NReinasVueltaAtras extends NReinasAbstract {
	
	/**
	 * Constructor de la clase.
	 * @param dimension tamaño del tablero y número de reinas a colocar
	 */
	public NReinasVueltaAtras(Integer dimension) {
		super(dimension);
	}

	/**
	 * Calcula la solución para una etapa concreta.
	 * @param etapa etapa para la que queremos calcular la solución.
	 */
	public void vueltaAtras(int etapa) {
		// TODO: implementar el algoritmo que encuentra la primera solución para una etapa concreta
		int i;
		int n = getDimension();
		if (etapa<n) {
			for(i = 0; (i<n)&&(!isExito()); i++) {
				solucion[etapa] = i;
				if (valido(etapa)) {
					if (etapa<n-1){ 
						vueltaAtras(etapa+1);
					}else{
						setExito(true);
					}
				}
			}
		}
	}
	
	/**
	 * Indica si la posible solución es válida para una etapa concreta
	 * @param  etapa etapa para la que queremos validar la solución
	 * @return si la solución es correcta
	 */
	protected Boolean valido(int etapa) { 
		// TODO: implementar el algoritmo para validar si una posible solución es válida para una etapa concreta
		int i;
		for (i=0;i<etapa;i++){
			if ((solucion[i]==solucion[etapa]) || (valAbs(solucion[i]-solucion[etapa])==valAbs(i-etapa))){
				return false;
			}
		}
		return true;
	}
	
	private int valAbs(int x) {
		return (x<0)?(-x):x;
	}

	/**
	 * Método que calcula todas las soluciones posibles para una etapa.
	 * 
	 * @param etapa etapa
	 */
	public void vaTodas(int etapa) {
		// TODO: implementar el algoritmo que calcula todas las posibles soluciones para una etapa concreta
		int i;
		int n = getDimension();
		if (etapa<n) {
			solucion[etapa] = 0;
			for(i = 0; i<n; i++) {
				solucion[etapa] = i;
				if (valido(etapa)) {
					if (etapa<n-1){
						vaTodas(etapa+1);
					}else { 
						todas.addElement(solucion);
						//comunicar solucion
						for (int j=0;j<solucion.length;j++){
							System.out.print(solucion[j]+", ");
						}
						System.out.println();
					}
				}
			}
		}
	}
}
