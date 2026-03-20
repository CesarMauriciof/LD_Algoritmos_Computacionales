package View;

import Controller.CRUD;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CRUD controlador = new CRUD();
        Scanner leer = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENU LISTA DOBLE (MVC) ---");
            System.out.println("1. Insertar numero (ID)");
            System.out.println("2. Mostrar lista completa");
            System.out.println("3. Actualizar ID");
            System.out.println("4. Eliminar ID");
            System.out.println("5. ORDENAR CON RADIX SORT");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            
            try {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el ID a insertar: ");
                        int id = leer.nextInt();
                        controlador.insert(id);
                        System.out.println("Insertado correctamente.");
                        break;
                    case 2:
                        System.out.println("\n--- Contenido de la Lista ---");
                        controlador.showAll();
                        break;
                    case 3:
                        System.out.print("Ingrese ID actual: ");
                        int viejo = leer.nextInt();
                        System.out.print("Ingrese nuevo ID: ");
                        int nuevo = leer.nextInt();
                        if(controlador.update(viejo, nuevo)) System.out.println("Actualizado.");
                        else System.out.println("No se encontro el ID.");
                        break;
                    case 4:
                        System.out.print("Ingrese ID a eliminar: ");
                        int borrar = leer.nextInt();
                        if(controlador.delete(borrar)) System.out.println("Eliminado.");
                        else System.out.println("No se pudo eliminar.");
                        break;
                    case 5:
                        System.out.println("Ejecutando Radix Sort...");
                        controlador.radixSort(); // Asegúrate de que sea public en CRUD.java
                        System.out.println("Lista ordenada.");
                        controlador.showAll();
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese solo numeros.");
                leer.next(); // Limpiar el scanner
            }
        } while (opcion != 6);
    }
}