import java.util.Scanner;

public class ColaFIFO {

    static final int MAX = 10;
    static String[] cola = new String[MAX];
    static int frente = 0;
    static int cantidad = 0;

    static Scanner sc = new Scanner(System.in);

    // 1. INSERTAR
    public static void insertar() {

        if (cantidad == MAX) {
            System.out.println("ERROR: COLA LLENA.");
            System.out.println("No se pueden insertar mas datos.");
            return;
        }

        System.out.print("Ingresa una letra o numero: ");
        String dato = sc.next();

        int fin = (frente + cantidad) % MAX;
        cola[fin] = dato;
        cantidad++;

        System.out.println("Elemento insertado correctamente.");
        System.out.println("Elementos en la cola: " + cantidad);

        if (cantidad == MAX) {
            System.out.println("COLA LLENA: limite de 10 elementos.");
        }
    }

    // 2. ELIMINAR ELEMENTO DEL FRENTE
    public static void eliminar() {

        if (cantidad == 0) {
            System.out.println("ERROR: COLA VACIA (UNDERFLOW).");
            return;
        }

        System.out.println("Elemento eliminado: " + cola[frente]);

        cola[frente] = null;
        frente = (frente + 1) % MAX;
        cantidad--;

        if (cantidad == 0) {
            frente = 0;
        }

        System.out.println("Elemento eliminado correctamente.");
    }

    // 3. MOSTRAR COLA
    public static void mostrar() {

        if (cantidad == 0) {
            System.out.println("La cola esta vacia.");
            return;
        }

        System.out.println("\n--- COLA FIFO ---");

        for (int i = 0; i < cantidad; i++) {
            int posicion = (frente + i) % MAX;

            System.out.println(
                "Elemento " + (i + 1) + ": " + cola[posicion]
            );
        }

        System.out.println("Total de elementos: " + cantidad);
    }

    // 4. VER EL FRENTE
    public static void verFrente() {

        if (cantidad == 0) {
            System.out.println("La cola esta vacia.");
            return;
        }

        System.out.println("Primer elemento de la cola: "
                + cola[frente]);
    }

    // 5. CONTAR ELEMENTOS
    public static void contarElementos() {

        System.out.println("Elementos actuales: " + cantidad);
        System.out.println("Capacidad maxima: " + MAX);
        System.out.println("Espacios disponibles: "
                + (MAX - cantidad));
    }

    // 6. VACIAR COLA
    public static void vaciarCola() {

        if (cantidad == 0) {
            System.out.println("La cola ya esta vacia.");
            return;
        }

        for (int i = 0; i < MAX; i++) {
            cola[i] = null;
        }

        frente = 0;
        cantidad = 0;

        System.out.println("La cola se ha vaciado correctamente.");
    }

    // 7. ELIMINAR ELEMENTO ESPECIFICO
    // Elimina el elemento elegido y todos los que estan delante.
    public static void eliminarEspecifico() {

        if (cantidad == 0) {
            System.out.println("ERROR: COLA VACIA.");
            return;
        }

        mostrar();

        System.out.print(
            "\nIngresa el elemento que deseas eliminar: "
        );
        String buscado = sc.next();

        int indice = -1;

        // Buscar la primera coincidencia desde el frente
        for (int i = 0; i < cantidad; i++) {
            int posicion = (frente + i) % MAX;

            if (cola[posicion].equalsIgnoreCase(buscado)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            System.out.println("El elemento no existe en la cola.");
            return;
        }

        // Eliminar el elemento seleccionado y los anteriores
        int eliminados = indice + 1;

        for (int i = 0; i < eliminados; i++) {
            int posicion = (frente + i) % MAX;
            System.out.println("Elemento eliminado: " + cola[posicion]);
            cola[posicion] = null;
        }

        frente = (frente + eliminados) % MAX;
        cantidad -= eliminados;

        if (cantidad == 0) {
            frente = 0;
        }

        System.out.println(
            "Se eliminaron " + eliminados + " elemento(s)."
        );

        if (cantidad > 0) {
            System.out.println("Elementos restantes:");
            mostrar();
        } else {
            System.out.println("La cola ha quedado vacia.");
        }
    }

    // 8. MENU PRINCIPAL
    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== MENU COLA FIFO =====");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Eliminar elemento del frente");
            System.out.println("3. Mostrar cola");
            System.out.println("4. Ver primer elemento");
            System.out.println("5. Contar elementos y espacios");
            System.out.println("6. Vaciar cola");
            System.out.println("7. Eliminar elemento especifico");
            System.out.println("8. Salir");
            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    insertar();
                    break;

                case 2:
                    eliminar();
                    break;

                case 3:
                    mostrar();
                    break;

                case 4:
                    verFrente();
                    break;

                case 5:
                    contarElementos();
                    break;

                case 6:
                    vaciarCola();
                    break;

                case 7:
                    eliminarEspecifico();
                    break;

                case 8:
                    System.out.println("Programa terminado.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 8);

        sc.close();
    }
}