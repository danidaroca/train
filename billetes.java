import java.util.Scanner;

public class billetes {
    static int numBilletes, billetesMenores;
    static float precioBillete = 0;    
    static Scanner Scanner = new Scanner(System.in);
    static  int descuentoMenores = 50;
    
    public static void init(float precio){
        precioBillete = precio;
    }
    
    //comprar: muestra el menu de compra y llama a una función diferente por cada opcion seleccionada
    public static void comprar(){
        boolean userError;
        do {
            userError = false;
            System.out.println("¿Cuántos billetes desea comprar? Recuerde que deberá comprar al menos 1 billete y que quedan "+ asientos.countFree() +" disponibles");
            numBilletes = Scanner.nextInt();
            if (numBilletes > asientos.countFree() || numBilletes <= 0) {
                System.out.println("El numero de billetes de ser mayor que 0 y menor o igual al numero de asientos libres("+asientos.countFree()+")");
                userError = true;
            }
        } while (userError);
        do {
            userError = false;
            System.out.println("¿Cuántos billetes son para menores? Recuerde que al menos un adulto debe acompañar a los viajeros menores de edad");
            billetesMenores = Scanner.nextInt();
            if (billetesMenores > numBilletes) {
                System.out.println("Error, el numero de billetes para menores no puede ser mayor al numero de billetes solicitado.");            
                userError = true;
            }else if (billetesMenores == numBilletes) {
                System.out.println("Debe haber almenos un mayor de edad.");            
                userError = true;
            }
        } while (userError);
        

        printOptions();
        int option = Scanner.nextInt();
        if (option == 1) {
            manual();
            factura();
            app.options();
        }else if (option == 2) {
            auto();
            factura();
            app.options();
        }else if (option == 3) {
            autoCont();
            factura();
            app.options();
        }else if (option == 4) {
            app.options();
        }
        
    }
    //manual: pregunta al usuario la informacion necesaria y reserva asientos uno a uno
    public static void manual(){
        int count = 1;
        boolean userError;
        int row,col;
        asientos.print();
        do {
            do {
                userError = false;
                System.out.print("en que fila quiere el asiento: ");
                row = Scanner.nextInt()-1;
                if (!Errores.IsValidRow(row)) {
                    System.out.println("El número de fila del asiento debe ser mayor que 0 y menor o igual al numero de filas del tren");
                    userError = true;
                }
            } while (userError);
            do {
                userError = false;
                System.out.print("en que columna quiere el asiento: ");
                col = Scanner.nextInt()-1;
                if (!Errores.IsValidCol(col)) {
                    System.out.println("El número de columna del asiento debe ser mayor que 0 y menor o igual al numero de columnas del tren");
                    userError = true;
                }
            } while (userError);

            if (asientos.isFree(row, col)) {
                asientos.reserve(row, col);
                count ++;
            }else{
                System.out.println("Este asiento ya esta ocupado, por favor introduzca otro");
            }
            asientos.print();
        } while (count <= numBilletes);
    }
    //auto: reserva automaticamente "n" billetes
    public static void auto(){
        asientos.reserveN(numBilletes);
    }
    //autoCont: reserva "n" billetes contiguos automaticamente
    public static void autoCont(){
        asientos.reserveNCont(numBilletes);
    }

    
    //change: cambia los "n" billetes indicados por el usuario
    @SuppressWarnings("empty-statement")
    public static void change(){
        boolean err = false;
        boolean userError;
        int count = 1;
        int fromRow,fromCol,toRow,toCol, billetesCambiar;       
        do {
            userError = false;
            System.out.print("Cuantos billetes desea cambiar: ");
            billetesCambiar = Scanner.nextInt();
            if (billetesCambiar > asientos.countFree() || billetesCambiar <= 0) {
                System.out.println("El numero de billetes que desea cambiar debe ser mayor que 0 y menor o igual al numero de asientos libres("+asientos.countFree()+")");
                userError = true;
            }else if (billetesCambiar > asientos.countReserved()) {
                userError = true;
                System.out.println("Error, el número de billetes que desea cambiar debe ser menor o igual al número de asientos reservados ");
            }
        } while (userError);
     
        System.out.println("Se va a proceder a cambiar " + billetesCambiar + " asientos");
        do {
            do{
                userError = false;
                System.out.println("Seleccione la fila del asiento que desea cambiar");
                fromRow = Scanner.nextInt()-1;
                if (!Errores.IsValidRow(fromRow)) {
                    System.out.println("El número de fila del asiento debe ser mayor que 0 y menor o igual al numero de filas del tren");
                    userError = true;
                }                
            }while (userError);
            
            do {
                userError = false;
                System.out.println("Seleccione la columna del asiento que desea cambiar");
                fromCol = Scanner.nextInt()-1;
                if (!Errores.IsValidCol(fromCol)) {
                    System.out.println("El número de columna del asiento debe ser mayor que 0 y menor o igual al numero de columnas del tren");
                    userError = true;
                }
            } while (userError);

            do {
                userError = false;
                System.out.println("Seleccione la fila del asiento al que desea cambiarse");
                toRow = Scanner.nextInt()-1;
                if (!Errores.IsValidRow(toRow)) {
                    System.out.println("El número de columna del asiento debe ser mayor que 0 y menor o igual al numero de filas del tren");
                    userError = true;
                }
            } while (userError);

            do {
                userError = false;
                System.out.println("Seleccione la columna del asiento al que desea cambiarse");
                toCol = Scanner.nextInt()-1;
                if (!Errores.IsValidCol(toCol)) {
                    System.out.println("El número de fila del asiento debe ser mayor que 0 y menor o igual al numero de columnas del tren");
                    userError = true;
                }
            } while (userError);
        
            asientos.change(fromRow, fromCol, toRow, toCol);            
            count ++;
        } while (count <= billetesCambiar && !err);
        
    }
    //factura: se encarga de hacer la factura
    public static void factura(){
        System.out.println("El numero de billetes comprados es: " + numBilletes);
        
        System.out.println("Precio por billete: "+ precioBillete);
        System.out.println("Descuento aplicado por número de billetes: Ninguno");
        System.out.println("Descuento aplicado por billetes para menores: " + descuentoMenores + "%");
        System.out.println("Precio total: " + precioTotal());

    }

    private static float precioTotal(){
        float precioBilleteAdulto, precioBilleteMenor;
        int billetesAdulto = numBilletes - billetesMenores;
        precioBilleteAdulto = precioBillete;
        precioBilleteMenor = precioBillete-(precioBillete * descuentoMenores)/100;         
        return (billetesAdulto * precioBilleteAdulto) + (billetesMenores * precioBilleteMenor);
    }

    private static void printOptions(){
        System.out.println("Elija una opción de compra de billetes");
        System.out.println("1. Selección manual de asientos");
        System.out.println("2. Selección automática de asientos");
        System.out.println("3. Selección automática de asientos contiguos");
        System.out.println("4. Salir del menú de compra");
        System.out.println("Introduzca el número de la opción de compra que desea elegir: ");
    }
}
