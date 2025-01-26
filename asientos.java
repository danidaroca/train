public class asientos {
    private static final int rows = 5;
    private static final int cols = 4;  
    
    private static char[][] asientos = new char[rows][cols];
        
    //init: inicializa como libres todos los asientos
    public static void init(){
        for (int i = 0; i < asientos.length; i++){
            for (int j = 0; j < asientos[i].length; j++){
                asientos[i][j] = 'L';
            }
        }    
    }

    public static int rows(){
        return rows;
    }

    public static int cols(){
        return cols;
    }

    //reserve: reserva el asiento especificado            
    public static void reserve(int row, int col){
        asientos[row][col] = 'O';
    }

    //change: libera un asiento (from) y ocupa otro(to)
    public static void change(int fromRow, int fromCol, int toRow, int toCol){
        asientos[fromRow][fromCol] = 'L';
        asientos[toRow][toCol] = 'O';
    }

    //print: imprime los asientos
    public static void print() {
        System.out.print("        ");
        // Print column headers
        for (int i=0; i<cols; i++) {
            System.out.print((i+1)+"  ");
        }
        for (int i=0; i<rows; i++) {
            System.out.println();
            System.out.print("Fila "+(i+1)+"  ");
            for (int j=0; j<cols; j++) {
                if (isFree(i, j)){
                    System.out.print("\u001B[32m"+asientos[i][j]+"\u001B[0m  ");
                }else{
                    System.out.print("\u001B[31m"+asientos[i][j]+"\u001B[0m  ");
                }
                
            }
            System.out.println();
        }
    }

    //reserveN: reserva los "n" primeros asientos libres
    public static void reserveN(int n){
        int count = 0;
        for (int i = 0; i < asientos.length; i++){
            for (int j = 0; j < asientos[i].length; j++){
                
                if (isFree(i, j) == true){
                    reserve(i, j);
                    count ++;
                }
                if (count == n){
                    return;
                }    
            }
        }
    }
    


    //isFree: devuelve "true" si el asiento esta libre, "false" en caso contrario
    public static boolean isFree(int row,int col){        
        if (asientos[row][col] == 'L'){
            return true;
        }
        else{
            return false;
        }
    }

    //countFree: devuelve el número de asientos libres
    public static int countFree(){
        int count = 0;
        for (int i = 0; i < asientos.length; i++){
            for (int j = 0; j < asientos[i].length; j++){
                if (isFree(i, j)) {
                    count++;
                }
            }
        }
        return count;    
    }

    //countReserved: devuelve el número de asientos ocupados
    public static int countReserved(){
        return (rows * cols) - countFree(); 
    }

    public static void reserveNCont(int n){
        boolean finish = false;
        for (int i = 0; i < asientos.length && !finish; i++){
            for (int j = 0; j < asientos[i].length && !finish; j++){
                if (nextNAreFree(i, j, n)) {
                    reserveNfrom(i, j, n);
                    finish = true;
                }
            }  
        }
        if (!finish){
            System.out.println("No se han encontrado "+n+" asientos contiguos en el tren.");
        }
    }
    
    private static boolean nextNAreFree(int row, int col, int n){
        boolean finish = false;
        int count = 0;
        for (int i = col; i < cols && !finish && count <= n; i++){
            if (isFree(row, i)) {
                count++;
            }else{
                finish = true;
            }
        }
        if (count >= n) {
            return true;
        }else{
            return false;
        }
    }

    private static void reserveNfrom(int row, int col, int n){
        int count = 0;
        for (int i = col; i < cols && count < n; i++){
            reserve(row, i);
            System.out.println("Se ha reservado el asiento de la fila "+(row+1)+" y de la columna "+(i+1));
            count ++;
        }
    }
}

