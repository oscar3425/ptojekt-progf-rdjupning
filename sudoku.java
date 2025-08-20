package sudoku;

public class Sudoku implements SudokuSolver{
    public int[][] rut;
    public int sumC=0;

    public Sudoku(){
        rut = new int[9][9];
       
    }

    public static void main(String[] args) {
        Sudoku s= new Sudoku();
        boolean t= s.solve();
        System.out.println(t);
        if (t){
        for (int i=0; i<9; i++){
            System.out.println(" ");
            for (int j=0;j<9;j++){
                System.out.print(s.rut[i][j]+ " ");
            }
        }
        }
        
    }
    public boolean solve(){
        return solve(0, 0);
    }
    private boolean solve(int r, int c){
        if (r==9){
            return true;
        }
        if (r<0){
            return false;
        }
        
        int r1=r; 
        int c1= c+1;
        if (c==8){
            r1=r+1;
            c1=0;
        }
        if (rut[r][c]!=0){
            return solve(r1, c1);
        }
        
        for (int i=1; i<10;i++){
            sumC++;
            rut[r][c]=i;
            if(isValid(r,c)){
                if (solve(r1,c1)){
                    return true;
                }
            }
        }
        rut[r][c]= 0;
        return false;
        
    }

    @Override
    public boolean isValid(int r, int c) {
        if (!(r>=0 && r<=8 && c>=0 && c <=8)){
            throw new IndexOutOfBoundsException("r eller c eller digit är för stor eller för liten");
        }
        int n = rut[r][c];
        for (int i=0; i<9;i++){
            if (i!= c && rut[r][i]==n){
                return false;
            }
            if(i!= r && rut[i][c]==n){
                return false;
            }
        }
        int r1= (r/3)*3 ;
        int c1=(c/3)*3 ;
        for (int j=0; j<3; j++){
            for (int k=0; k<3;k++){
                if ( rut[r1+j][c1+k]==n){
                    if ((r1+j)!=r || (c1+k)!=c){
                    return false;
                    }
                }
            }
        }   
        return true;  
    }
    @Override
    public void set(int row, int col, int digit){
        if (row>=0 && row<=8 && col>=0 && col <=8 && digit>=1 && digit<=9){
            rut[row][ col]= digit;
        }else {
            throw new IndexOutOfBoundsException("row eller col eller digit är för stor eller för liten");
        }
    }
    @Override
    public int get(int row, int col){

        if (row>=0 && row<=8 && col>=0 && col <=8){
            return rut[row][ col];
        }else {
            throw new IndexOutOfBoundsException("row eller col är för stor eller för liten");
        }
    }
    @Override
    public void clear(int row, int col){
        if (row>=0 && row<=8 && col>=0 && col <=8){
            rut[row][col]=0;
        }else {
            throw new IndexOutOfBoundsException("row eller col är för stor eller för liten");
        }
    }
    @Override
    public void clearAll(){
        int[][] nyRut= new int[9][9];
        rut= nyRut;
        
    }
    @Override 
    public boolean isAllValid(){
        for (int i=0; i<9; i++){
            for (int j=0;j<9;j++){
                if(! isValid(i,j)){
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void setGrid(int[][] board){
        if (board.length != 9) {
        throw new IllegalArgumentException("matrisen måste vara 9x9");
    }
    for (int i = 0; i < 9; i++) {
        if (board[i].length != 9) {
            throw new IllegalArgumentException("matrisen måste vara 9x9");
        }
    }

        int[][] newRut = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = board[i][j];
                if (val < 0 || val > 9) {
                    throw new IllegalArgumentException("värdena måste vara mellan 0-9");
                }
                newRut[i][j] = val;
            }
    }
        rut = newRut;

    }
    @Override
    public int[][] getGrid(){
        return rut;
    }

   
}



