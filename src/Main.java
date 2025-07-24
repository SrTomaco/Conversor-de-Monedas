package conversor;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Conversor conversor = new Conversor();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            if (opcion >= 1 && opcion <= 6) {
                realizarConversion(opcion);
                esperarYlimpiar();
            } else if (opcion != 7) {
                System.out.println("\uD83D\uDE4A Opción inválida. Intente de nuevo.\n");
                esperarYlimpiar();
            }

        } while (opcion != 7);

        System.out.println("\n¡Gracias por usar el conversor de monedas!");
        System.out.println("\n         ¡Hasta pronto! \uD83E\uDEE1");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("**************************************************");
        System.out.println("\uD83D\uDC4B Bienvenido/a al Conversor de Moneda \uD83D\uDC4B");
        System.out.println("**************************************************");
        System.out.println("1) Dólar           => Peso argentino");
        System.out.println("2) Peso argentino  => Dólar");
        System.out.println("3) Dólar           => Real brasileño");
        System.out.println("4) Real brasileño  => Dólar");
        System.out.println("5) Dólar           => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Salir");
        System.out.print(" ☝\uFE0F Elija una opción válida: ");
    }

    private static int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número: \uD83D\uDD0D");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void realizarConversion(int opcion) {
        String base = "USD"; // Valor predeterminado
        String destino = "USD"; // Valor predeterminado

        switch (opcion) {
            case 1 -> { base = "USD"; destino = "ARS"; }
            case 2 -> { base = "ARS"; destino = "USD"; }
            case 3 -> { base = "USD"; destino = "BRL"; }
            case 4 -> { base = "BRL"; destino = "USD"; }
            case 5 -> { base = "USD"; destino = "COP"; }
            case 6 -> { base = "COP"; destino = "USD"; }
        }

        System.out.printf("Ingrese el monto en %s: ", base);
        while (!scanner.hasNextDouble()) {
            System.out.print("\uD83D\uDE4A Por favor, ingrese un número válido: ");
            scanner.next();
        }
        double monto = scanner.nextDouble();

        double resultado = conversor.convertir(base, destino, monto);

        if (resultado > 0) {
            System.out.println("\n--------------------------------------------------");
            System.out.printf("El valor %.2f [%s] corresponde a =>>> %.2f [%s]%n",
                    monto, base, resultado, destino);
            System.out.println("--------------------------------------------------\n");
        } else {
            System.out.println("\uD83D\uDE4A No se pudo realizar la conversión.\n");
        }
    }

    private static void esperarYlimpiar() {
        try {
            Thread.sleep(3000);
            Cleaner.limpiarPantalla();
        } catch (InterruptedException e) {
            System.out.println("\uD83D\uDE4A Error al esperar: " + e.getMessage());
        }
    }
}