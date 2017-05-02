import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class Huffman extends HuffmanAbstract {

	/**
	 * Permite generar el arbol de codigos huffman sin establecer los códigos. Este paso se realizará en el método
	 * generarListaCodigo de la clase HuffmanAbstract
	 */
	public void generarArbol(){
		try{
		while (primero.getDerecha() != null) {
			int suma = primero.getFrecuencia() + primero.getDerecha().getFrecuencia();
			Nodo temp = new Nodo((char) 0, suma, false);
			temp = ubicarNodo(temp, primero);
			temp.setHijoIzq(primero);
			temp.setHijoDer(primero.getDerecha());
			primero = primero.getDerecha().getDerecha();
			if(primero.getDerecha() == null){
				raizArbol = temp;
			}
		}
		}catch(NullPointerException e){
			System.out.println("El archivo est· vacio.");
		}
	}

	/**
	 * Metodo principal para realizar pruebas
	 */
	public static void main(String arg[]) throws FileNotFoundException, IOException {
		HuffmanAbstract h = new Huffman();
		String text = "j'aime aller sur le bord de l'eau les jeudis ou les jours impairs";
		InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
		h.readOrigen(is);
		h.imprimirArbol(h.raizArbol,"");
		h.imprimirListaCodigos();
	}
	

}
