import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class SudokuBoards {
	
	
	int board1[][] = { 	{ 3, 0, 6, 5, 0, 8, 4, 0, 0 },
			            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
			            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
			            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
			            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
			            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
			            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
			            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
			            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
	
	
	int board2[][] = {  {0, 0, 0, 0, 0, 0, 0, 0, 7},
						{0 , 0, 0, 0, 7, 0, 3, 0,8},
						{0 ,0 ,0, 5 ,0 ,4 ,0, 6 ,0},
						{0 ,0, 0, 0 ,0 ,0 ,0 ,8 ,0},
						{7 ,1 ,0 ,0 ,9 ,0 ,0 ,0 ,5},
						{8 ,0 ,0 ,0 ,1 ,5 ,9 ,0 ,0},
						{3 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
						{0 ,0 ,8 ,9 ,4 ,0 ,6 ,3 ,0},
						{0 ,2 ,7 ,6 ,0 ,3 ,0 ,0 ,0} };
	
	
	int board1solution[][] = {  {3,1,6,5,7,8,4,9,2},
								{5,2,9,1,3,4,7,6,8},
								{4,8,7,6,2,9,5,3,1},
								{2,6,3,4,1,5,9,8,7},
								{9,7,4,8,6,3,1,2,5},
								{8,5,1,7,9,2,6,4,3},
								{1,3,8,9,4,7,2,5,6},
								{6,9,2,3,5,1,8,7,4},
								{7,4,5,2,8,6,3,1,9}};
	
	
	FileReaderClass file = new FileReaderClass();
	Map<Integer,int[][]> boards;
	
	ArrayList<int[][]> a = new ArrayList();
	public SudokuBoards() throws IOException {
		boards=file.readFile();
		a.add(board1);
		a.add(board2);
	}
	/*
	 * reverse any board so that it can be presented in the GUI as normal
	 * 3x3 structure of GUI parses values in wrong positions if this is not done.
	 * @sudoku board
	 */
	public int[][] newBoard(int[][] board){
		int[][] newb=new int[9][9];
		int row=0;
		int col=0;
		for(int i=0;i<9;i++) {
			int count =0;
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
					if(i>2 && i<6) {
						row =(((i*3)+(j*9)+k)/9) +2;
					}
					else if(i>5) {
						row =(((i*3)+(j*9)+k)/9) +4;
					}
					else {
						row =((i*3)+(j*9)+k)/9 ;
					}
					
					col = ((i*3)+(j*9)+k)%9 ;
					newb[i][count++]=board[row][col];	
				}	
			}
		}
		return newb;
	}
	

	/*
	 * update board to add value to specific location
	 * @param sudoku board
	 * @param row
	 * @param col
	 * @param value to parse.
	 */
	public void updateBoard(int board[][],int x,int y,int value) {
		board[x][y] = value;	
	}
	
	
	/*
	 * Method to copy 2d array by value instead of reference to original 2d array
	 * Reference to original 2d array was changing the original 2d array too which was causing issues on the reset method of the puzzle ->
	 * 					since original didn't exist anymore
	 * @param array you want to copy from.
	 */
	public int[][] copyArray(int grid[][]){
		
		int[][] arr = new int[9][9];
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[i].length; j++) {
				arr[i][j]=grid[i][j];
			}
		}	
		return arr;
	}
	
}
