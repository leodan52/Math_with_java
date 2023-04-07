package metodos;

import java.util.ArrayList;

public abstract class VectorMath {

	private ArrayList<Integer> vector = new ArrayList<>();
	
	public abstract void add(int element);

	public boolean equals(VectorMath other) {
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

	public static double dotProduct(VectorMath a, VectorMath b) {
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

	public double norm() {
		return Math.sqrt(VectorMath.dotProduct(this, this));
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
