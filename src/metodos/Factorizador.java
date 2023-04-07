package metodos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author leodan52
 *
 */

public class Factorizador {

	private VectorInt conjunto = new VectorInt();
	private ArrayList<Integer> primes = new ArrayList<>();
	private ArrayList<Integer> allDivisors = new ArrayList<>();
	private ArrayList<Integer> divisorsAllNumbers = new ArrayList<>();

	public static void main(String[] args) {

		Factorizador factor = new Factorizador(12, 18, 30);

		factor.factorizar();

		System.out.println(factor.getAllDivisors());
		System.out.println(factor.getDivisorsAllNumbers());
		System.out.println(factor.getConjunto());
		
		System.out.println(factor.getGreatestCommonDivisor());
		System.out.println(factor.getLeastCommonMultiple());

	}

	public Factorizador() {
		this.extractPrimeNumbers();
	}

	public Factorizador(int... numeros) {
		this.conjunto = new VectorInt(numeros);
		this.extractPrimeNumbers();

	}

	public void factorizar() {

		VectorInt aux = this.conjunto;

		for (int i = 0; i < this.primes.size(); i++) {

			if (aux.isAllComponentsOnes()) {
				break;
			}
			
			while (aux.isAnyDivisibleBy(primes.get(i))) {
				
				if (aux.isAllDivisibleBy(primes.get(i))) {
					this.divisorsAllNumbers.add(primes.get(i));
				} 
				
				this.allDivisors.add(primes.get(i));
				aux = this.intDivision(aux, primes.get(i));
			}
			
		}

	}

	public int getLeastCommonMultiple() {
		return Factorizador.productAllList(this.allDivisors);
	}

	public int getGreatestCommonDivisor() {
		return Factorizador.productAllList(divisorsAllNumbers);
	}

	private VectorInt intDivision(VectorInt vector, int divisor) {
		ArrayList<Integer> salida = new ArrayList<>();

		for (int i = 0; i < vector.getDimension(); i++) {

			if (vector.getComponent(i) % divisor != 0) {
				salida.add(vector.getComponent(i));
			} else {
				salida.add(vector.getComponent(i) / divisor);
			}

		}

		return new VectorInt(salida);
	}

	private void extractPrimeNumbers() {

		try {
			FileReader file = new FileReader("Estos_son_primos.txt");
			BufferedReader entrada = new BufferedReader(file);
			String linea;

			while ((linea = entrada.readLine()) != null) {
				this.primes.add(Integer.parseInt(linea));
			}

			entrada.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	private static int productAllList(ArrayList<Integer> list) {
		int salida = 1;

		for (int divisor : list) {
			salida *= divisor;
		}

		return salida;
	}

	public VectorInt getConjunto() {
		return this.conjunto;
	}
	
	public void add(int...numeros) {
		this.conjunto.add(numeros);
	}

	public ArrayList<Integer> getAllDivisors() {
		return allDivisors;
	}

	public ArrayList<Integer> getDivisorsAllNumbers() {
		return divisorsAllNumbers;
	}
	
	public void clear() {
		this.conjunto.clear();
		this.allDivisors.clear();
		this.divisorsAllNumbers.clear();
	}

}
