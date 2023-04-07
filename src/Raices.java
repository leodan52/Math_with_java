import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import metodos.Factorizador;

public class Raices {

	private int entradaRaiz = 1;
	private ArrayList<Integer> salidaRaiz = new ArrayList<>();
	private Map<Integer, Integer> contador = new HashMap<>();

	public static void main(String[] args) {

		Raices raiz = new Raices(987654321);
		System.out.println(raiz);

	}

	public Raices(int entrada) {
		this.entradaRaiz = entrada;
		this.salidaRaiz.add(1);
		this.salidaRaiz.add(1);

		this.simplificarRaiz();
	}

	private void simplificarRaiz() {

		Factorizador factoring = new Factorizador(this.entradaRaiz);
		factoring.factorizar();

		for (int factor : factoring.getAllDivisors()) {

			if (contador.containsKey(factor)) {
				contador.put(factor, contador.get(factor) + 1);
			} else {
				contador.put(factor, 1);
			}

		}

		for (int factor : contador.keySet()) {

			int exponente = contador.get(factor);
			int afueraRaiz = exponente / 2;
			int dentroRaiz = exponente % 2;

			if (afueraRaiz != 0) {
				salidaRaiz.set(0, salidaRaiz.get(0) * ((int) Math.pow(factor, afueraRaiz)));
			}

			if (dentroRaiz != 0) {
				salidaRaiz.set(1, salidaRaiz.get(1) * ((int) Math.pow(factor, dentroRaiz)));
			}

		}

	}

	@Override
	public String toString() {

		String salida = salidaRaiz.get(0) + " √" + salidaRaiz.get(1);

		if (salidaRaiz.get(1) == 1) {
			salida = String.valueOf(salidaRaiz.get(0));
		} else if (salidaRaiz.get(0) == 1) {
			salida = "√" + salidaRaiz.get(1);
		}

		return salida;
	}

	public int getEntradaRaiz() {
		return entradaRaiz;
	}

	public void setEntradaRaiz(int entradaRaiz) {
		this.entradaRaiz = entradaRaiz;
		this.salidaRaiz.clear();
		this.salidaRaiz.add(1);
		this.salidaRaiz.add(1);

		this.simplificarRaiz();
	}

}
