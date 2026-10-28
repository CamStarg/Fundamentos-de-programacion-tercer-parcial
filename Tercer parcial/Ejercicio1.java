

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        System.out.print("Ingresa la cantidad de sucursales: ");
        int cantidad = sc.nextInt();

        while (cantidad <= 0) {
            System.out.print("Ingresa una cantidad mayor que cero: ");
            cantidad = sc.nextInt();
        }

        double[][] ventas = new double[cantidad][12];
        double[] totales = new double[cantidad];
        double sumaGlobal = 0;

        // Capturar las ventas de cada sucursal
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nSUCURSAL " + (i + 1));

            for (int j = 0; j < 12; j++) {
                System.out.print("Ventas de " + meses[j] + ": $");
                ventas[i][j] = sc.nextDouble();

                while (ventas[i][j] < 0) {
                    System.out.print("La venta no puede ser negativa. Ingresa otra cantidad: $");
                    ventas[i][j] = sc.nextDouble();
                }
            }
        }

        // Calcular e imprimir resultados por sucursal
        System.out.println("\n===== REPORTE DE VENTAS =====");

        for (int i = 0; i < cantidad; i++) {

            double total = 0;
            int mejorMes = 0;

            for (int j = 0; j < 12; j++) {
                total += ventas[i][j];

                if (ventas[i][j] > ventas[i][mejorMes]) {
                    mejorMes = j;
                }
            }

            totales[i] = total;
            sumaGlobal += total;

            double promedio = total / 12;

            System.out.println("\nSucursal " + (i + 1));
            System.out.printf("Ventas anuales: $%.2f%n", total);
            System.out.printf("Promedio mensual: $%.2f%n", promedio);
            System.out.println("Mes con mayores ventas: " + meses[mejorMes]);
            System.out.println("Ventas mensuales: "
                    + Arrays.toString(ventas[i]));
        }

        // Calcular promedio global anual por sucursal
        double promedioGlobal = sumaGlobal / cantidad;

        System.out.printf(
                "%nPromedio global anual por sucursal: $%.2f%n",
                promedioGlobal
        );

        // Identificar sucursales superiores al promedio global
        System.out.println("\n===== SUCURSALES SOBRE EL PROMEDIO GLOBAL =====");

        boolean encontrada = false;

        for (int i = 0; i < cantidad; i++) {
            if (totales[i] > promedioGlobal) {
                System.out.printf("Sucursal %d: $%.2f%n",
                        i + 1, totales[i]);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("Ninguna sucursal supera el promedio global.");
        }

        // Ordenar una copia de los totales sin perder los originales
        double[] totalesOrdenados = Arrays.copyOf(totales, cantidad);
        Arrays.sort(totalesOrdenados);

        // Crear ranking de mayor a menor conservando la sucursal
        System.out.println("\n===== RANKING DE SUCURSALES =====");

        boolean[] mostrada = new boolean[cantidad];

        for (int i = cantidad - 1; i >= 0; i--) {

            double mayor = totalesOrdenados[i];

            for (int j = 0; j < cantidad; j++) {
                if (!mostrada[j] && totales[j] == mayor) {
                    System.out.printf("Sucursal %d: $%.2f%n",
                            j + 1, totales[j]);
                    mostrada[j] = true;
                    break;
                }
            }
        }

        sc.close();
    }
}
