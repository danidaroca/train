public class Errores {
    public static boolean IsValidRow(int row){
        boolean isValid = true;
        if (row > asientos.rows()) {
            isValid = false; 
        }else if (row <= 0) {
            isValid = false;
        }
        return isValid;
    }
    public static boolean IsValidCol(int col){
        boolean userError = false;
        if (col > asientos.cols()) {
            userError = true; 
        }else if (col <= 0) {
            userError = true;
        }
        return userError;
    }

    
}

