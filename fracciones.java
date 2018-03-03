import java.util.Scanner;

public class fracciones {

	public static void main(String[] args) {
		String entradaTeclado = "";
		@SuppressWarnings("resource")
		Scanner entradaEscaner = new Scanner(System.in);
		entradaTeclado = entradaEscaner.nextLine();
		
		while (!entradaTeclado.equals("\n")) {
			try {
				long result = 0;
				long num = Integer.parseInt(entradaTeclado);
				long k = num;
				boolean encontrado = false;
				long y = 0;
				long intervalo = 0;
				long x = k + 1;
				
				// Encuentro la primera pareja para delimitar el numero de iteracciones
				while (!encontrado) {
					if ((k * x) % (x - k) == 0 && !encontrado) {
						y = (k * x) / (x - k);
						result++;
						intervalo = y;
						encontrado = true;
					}
					x++;
				}
				
				//Busco el resto de parejas
				while (x < intervalo) {
					if ((k * x) % (x - k) == 0 && encontrado) {
						y = (k * x) / (x - k);
						result++;
						intervalo = y; // En el momento en que x==y se acaba
					}
					x++;
				}
				System.out.println(result);
				entradaTeclado = entradaEscaner.nextLine();
			} catch (NumberFormatException nfe) {
				return;
			}

		}
	}

}
