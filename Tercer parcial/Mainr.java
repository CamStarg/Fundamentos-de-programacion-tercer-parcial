  
import java.util.Scanner;

public class Mainr {

    static final int MAX = 7;
    static String[] pila = new String[MAX];
    static int tope = -1;

    // Metodo para insertar un elemento (PUSH)
    public static void push(String dato) {
        if (tope == MAX - 1) {
            System.out.println("Desbordamiento: la pila esta llena.");
        } else {
            tope++;
            pila[tope] = dato;
            System.out.println("Elemento agregado: " + dato);
        }
    }

    // Metodo para eliminar el ultimo elemento (POP)
    public static void pop() {
        if (tope < 0) {
            System.out.println("Subdesbordamiento: la pila esta vacia.");
        } else {
            System.out.println("Elemento eliminado: " + pila[tope]);
            pila[tope] = null;
            tope--;
        }
    }

    // Metodo para eliminar un elemento especifico
    // y todos los elementos que estan arriba de el
    public static void eliminarElemento(String dato) {
        if (tope < 0) {
            System.out.println("La pila esta vacia.");
            return;
        }

        int posicion = -1;

        // Buscar el elemento desde el tope hacia abajo
        for (int i = tope; i >= 0; i--) {
            if (pila[i] != null &&
                pila[i].equalsIgnoreCase(dato)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("El elemento no existe en la pila.");
        } else {
            // Eliminar el elemento seleccionado
            // y todos los que estan arriba
            for (int i = tope; i >= posicion; i--) {
                System.out.println("Elemento eliminado: " + pila[i]);
                pila[i] = null;
            }

            // Actualizar el tope
            tope = posicion - 1;

            if (tope < 0) {
                System.out.println("La pila esta vacia.");
            } else {
                System.out.println("Nuevo tope: " + pila[tope]);
            }
        }
    }

    // Metodo para mostrar la pila
    public static void mostrarPila() {
        if (tope < 0) {
            System.out.println("La pila esta vacia.");
        } else {
            System.out.println("\nElementos de la pila:");

            for (int i = MAX - 1; i >= 0; i--) {
                if (pila[i] != null) {
                    System.out.println(
                        "Posicion " + i + ": " + pila[i]
                    );
                }
            }
        }

        System.out.println("Tope actual: " + tope);
        System.out.println("Tope MAX: " + (MAX - 1));
        System.out.println("Capacidad maxima: " + MAX);
    }

    // Metodo principal
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU DE PILAS ---");
            System.out.println("1. Push (agregar)");
            System.out.println("2. Pop (eliminar el ultimo)");
            System.out.println("3. Mostrar pila");
            System.out.println("4. Salir");
            System.out.println("5. Eliminar elemento especifico");
            System.out.print("Elige una opcion: ");

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el elemento: ");
                    String dato = entrada.nextLine();
                    push(dato);
                    break;

                case 2:
                    pop();
                    break;

                case 3:
                    mostrarPila();
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                case 5:
                    System.out.print(
                        "Que elemento quieres eliminar: "
                    );
                    String eliminar = entrada.nextLine();
                    eliminarElemento(eliminar);
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);

        entrada.close();
    }
}
 