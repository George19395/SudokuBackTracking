import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReaderClass {
	
	File file = new File("D:\\C_Drive_Desktop_items\\Git-repos\\SudokuBackTracking\\src\\sudokus.txt");
	BufferedReader in;
	private Map<Integer,int[][]> puzzles = new HashMap< Integer,int[][]>();
	public Map<Integer,int[][]> readFile() throws IOException {
		in = new BufferedReader(new FileReader(file));
		
		String grid="";
		String s;
//		System.out.println(grid.charAt(0));
		int[][] a=new int[9][9];
		
//		for(int i=0;i<9;i++) {
//			for(int j=0;j<9;j++) {
//				a[i][j]=Character.getNumericValue(grid.charAt(9*i + j));
//				System.out.print(a[i][j]);
//			}
//			System.out.println();
//		}
		
		int value=0;
		while((s=in.readLine())!=null) {
			if(s.startsWith("Grid")) {
				
				value =Integer.parseInt(s.charAt(5)+""+s.charAt(6));
//				System.out.println(value);
				
			}
			else {
				grid+=s;
			}
			
			if(grid.length()==81) {
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						a[i][j]=Character.getNumericValue(grid.charAt(9*i + j));
//						System.out.print(a[i][j]);
					}
//					System.out.println();
				}
				grid="";
				puzzles.put(value, a);
				a=new int[9][9];
			}

		}
		return puzzles;
	}
	
	public static void main(String[] args) throws IOException {
		FileReaderClass f = new FileReaderClass();
		f.readFile();
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(f.readFile().get(2)[i][j]);
			}
			System.out.println();
		}
//		f.puzzles.get(1);
	}
	

}
