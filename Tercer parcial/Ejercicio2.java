import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio2 {

    static Scanner sc = new Scanner(System.in);
    static int[] datos = new int[0];

    public static void main(String[] args) {

        System.out.print("Cuantos identificadores deseas ingresar? ");
        int cantidad = leerEntero();

        insertarDatos(cantidad);

        int opcion;

        do {
            System.out.println("\n===== MENU DE IDENTIFICADORES =====");
            System.out.println("1. Mostrar datos");
            System.out.println("2. Insertar mas datos");
            System.out.println("3. Eliminar un identificador");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    mostrarDatos();
                    break;

                case 2:
                    System.out.print(
                        "Cuantos identificadores deseas agregar? "
                    );
                    int nuevos = leerEntero();
                    insertarDatos(nuevos);
                    break;

                case 3:
                    eliminarElemento();
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println(
                        "Opcion no valida. Intenta de nuevo."
                    );
            }

        } while (opcion != 4);

        sc.close();
    }

    // Leer un numero entero
    public static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero entero valido: ");
            sc.next();
        }

        return sc.nextInt();
    }

    // Insertar, ordenar y eliminar duplicados automaticamente
    public static void insertarDatos(int cantidad) {

        while (cantidad < 0) {
            System.out.print(
                "La cantidad no puede ser negativa. Intenta otra vez: "
            );
            cantidad = leerEntero();
        }

        int tamanioAnterior = datos.length;

        datos = Arrays.copyOf(datos, tamanioAnterior + cantidad);

        for (int i = tamanioAnterior; i < datos.length; i++) {
            System.out.print("Ingresa el identificador: ");
            datos[i] = leerEntero();
        }

        // Ordenar automaticamente de menor a mayor
        Arrays.sort(datos);

        // Eliminar duplicados
        int[] temporal = new int[datos.length];
        int cantidadUnicos = 0;

        for (int i = 0; i < datos.length; i++) {

            if (cantidadUnicos == 0 ||
                temporal[cantidadUnicos - 1] != datos[i]) {

                temporal[cantidadUnicos] = datos[i];
                cantidadUnicos++;
            }
        }

        datos = Arrays.copyOf(temporal, cantidadUnicos);

        System.out.println(
            "Datos agregados, ordenados y duplicados eliminados."
        );

        mostrarDatos();
    }

    // Mostrar los datos actuales
    public static void mostrarDatos() {
        System.out.println(
            "Arreglo actual: " + Arrays.toString(datos)
        );

        System.out.println(
            "Cantidad de identificadores: " + datos.length
        );
    }

    // Eliminar un identificador sin dejar ceros
    public static void eliminarElemento() {

        if (datos.length == 0) {
            System.out.println("El arreglo esta vacio.");
            return;
        }

        mostrarDatos();

        System.out.print(
            "Ingresa el identificador que deseas eliminar: "
        );
        int elemento = leerEntero();

        int posicion = -1;

        for (int i = 0; i < datos.length; i++) {
            if (datos[i] == elemento) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println(
                "El identificador no existe en el arreglo."
            );
            return;
        }

        // Crear un arreglo con un espacio menos
        int[] nuevo = new int[datos.length - 1];

        System.arraycopy(datos, 0, nuevo, 0, posicion);

        System.arraycopy(
            datos,
            posicion + 1,
            nuevo,
            posicion,
            datos.length - posicion - 1
        );

        datos = nuevo;

        // Mantener el orden de menor a mayor
        Arrays.sort(datos);

        System.out.println("Identificador eliminado correctamente.");
        mostrarDatos();
    }
}
