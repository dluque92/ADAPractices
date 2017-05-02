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
		//A IMPLEMENTAR POR EL ALUMNO
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
