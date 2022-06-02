/*
 * this is the sudoku solver class using backtracking to find solutions
 * also used by GUI to accommodate the automatic solve button.
 */
public class Backtracking {
	
	int[][] grid;
////	SudokuBoards b=new SudokuBoards();
//	int board[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
//            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
//            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
//            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
//            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
//            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
//            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
//            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
//            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
	
	
	public Backtracking() {
//		this.grid=grid;
//		if(sudokuSolver(grid,0,0)) {
//			System.out.println("Has Solution");
////			printBoard(board);
//		}
//		else {
//			System.out.println("No Solution");
//		}
	}
	
	
	/*
	 * MAIN METHOD TO BE USED WHEN we want to run the backtracking algorithm in the console
	 */
//	public static void main(String[] args) {
//		
//		
//		SudokuBoards b=new SudokuBoards();
//		Main main = new Main(b.board1);
//		if(main.sudokuSolver(b.board1,0,0)) {
//			System.out.println("Has Solution");
//			main.printBoard(b.board1);
//		}
//		else {
//			System.out.println("No Solution");
//		}
//		
//	}
	
	
	public boolean sudokuSolver(int grid[][], int row,
            int col) {
		
		/*
		 * check if we reached the end of matrix row8,col9
		 * if end return true
		 */
		if(row==8 && col==9) {
			return true;
		}
		
		/*
		 * if we go over the last column change rows
		 * reset col num to 0
		 */
		if(col==9) {
			row++;
			col=0;
		}
		
		if(grid[row][col]>0) {
			return sudokuSolver(grid,row,col+1);
		}
		
		for(int n=1;n<=9;n++) {
			if(isValid(grid,col,row,n)) {
				grid[row][col]=n;
				
				if(sudokuSolver(grid,row,col+1)) {
					return true;
				}
			}
			grid[row][col]=0;
		}
		
		
     return false;
	}
	
	private boolean checkRow(int[][] grid,int row,int n) {
		
		for(int i=0; i<9; i++) {
			if(grid[row][i]==n) {
				return false;
			}
		}
		return true;	
	}
	
	private boolean checkCol(int[][] grid,int col, int n) {
		for(int i=0; i<9; i++) {
			if(grid[i][col]==n) {
				return false;
			}
		}
		return true;
	}
	
	
	private boolean check3x3(int[][] grid,int col,int row, int n) {
		//get startrow and col using row - row%3
		// take current   ror - row%3     0) 0 - 0%3 = 0 , 1 - 1%3 = 0, 2 - 2%3 = 0, 3 - 3%3 = 3, 
		int startRow= row - row%3;
		int startCol = col - col%3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++){
				if(grid[i+startRow][j+startCol]==n) { //start row +
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValid(int[][] grid,int col,int row, int n) {
		if(check3x3(grid,col,row,n) && checkCol(grid,col,n) && checkRow(grid,row,n)) {
			return true;
		}
		
		return false;
		
	}
	
	
	public void printBoard(int grid[][]) {
		for(int i=0; i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(grid[i][j]+",");
			}
			System.out.println();
		}
	}
	
	public String printBoardString(int grid[][]) {
		String a="";
		for(int i=0; i<9;i++) {
			for(int j=0;j<9;j++) {
				a=a+grid[i][j];
				System.out.print(grid[i][j]+",");
			}
			System.out.println();
		}
		return a;
	}

}
