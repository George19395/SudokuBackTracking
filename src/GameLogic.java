import java.util.HashSet;

public class GameLogic {


	private static boolean notInRow(int[][] board,int row) {
		HashSet<Integer> st = new HashSet();
		for(int i=0;i<9;i++) {
			if(st.contains(board[row][i])) {
				return false;
			}
			if(board[row][i]!=0) {
				st.add(board[row][i]);
			}
		}
		return true;
	}

	private static boolean notInCol(int[][] board,int col) {
		HashSet<Integer> st = new HashSet();
		for(int i=0;i<9;i++) {
			if(st.contains(board[i][col])) {
				return false;
			}
//			if(board[i][col]!=0) {
//				st.add(board[i][col]);
//			}
			st.add(board[i][col]);
		}
		return true;
	}


	private static boolean notInBox(int board[][],
			int startRow,
			int startCol)
	{
		HashSet<Integer> st = new HashSet<>();

		for(int row = 0; row < 3; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				int curr = board[row + startRow][col + startCol];

				// If already encountered before, return
				// false
				if (st.contains(curr))
					return false;

				// If it is not an empty cell,
				// insert value at current cell in set
				if (curr != 0)
					st.add(curr);
			}
		}
		return true;
	}

	private static boolean isValid(int board[][], int row,
			int col)
	{
		return notInRow(board, row) && notInCol(board, col) &&
				notInBox(board, row - row % 3, col - col % 3);
	}
	
	public static boolean isValidConfig(int board[][], int n)
	{
	    for(int i = 0; i < n; i++)
	    {
	        for(int j = 0; j < n; j++)
	        {
	             
	            // If current row or current column or
	            // current 3x3 box is not valid, return
	            // false
	            if (!isValid(board, i, j))
	                return false;
	        }
	    }
	    return true;
	}


}
