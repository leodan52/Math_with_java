import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import metodos.Factorizador;

public class Coprimos {
	public static void main(String[] args) {
		
		FileWriter w = null;
		BufferedWriter bw = null;
		PrintWriter wr = null;
		Factorizador factorizador = new Factorizador();
		int limite = 20;
		
		ArrayList<HashSet<String>> coprimos = new ArrayList<>();
		
		for (int i = 1; i <= limite; i++) {
			
			for (int j = 1; j <= limite; j++) {
				
				if (i == j) {
					continue;
				}
				
				factorizador.add(i, j);
				factorizador.factorizar();
				
				if (factorizador.getGreatestCommonDivisor() == 1) {
					HashSet<String> aux = new HashSet<>();
					aux.add(String.valueOf(i));
					aux.add(String.valueOf(j));
					
					if (!coprimos.contains(aux)) {
						coprimos.add(aux);
					}
				}
				
				factorizador.clear();
			}
			
		}
		
		
		
		Collections.sort(coprimos, (set1, set2) -> {
			double max1 = 0;
			double max2 = 0;
			
			for (String num: set1) {
				if (Double.parseDouble(num) > max1) {
					max1 = Double.parseDouble(num);
				}
			}
			
			for (String num: set2) {
				if (Double.parseDouble(num) > max2) {
					max2 = Double.parseDouble(num);
				}
			}
			
			return Double.compare(max1, max2);
		});
	
		
		try {
			w = new FileWriter("Estos_son_pares_coprimos.txt");
			bw = new BufferedWriter(w);
			wr = new PrintWriter(bw);

			for (HashSet<String> par : coprimos) {
				
				ArrayList<String> aux = new ArrayList<>(par);
				
				wr.println(String.join(":", aux));
				
			}

			wr.close();
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
