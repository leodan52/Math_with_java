package metodos;

import java.util.ArrayList;

public class VectorInt {

	private ArrayList<Integer> vector = new ArrayList<>();

	public VectorInt() {
	}

	public VectorInt(int... numeros) {
		for (int num : numeros) {
			this.vector.add(num);
		}
	}

	public VectorInt(ArrayList<Integer> vector) {
		this.vector = vector;
	}

	public boolean equals(VectorInt other) {
		if (this.getDimension() != other.getDimension()) {
			return false;
		} else {

			for (int i = 0; i < this.getDimension(); i++) {
				if (this.getComponent(i) != other.getComponent(i)) {
					return false;
				}
			}

		}

		return true;
	}

	public boolean isAllDivisibleBy(int escalar) {

		for (int i = 0; i < this.getDimension(); i++) {
			if (this.getComponent(i) % escalar != 0) {
				return false;
			}
		}

		return true;
	}

	public boolean isAnyDivisibleBy(int escalar) {

		for (int i = 0; i < this.getDimension(); i++) {
			if (this.getComponent(i) % escalar == 0) {
				return true;
			}
		}

		return false;
	}

	public boolean isAllComponentsOnes() {
		VectorInt ones = VectorInt.vectorOnes(this.getDimension());

		return this.equals(ones);
	}

	public VectorInt module(int escalar) {
		ArrayList<Integer> salida = new ArrayList<>();

		for (int i = 0; i < this.getDimension(); i++) {
			salida.add(this.getComponent(i) % escalar);
		}

		return new VectorInt(salida);
	}

	public VectorInt product(int escalar) {
		ArrayList<Integer> salida = new ArrayList<>();

		for (int i = 0; i < this.getDimension(); i++) {
			salida.add(this.getComponent(i) * escalar);
		}

		return new VectorInt(salida);
	}

	public static VectorInt addition(VectorInt... vectors) {
		VectorInt salida = VectorInt.vectorZeros(vectors[0].getDimension());

		for (VectorInt vector : vectors) {
			salida = binAddition(salida, vector);
		}

		return salida;

	}

	private static VectorInt binAddition(VectorInt a, VectorInt b) {

		ArrayList<Integer> salida = new ArrayList<>();

		if (a.getDimension() != b.getDimension()) {
			return null;
		} else {

			for (int i = 0; i < a.getDimension(); i++) {
				salida.add(a.getComponent(i) + b.getComponent(i));
			}

		}

		return new VectorInt(salida);
	}

	public static VectorInt directProduct(VectorInt a, VectorInt b) {

		ArrayList<Integer> salida = new ArrayList<>();

		if (a.getDimension() != b.getDimension()) {
			return null;
		} else {

			for (int i = 0; i < a.getDimension(); i++) {
				salida.add(a.getComponent(i) * b.getComponent(i));
			}

		}

		return new VectorInt(salida);
	}

	public static int dotProduct(VectorInt a, VectorInt b) {
		int salida = 0;

		if (a.getDimension() != b.getDimension()) {
			return 0;
		} else {

			for (int i = 0; i < a.getDimension(); i++) {
				salida += a.getComponent(i) * b.getComponent(i);
			}

		}

		return salida;
	}

	public static VectorInt vectorOnes(int dimension) {
		VectorInt salida = new VectorInt();

		for (int i = 0; i < dimension; i++) {
			salida.add(1);
		}

		return salida;
	}

	public static VectorInt vectorZeros(int dimension) {
		VectorInt salida = new VectorInt();

		for (int i = 0; i < dimension; i++) {
			salida.add(0);
		}

		return salida;
	}

	public static VectorInt toInt(VectorDouble vector) {

		VectorInt salida = new VectorInt();

		for (int i = 0; i < vector.getDimension(); i++) {
			salida.add((int) vector.getComponent(i));
		}
		
		return salida;
	}

	public void add(int...elements) {
		for (int element: elements) {
			this.vector.add(element);
		}
	}
	
	public void clear() {
		this.vector.clear();
	}

	public double norm() {
		return Math.sqrt(VectorInt.dotProduct(this, this));
	}

	public int getComponent(int index) {
		return this.vector.get(index);
	}

	public int getDimension() {
		return this.vector.size();
	}

	@Override
	public String toString() {
		return this.vector.toString();
	}

}
