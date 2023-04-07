import java.util.Scanner;

public class Factorial {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		long entrada, salida = 1;
		String control;

		while (true) {
			System.out.print("Ingrese un número>>> ");
			entrada = Long.parseLong(scanner.nextLine());

			for (long i = 1; i <= entrada; i++) {

				salida *= i;

			}

			System.out.println("\n" + entrada + "! = " + salida);

			System.out.print("\n¿Desea continuar? (s/otra tecla)>> ");
			control = scanner.nextLine();

			if (control.toLowerCase().equals("s")) {
				salida = 1;
				System.out.println();
			} else {
				System.out.println("\nGracias!!");
				break;
			}

		}

		scanner.close();
	}
}
