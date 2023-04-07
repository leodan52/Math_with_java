import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;

public class Primos {
	public static void main(String[] args) {
		FileWriter w = null;
		BufferedWriter bw = null;
		PrintWriter wr = null;
		ArrayList<Integer> primos = new ArrayList<>();
		int limite = 1_000_000;
		long inicial, final_, deltaTiempo;
		//String fechaCadena;

		primos.add(3);

		inicial = System.currentTimeMillis();

		System.out.println("Buscando los números primos menores a " + limite + "...");

		for (int numero = 5; numero <= limite; numero += 2) {
						
			for (int index = 0; index < primos.size(); index++) {

				if (primos.get(index) > numero / 3.0) {
					primos.add(numero);
					break;
				} else if (numero % primos.get(index) == 0) {
					break;
				}

			}

		}

		final_ = System.currentTimeMillis();

		deltaTiempo = final_ - inicial;

		// fechaCadena = TimeUnit.MILLISECONDS.toSeconds(deltaTiempo) + " segundos";

		System.out.println("Primos encontrados. Tiempo de ejecución: " + deltaTiempo / 1000.0 + " segundos");

		try {
			w = new FileWriter("Estos_son_primos.txt");
			bw = new BufferedWriter(w);
			wr = new PrintWriter(bw);

			wr.println(2);
			for (int primo : primos) {
				wr.println(primo);
			}

			wr.close();
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Programa terminado");

	}
}
