import java.util.Scanner;

public class app {
    static Scanner Scanner = new Scanner(System.in);
    static float precioBillete;
    static boolean finish = true;
    public static void main(String[] args) {

        
        asientos.init();
        System.out.println("Bienvenido al software de gestión de billetes");
        System.out.println();
        System.out.println("El estado actual del tren es el siguiente:");
        asientos.print();
        System.out.println();
        System.out.println("Introduzca el precio del billete de tren");
        precioBillete = Scanner.nextFloat();
        billetes.init(precioBillete);
        options();

    }
    
    public static void options(){
        do {
            menu();
            int option = Scanner.nextInt();
            if (option == 1) {
                billetes.comprar();           
            }else if (option == 2) {
                billetes.change();
            }else if (option == 3) {
                asientos.print();
            }else if (option == 4) {
                finish = false;
            }
        } while (finish == true);
    }
    
    
    //printOptions prints main menu options
    public static void menu() {
        System.out.println("");
        System.out.println("Elija una de las siguientes opciones: ");
        System.out.println("1. Comprar billetes");
        System.out.println("2. Cambiar billetes");
        System.out.println("3. Mostrar asientos");
        System.out.println("4. Salir del programa");
        System.out.println("Introduzca el número de la opción que desea elegir: ");
    }   
}
