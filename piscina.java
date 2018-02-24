import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.Math;

// Imports solo para leer decentemente.
// Por algo amamos Java para aprender a programar (no).

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				System.out.println("Error " + e.getMessage());
			}
		}
		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		return str;
	}
}

public class test {

	// Calculo cuantas veces tiene que viajar para llenar la piscina
	public static long vueltas(double piscina, long cubo, long resta) {
		// Si el cubo llena la piscina a la primera no pierde nada.
		// Si no lo hace, lo que pierde la piscina no puede ser mayor igual a lo que
		// llena el cubo.
		// Si no se puede llegar a llenar la piscina devuelvo -1
		long resultado = -1;
		if (piscina > 0) {
			if ((piscina - cubo) <= 0) {
				resultado = 1;
			}
			if (cubo > resta && (piscina - cubo) > 0) {
				resultado = 1 + (long) Math.ceil((piscina - cubo) / (cubo - resta));
			}
			if (cubo == 0 && resta != 0) {
				resultado = (long) Math.ceil((piscina - cubo) / (cubo - resta));
			}
		}
		// System.out.println(resultado);
		return resultado;
	}

	// Programa que obtiene si gana el vecino o yo
	public static String carrera(long[] datos) {
		double piscinaYo = (double) datos[0];
		long cuboYo = datos[1];
		long perdYo = datos[2];
		double piscinaVecino = (double) datos[3];
		long cuboVecino = datos[4];
		long perdVecino = datos[5];
		double viajesYo = 0;
		double viajesVecino = 0;
		long viajes = 0;
		long numviajesYo = 0;
		long numviajesVecino = 0;
		
		if(piscinaYo == 0 || piscinaVecino == 0) {
			return "";
		}
		
		//Obtengo cuántas vueltas hace cada uno para llenar sus respectivas piscinas
		numviajesYo = vueltas(piscinaYo, cuboYo, perdYo);
		numviajesVecino = vueltas(piscinaVecino, cuboVecino, perdVecino);

		String result = null;
		// Si ambos hacen el mismo numero de viajes EMPATE
		if (numviajesYo == numviajesVecino && numviajesYo > 0) {
			viajes = (int) numviajesYo;
			result = "EMPATE " + viajes;
		}
		// Si el vecino ha hecho menos viajes que yo y son mayores a 0
		else if (numviajesVecino < numviajesYo && numviajesVecino > 0 || (numviajesYo < 0 && numviajesVecino > 0)) {
			viajes = (int) numviajesVecino;
			result = "VECINO " + viajes;
		}
		// Si yo hago mas viajes que el vecino y son mayores a 0
		else if (numviajesYo < numviajesVecino && numviajesYo > 0 || (numviajesVecino < 0 && numviajesYo > 0)) {
			viajes = (int) numviajesYo;
			result = "YO " + viajes;
		}
		return result;
	}

	// MAIN

	public static void main(String[] args) {
		// Voy guardando los posibles resultados
		ArrayList<String> resultfinal = new ArrayList<String>();
		FastReader fr = new FastReader();
		boolean fallo = false;
		long[] datos = new long[6];
		// Obtengo los datos
		for (int i = 0; i < 6; i++) {
			datos[i] = fr.nextLong();
		}
		// Mientras las dos piscinas no sean de tamaño 0
		while (datos[0] != 0 || datos[3] != 0) {
			// result es el ganador en llenar su piscina del caso n
			String result = carrera(datos);
			// Si este resultado es null se ha producido un error y si es "" significa que
			// se ha encontrado con 0 0 0 0 0 0
			if (result != null && result != "") {
				resultfinal.add(result);
				// cargo los siguientes datos
				for (int i = 0; i < 6; i++) {
					datos[i] = fr.nextLong();
				}
			} else if (result == null) {
				// Por si se ha producido algun error no me interesa imprimir nada
				fallo = true;
				break;
			} else if (result == "")
				break;
		}
		if (fallo == false) {
			for (int j = 0; j < resultfinal.size(); j++) {
				// Imprimo el ganador de cada caso
				System.out.println(resultfinal.get(j));
			}
		}

	}

}
