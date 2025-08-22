package sudoku;

public class Sudoku implements SudokuSolver{
    private int[][] board;

    public Sudoku(){
        board = new int[9][9];
       
    }

    public static void main(String[] args) {
        Sudoku s= new Sudoku();
        SudokuGraphics g = new SudokuGraphics(s);
    }
    public boolean solve(){
        return solve(0, 0);
    }
    private boolean solve(int r, int c){
        for (int i = 0; i < 9; i++) {
            for(int j=0; j<9; j++){
                if (board[i][j] != 0){
                    if (!isValid(i, j)) {
                        return false;
                    }
                }
            }
        }

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
        if (board[r][c]!=0){
            return solve(r1, c1);
        }
        
        for (int i=1; i<10;i++){
            board[r][c]=i;
            if(isValid(r,c)){
                if (solve(r1,c1)){
                    return true;
                }
            }
        }
        board[r][c]= 0;
        return false;
        
    }

    @Override
    public boolean isValid(int r, int c) {
        if (!(r>=0 && r<=8 && c>=0 && c <=8)){
            throw new IndexOutOfBoundsException("r eller c eller digit är för stor eller för liten");
        }
        int n = board[r][c];
        for (int i=0; i<9;i++){
            if (i!= c && board[r][i]==n){
                return false;
            }
            if(i!= r && board[i][c]==n){
                return false;
            }
        }
        int r1= (r/3)*3 ;
        int c1=(c/3)*3 ;
        for (int j=0; j<3; j++){
            for (int k=0; k<3;k++){
                if ( board[r1+j][c1+k]==n){
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
        if (row>=0 && row<=8 && col>=0 && col <=8){
            if (digit>=1 && digit<=9){
                board[row][ col]= digit;
            }else {
                throw new IllegalArgumentException("digit är för stor eller för liten");
            }

        }else {
            throw new IndexOutOfBoundsException("row eller col är för stor eller för liten");
        }
    }
    @Override
    public int get(int row, int col){

        if (row>=0 && row<=8 && col>=0 && col <=8){
            int copy = board[row][col];
            return copy;
        }else {
            throw new IndexOutOfBoundsException("row eller col är för stor eller för liten");
        }
    }
    @Override
    public void clear(int row, int col){
        if (row>=0 && row<=8 && col>=0 && col <=8){
            board[row][col]=0;
        }else {
            throw new IndexOutOfBoundsException("row eller col är för stor eller för liten");
        }
    }
    @Override
    public void clearAll(){
        int[][] nyboard= new int[9][9];
        board= nyboard;
        
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

        int[][] newboard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = board[i][j];
                if (val < 0 || val > 9) {
                    throw new IllegalArgumentException("värdena måste vara mellan 0-9");
                }
                newboard[i][j] = val;
            }
    }
        board = newboard;

    }
    @Override
    public int[][] getGrid(){
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 9);
        }
        return copy;
    }

   
}



