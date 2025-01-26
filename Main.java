import java.util.Scanner;

public class Main {
    private static final int rows = 5;
    private static final int cols = 4;  
    
    private static String[][] train = new String[rows][cols];
    private static Scanner KAKA = new Scanner(System.in);
    private static float precioBilletes;
    private static int nbilletes, menores, menuOption, subMenuOption, line, colum, count;
    public static void main(String[] args){
        count = 1;
        System.out.println("Bienvenido al software de gestión de billetes");
        System.out.println();
        System.out.println("El estado actual del tren es el siguiente:");
        initTrain();
        printTrain();
        System.out.print("Introduzca el precio del billete: ");
        precioBilletes = KAKA.nextFloat();
        while (precioBilletes <= 0) {
            System.out.print("Error, pruebe otra vez: ");
            precioBilletes = KAKA.nextFloat();
        }
        System.out.println("Que desa hacer:");
        menuOption = KAKA.nextInt();
        while (4 < menuOption || menuOption <= 0) {
            menuOption = KAKA.nextInt();
        }
        
        
        if (menuOption == 1) {
            System.out.print("Cuantos billetes desea comprar: ");
            nbilletes = KAKA.nextInt();
            while (nbilletes < 0) {
                nbilletes = KAKA.nextInt();
            }
            System.out.print("Cuantos de esos billetes son para menores: ");
            menores = KAKA.nextInt();
            while (menores > nbilletes) {
                menores = KAKA.nextInt();
            }
            subMenuOption = KAKA.nextInt();
            while (subMenuOption > 4 || subMenuOption <= 0) {
                subMenuOption = KAKA.nextInt();
            }
            if (subMenuOption == 1) {
                printTrain();
                do {
                    System.out.print("En que fila quiere el asiento: ");
                    line = KAKA.nextInt();
                    System.out.print("En que columna quiere el asiento: ");
                    colum = KAKA.nextInt();
                    reserveSeat(line, colum);
                    printTrain();
                    count ++;
                } while (count <= nbilletes);
            }
            
            if (subMenuOption == 2) {
                reserveNseats(nbilletes);
                printTrain();
            }
            if (subMenuOption == 3) {
                
            }
            if (subMenuOption == 4) {
                
            }
        }
        if (menuOption == 2){

        }
        if (menuOption == 3) {
            
        }
        if (menuOption == 4) {
            
        }
    }    
    
    
    
    //asigna el valor inicial a la matriz
    public static void initTrain(){
        for (int i = 0; i < train.length; i++){
            for (int j = 0; j < train[i].length; j++){
                train[i][j] = "L";
            }
        }    
    }
    //Printea el tren
    public static void printTrain(){
        for (int i = 0; i < train.length; i++){
            for (int j = 0; j < train[i].length; j++){
                if (train[i][j] == "L"){
                    System.out.print("L ");
                }else System.out.print("O ");
                
            }
            System.out.println();
        }
    }
    //revisa si el asiento esta libre
    public static boolean isFree(int row,int col){
         
        if (train[row][col] == "L"){
            return true;
        }
        else{
            return false;
        }
    }    
    //Reserva un sitio            
    public static void reserveSeat(int row, int col){
        train[row][col] = "O";

    }     
    //reserva "n" sitios 
    public static void reserveNseats(int n){
        int count = 0;
        for (int i = 0; i < train.length; i++){
            for (int j = 0; j < train[i].length; j++){
                
                if (isFree(i, j) == true){
                    reserveSeat(i, j);
                    count ++;
                }
                if (count == n){
                    return;
                }

    
            }
        } 


        
   
    }    

    
}
