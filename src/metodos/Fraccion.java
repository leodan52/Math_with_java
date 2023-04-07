package metodos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Clase que define un objeto para el calculo usando números racionales. Se
 * puede realizar operaciones como suma, resta, multiplicación, división y
 * potenciación, así como obtener el inverso aditivo y el multiplicativo de cada
 * objeto.
 * 
 * <p>
 * Esta clase depende de la clase {@link Factorizador}.
 * 
 * @author Santiago, Leonardo D.
 * @see Factorizador
 */

public class Fraccion {

	private Map<String, Integer> fraccion = new HashMap<>();
	private Factorizador factor = new Factorizador();
	private BigDecimal valorDecimal;
	private byte signo;

	public static void main(String[] args) {

		Fraccion f1 = new Fraccion(1, 3);
		Fraccion f2 = new Fraccion(1, 2);

		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f1.sumar(f2).potenciar(2));

//		double numero = 0.33;
//
//		Fraccion f = Fraccion.fraccionDesdeDouble(numero);
//
//		System.out.println("Argumento: " + numero);
//		System.out.println("Salida: " + f);
	}

	/**
	 * <p>
	 * Constructor para definir un {@code Fraccion} de un número entero. Útil para
	 * realizar operaciones.
	 * 
	 * @param numerador Ingresa el entero (int) que desees tratar como fracción
	 */
	public Fraccion(int numerador) {
		this.valorDecimal = new BigDecimal(numerador);
		try {
			this.signo = this.valorDecimal.divide(this.valorDecimal.abs()).byteValue();
		} catch (ArithmeticException e) {
			this.signo = 1;
		}
		this.fraccion.put("num", Math.abs(numerador));
		this.fraccion.put("den", 1);
	}

	/**
	 * <p>
	 * Método estático para convertir un decimal finito, o bien, uno infinito
	 * periódico en su representación fraccionaria.
	 * 
	 * <p>
	 * Para indicar periodicidad en un patrón, hay que ingresar al argumento el
	 * patrón repetido al menos 3 veces, de lo contrario se tomará como un decimal
	 * finito.
	 * 
	 * <p>
	 * Ejemplos:<br>
	 * 0.33 será representado como 33 / 100<br>
	 * 0.333 o 0.3333 seran representado como 1 / 3
	 * 
	 * @param valor Ingresa un double que represente el número decimal finito o
	 *              infinito periodico
	 * @return Regresa un {@code Fraccion} con la representación fraccionaria del
	 *         argumento
	 */
	public static Fraccion fraccionDesdeDouble(double valor) {
		String valorString = String.valueOf(valor);
		String[] valorArray = valorString.split("\\.");
		Fraccion parteEntera = new Fraccion(Integer.parseInt(valorArray[0]));
		Fraccion parteDecimal = Fraccion.parteDecimalToFraccion(valorArray[1]);

		return parteEntera.sumar(parteDecimal);
	}

	/**
	 * <p>
	 * Contructor para definir un {@code Fraccion} usando tanto el numerador y el
	 * denominador de su forma fraccionaria ya definida.
	 * 
	 * @param numerador   Ingresa un int para el numerador de la fracción
	 * @param denomidador Ingresa un int para el denominador de la fracción
	 */
	public Fraccion(int numerador, int denomidador) {
		this.valorDecimal = new BigDecimal((double) numerador / denomidador);
		try {
			this.signo = this.valorDecimal.divide(this.valorDecimal.abs()).byteValue();
		} catch (ArithmeticException e) {
			this.signo = 1;
		}
		this.fraccion.put("num", Math.abs(numerador));
		this.fraccion.put("den", Math.abs(denomidador));

		this.simplificar();
	}

	/**
	 * Este método privado es usado para simplificar la representación fraccionaria
	 * del objeto Fraccion, es decir, asegurar que el numerador y el denominador
	 * sean coprimos.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Coprime_integers">Números
	 *      coprimos en Wikipedia</a>
	 * 
	 */
	private void simplificar() {

		int divisor;

		if (this.fraccion.get("num") == 0) {
			this.fraccion.put("den", 1);
			return;
		} else if (this.fraccion.get("num") == 0) {
			return;
		}

		factor.add(this.fraccion.get("num"), this.fraccion.get("den"));
		factor.factorizar();

		divisor = factor.getGreatestCommonDivisor();
		this.fraccion.put("num", this.fraccion.get("num") / divisor);
		this.fraccion.put("den", this.fraccion.get("den") / divisor);
	}

	/**
	 * Método para sumar uno o más objetos al {@code this}. {@code this} no sufre
	 * cambios.
	 * 
	 * @param args Al menos un {@code Fraccion} para sumar a {@code this}
	 * @return Regresa el resultado de la suma como {@code Fraccion}
	 */
	public Fraccion sumar(Fraccion... args) {

		Fraccion salida = this;

		for (Fraccion f : args) {
			salida = Fraccion.suma(salida, f);
		}

		return salida;

	}

	/**
	 * <p>
	 * Método para obtener el inverso aditivo de {@code this}. Es decir, que si
	 * <em>A</em> es {@code this}, el uso de este método sería lo equivalente a
	 * hacer <em>(-A)</em>; multiplicar por -1. {@code this} no sufre cambios.
	 * 
	 * <p>
	 * Que sea inverso aditivo implica que <br>
	 * <em> A + (-A) = 0 </em>
	 * 
	 * 
	 * @return Regresa un objeto {@code Fraccion} que es el inverso aditivo de
	 *         {@code this}.
	 */
	public Fraccion neg() {
		return new Fraccion(-this.getNumerador(), this.getDenominador());
	}

	/**
	 * <p>
	 * Método para realizar la diferencia ---o resta--- entre {@code this} y otro
	 * más. Es decir, si {@code this} es <em>A</em> y el objeto del argumento es
	 * <em>B</em>, la utilización del método sería equivalente a <br>
	 * <em>A - B .</em><br>
	 * {@code this} no sufre cambios.
	 * 
	 * @param other Un {@code Fraccion} que se resta al objeto original
	 * @return Regresa un {@code Fraccion} resultado de la resta
	 */
	public Fraccion restar(Fraccion other) {
		return this.sumar(other.neg());
	}

	/**
	 * Método para multiplicar uno o más objetos a {@code this}. {@code this} no
	 * sufre cambios.
	 * 
	 * @param args Uno o varios {@code Fraccion} para multiplicar a {@code this}
	 * @return Regresa el resultado de la multiplicación como {@code Fraccion}
	 */
	public Fraccion multiplicar(Fraccion... args) {

		int numeradorSalida = this.getNumerador();
		int denominadorSalida = this.getDenominador();

		for (Fraccion f : args) {
			numeradorSalida *= f.getNumerador();
			denominadorSalida *= f.getDenominador();
		}

		return new Fraccion(numeradorSalida, denominadorSalida);

	}

	/**
	 * <p>
	 * Método para obtener el inverso multiplicativo {@code this}. Es decir, que si
	 * <em>A</em> es {@code this}, el uso de este método sería lo equivalente a
	 * hacer <em>A^(-1)</em>; elevar por -1. {@code this} no sufre cambios.
	 * 
	 * <p>
	 * Que sea inverso multiplicativo implica que <br>
	 * <em> A * A^(-1) = 1 </em><br>
	 * con * como el operador de la multiplicación.
	 * 
	 * @return Regresa un {@code Fraccion} que es el inverso multiplicativo de
	 *         {@code this}.
	 */
	public Fraccion invertir() {
		return new Fraccion(this.getDenominador(), this.getNumerador());
	}

	/**
	 * <p>
	 * Método para dividir en cadena uno o más objetos comenzando con {@code this}.
	 * {@code this} no sufre cambios.
	 * 
	 * <p>
	 * La operación que se realiza, con <em>A</em> como {@code this} es la
	 * siguiente<br>
	 * <em>A / B_1 / B_2 / B_3 / ...</em> con <em> B_1, B_2, B_3,...</em> los
	 * {@code args}
	 * 
	 * 
	 * @param args Uno o varios {@code Fraccion} para dividir en cadena comenzando
	 *             por {@code this}
	 * @return Regresa el resultado de la división como {@code Fraccion}
	 */
	public Fraccion dividir(Fraccion... args) {

		Fraccion salida = this;

		for (Fraccion f : args) {
			salida = salida.multiplicar(f.invertir());
		}

		return salida;

	}

	/**
	 * Método para elevar {@code this} a un exponente entero dado. Es decir, dado el
	 * objeto <em>A</em>, la operación que se realiza es<br>
	 * <em>A^n,</em><br>
	 * siendo <em>n</em> el {@code exponente}. {@code this} no presenta cambios.
	 * 
	 * @param exponente Elige el exponente como int
	 * @return Regresa un {@code Fraccion} que resulta al elevar {@code this} a la
	 *         potencia {@code exponente}
	 */
	public Fraccion potenciar(int exponente) {

		int nuevoNumerador = (int) Math.pow(this.getNumerador(), Math.abs(exponente));
		int nuevoDenominador = (int) Math.pow(this.getDenominador(), Math.abs(exponente));
		int aux;

		if (exponente < 0) {
			aux = nuevoNumerador;
			nuevoNumerador = nuevoDenominador;
			nuevoDenominador = aux;
		}

		return new Fraccion(nuevoNumerador, nuevoDenominador);
	}

	/**
	 * Método estático para la suma de dos {@code Fraccion} cualquiera.
	 * 
	 * @param a {@code Fraccion} a sumar
	 * @param b {@code Fraccion} a sumar
	 * @return Regresa un {@code Fraccion} resultado de la suma
	 */
	public static Fraccion suma(Fraccion a, Fraccion b) {

		Factorizador factor = new Factorizador(a.getDenominador(), b.getDenominador());
		factor.factorizar();
		int denominadorComun = factor.getLeastCommonMultiple();
		int aNumerador = a.getNumerador() * (denominadorComun / a.getDenominador());
		int bNumerador = b.getNumerador() * (denominadorComun / b.getDenominador());
		double numerador = aNumerador + bNumerador;

		return new Fraccion((int) numerador, denominadorComun);

	}

	/**
	 * Esté metodo privado y estático funge como auxiliar para converir un número
	 * decimal a fracción. En particular este método se encarga de la parte decimal
	 * devolviendo un {@code Fraccion} para sumar con la parte entera que
	 * anteriormente también se instanció como {@code Fraccion}.
	 * 
	 * @param cadena Representación en String de la parte decimal de un número
	 *               decimal, es decir, los números a la derecha del punto decimal.
	 * @return Regresa {@code Fraccion} que represente esa parte decimal.
	 */
	private static Fraccion parteDecimalToFraccion(String cadena) {

		if (cadena.equals("0")) {
			return new Fraccion(0);
		}

		String[] grupo = Fraccion.buscarPeriodico(cadena);
		String partePura = grupo[0], patron = grupo[1];
		int lenPatron = patron.length();
		String parteMixta = cadena.replaceAll(partePura + "$", "");
		int lenParteMixta = parteMixta.length();

		Fraccion partePuraFraccion, parteMixtaFraccion, fraccionFactor;

		if (patron.equals("")) {
			partePuraFraccion = new Fraccion(0);
		} else {
			partePuraFraccion = new Fraccion(Integer.parseInt(patron), (int) Math.pow(10, lenPatron) - 1);
		}

		if (parteMixta.equals("")) {
			parteMixtaFraccion = new Fraccion(0);
			fraccionFactor = new Fraccion(1);
		} else {
			parteMixtaFraccion = new Fraccion(Integer.parseInt(parteMixta));
			fraccionFactor = new Fraccion(1, (int) Math.pow(10, lenParteMixta));
		}

		return fraccionFactor.multiplicar(parteMixtaFraccion.sumar(partePuraFraccion));
	}

	/**
	 * Método privado y estático que usa regex para encontrar patrones que se
	 * repiten en la parte decimal de un número decimal. Sí la cadena termina con un
	 * patrón que se repite 3 o más veces, este será tomado como decimal periódico,
	 * de lo contrario, será tomado como decimal finito.
	 * 
	 * @param cadena Representación en String de la parte decimal de un número
	 *               decimal, es decir, los números a la derecha del punto decimal.
	 * @return Regresa el resultado del regex del grupo 0 y 1 en un String[], es
	 *         decir, regresa la subcadena que se encuentra en cadena, y el patrón
	 *         que se repite.
	 */
	private static String[] buscarPeriodico(String cadena) {
		Pattern pattern = Pattern.compile("(\\d+)\\1{2,}\\Z");
		Matcher matcher = pattern.matcher(cadena);
		boolean findCoin = matcher.find();
		String grupo0 = "", grupo1 = "";

		if (findCoin) {
			grupo0 = matcher.group(0);
			grupo1 = matcher.group(1);

			// System.out.println(grupo0);
			// System.out.println(grupo1);
		}

		return new String[] { grupo0, grupo1 };

	}

	/**
	 * Obten el valor del númerador de {@code this}. En esta clase se considera que
	 * el signo de la fracción está contenida en el númerador, por lo que el valor
	 * retornado lo incluirá.
	 * 
	 * @return Valor int del númerador.
	 */
	public int getNumerador() {
		return this.signo * this.fraccion.get("num");
	}

	/**
	 * Obten el valor del denominador de {@code this}. En esta clase se considera
	 * que el denominador siempre será positivo. El signo lo da el numerador.
	 * 
	 * @return Valor int del denominador.
	 */
	public int getDenominador() {
		return this.fraccion.get("den");
	}

	/**
	 * Obten el valor decimal de {@code this} con punto flotante.
	 * 
	 * @return Regresa un BigDecimal
	 */
	public BigDecimal getValorDecimal() {
		return valorDecimal;
	}

	/**
	 * Obten un 1 o un -1 que define el signo {@code this}, positivo o negativo
	 * respectivamente.
	 * 
	 * @return Regresa un byte con el signo del objeto.
	 */
	public byte getSigno() {
		return signo;
	}

	@Override
	public String toString() {
		String salida = (this.signo * this.fraccion.get("num")) + " / " + this.fraccion.get("den");

		if (this.fraccion.get("num") == 0) {
			salida = "0";
		} else if (this.fraccion.get("den") == 1) {
			salida = String.valueOf(this.signo * this.fraccion.get("num"));
		}

		return salida;
	}

}
