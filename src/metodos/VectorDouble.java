package metodos;

import java.util.ArrayList;

public class VectorDouble {

	private ArrayList<Double> vector = new ArrayList<>();
	
	public static void main(String[] args) {
		
		VectorDouble vector = new VectorDouble(20, 21, 23);
		
		System.out.println(vector);
		
		vector.add(25);
		
		System.out.println(vector);
		
		System.out.println(VectorInt.toInt(vector));
	}

	public VectorDouble() {
	}

	public VectorDouble(double... numeros) {
		for (double num : numeros) {
			this.vector.add(num);
		}
	}

	public VectorDouble(ArrayList<Double> vector) {
		this.vector = vector;
	}

	public boolean equals(VectorDouble other) {
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


	public boolean isAllComponentsOnes() {
		VectorDouble ones = VectorDouble.vectorOnes(this.getDimension());

		return this.equals(ones);
	}

	public VectorDouble product(double escalar) {
		ArrayList<Double> salida = new ArrayList<>();

		for (int i = 0; i < this.getDimension(); i++) {
			salida.add(this.getComponent(i) * escalar);
		}

		return new VectorDouble(salida);
	}

	
	public static VectorDouble addition(VectorDouble...vectors) {
		VectorDouble salida = VectorDouble.vectorZeros(vectors[0].getDimension()); 
		
		for (VectorDouble vector: vectors) {
			salida = binAddition(salida, vector);
		}
		
		return salida;
		
	}
	
	private static VectorDouble binAddition(VectorDouble a, VectorDouble b) {

		ArrayList<Double> salida = new ArrayList<>();

		if (a.getDimension() != b.getDimension()) {
			return null;
		} else {

			for (int i = 0; i < a.getDimension(); i++) {
				salida.add(a.getComponent(i) + b.getComponent(i));
			}

		}

		return new VectorDouble(salida);
	}

	public static VectorDouble directProduct(VectorDouble a, VectorDouble b) {

		ArrayList<Double> salida = new ArrayList<>();

		if (a.getDimension() != b.getDimension()) {
			return null;
		} else {

			for (int i = 0; i < a.getDimension(); i++) {
				salida.add(a.getComponent(i) * b.getComponent(i));
			}

		}

		return new VectorDouble(salida);
	}

	public static double dotProduct(VectorDouble a, VectorDouble b) {
		double salida = 0;

		if (a.getDimension() != b.getDimension()) {
			return 0;
		} else {

			for (int i = 0; i < a.getDimension(); i++) {
				salida += a.getComponent(i) * b.getComponent(i);
			}

		}

		return salida;
	}

	public static VectorDouble vectorOnes(int dimension) {
		VectorDouble salida = new VectorDouble();

		for (int i = 0; i < dimension; i++) {
			salida.add(1);
		}

		return salida;
	}

	public static VectorDouble vectorZeros(int dimension) {
		VectorDouble salida = new VectorDouble();

		for (int i = 0; i < dimension; i++) {
			salida.add(0);
		}

		return salida;
	}
	
	public static VectorDouble toDouble(VectorInt vector) {

		VectorDouble salida = new VectorDouble();

		for (int i = 0; i < vector.getDimension(); i++) {
			salida.add((double) vector.getComponent(i));
		}
		
		return salida;
	}

	public void add(double element) {
		this.vector.add(element);
	}

	public double norm() {
		return Math.sqrt(VectorDouble.dotProduct(this, this));
	}

	public double getComponent(int index) {
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
