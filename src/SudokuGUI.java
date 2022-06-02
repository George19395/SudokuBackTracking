import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuGUI {
	JFrame frame;
	GridPanel midPanel;
	GridLayout general = new GridLayout(3,3,2,2);
	JPanel panel1 = new JPanel(general);
	JPanel panel2 = new JPanel(general);
	JPanel panel3 = new JPanel(general);
	JPanel panel4 = new JPanel(general);
	JPanel panel5 = new JPanel(general);
	JPanel panel6 = new JPanel(general);
	JPanel panel7 = new JPanel(general);
	JPanel panel8 = new JPanel(general);
	JPanel panel9 = new JPanel(general);
	JTextField[] textField = new JTextField[81];	
	Dimension fDimension = new Dimension(600,600);
	
	private int currentBoardNum=0;
	private int[][] currentBoard;
	private int[][] currentBoardReverse;
	
	SudokuBoards sb = new SudokuBoards();
//	public int[][] board = sb.copyArray(sb.newBoard(sb.board1));

	Backtracking m = new Backtracking();
	
	

	
	private int[][] selectBoard() {
		Random rand = new Random();
		currentBoardNum= rand.nextInt(50);
		currentBoard=sb.copyArray(sb.boards.get(currentBoardNum));
		currentBoardReverse=sb.newBoard(currentBoard);
		System.out.println(currentBoardNum);
		return currentBoardReverse;
	}

	/*
	 * Constructor
	 * initialise all the textfields and adds keyListener to check that only 1 letter is allowed per textfield
	 * Call the GUI method which creates THE UI.
	 */
	public SudokuGUI() throws IOException{
		selectBoard();
		
		for (int y = 0; y < 81; y++){
			textField[y] = new JTextField();
			textField[y].setForeground(Color.RED);
			textField[y].setDocument(new JTextFieldLimit(5));
			int row= y/9;
			int col = y%9;
			textField[y].addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {

					char c = e.getKeyChar();

					if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume();  // if it's not a number, ignore the event
					}
					else {
//						System.out.println("CHAR: "+c);
						int cI= Character.getNumericValue(c);
						sb.updateBoard(currentBoardReverse,row,col,cI);
						m.printBoardString(currentBoardReverse);
					}
					
				}
			});
		}
		Gui(currentBoardReverse);
	}

	/*
	 * Fill the GUI Board with the predetermined sudoku values used at the creation of GUI.
	 * @param sudoku board.
	 */
	private void fillGUI(int grid[][]) {
		int counter=0;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				
				if(grid[i][j]!=0) {
					textField[counter].setForeground(Color.BLACK);
					textField[counter].setFont(new Font("Verdana",Font.BOLD,14));
					textField[counter].setEditable(false);
				}
//				textField[counter].setFont(new Font("Verdana",14));
				textField[counter].setForeground(Color.DARK_GRAY);
				textField[counter++].setText(grid[i][j]+"");
				
			}
		}
	}
	
	/*
	 * fill GUI at any time with values of the grid parsed.
	 * @param sudoku board to fill gui from
	 * @param id to distinguish from fillGUI method used only at creation
	 */
	private void fillGUI(int grid[][],int id) {
		int counter=0;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				textField[counter].setForeground(Color.DARK_GRAY);
				textField[counter++].setText(grid[i][j]+"");
			}
		}
	}
	
	/*
	 * Method where the GUI is created.
	 * @param sudoku board
	 */
	private void Gui(int grid[][]) {
		frame = new JFrame("Sudoku");
		frame.setMinimumSize(fDimension);
		frame.setMaximumSize(fDimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		midPanel = new GridPanel(new GridLayout(3,3,7,7)) ;

		fillGUI(grid);
		
		for(int i=0; i<9;i++) {
			panel1.add(textField[i]);
			panel2.add(textField[i+9*1]);
			panel3.add(textField[i+9*2]);
			panel4.add(textField[i+9*3]);
			panel5.add(textField[i+9*4]);
			panel6.add(textField[i+9*5]);
			panel7.add(textField[i+9*6]);
			panel8.add(textField[i+9*7]);
			panel9.add(textField[i+9*8]);
		}

		midPanel.add(panel1);
		midPanel.add(panel2);
		midPanel.add(panel3);
		midPanel.add(panel4);
		midPanel.add(panel5);
		midPanel.add(panel6);
		midPanel.add(panel7);
		midPanel.add(panel8);
		midPanel.add(panel9);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.weighty = 1; // space between columns
		gridBagConstraints.weightx = 1;// space between rows
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		
		//add label to top of main panel
		JLabel topLabel = new JLabel("Sudoku Solver", JLabel.CENTER);
		topLabel.setOpaque(true);
		topLabel.setBackground(Color.BLACK);
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		gridBagConstraints.gridx = 0; // what row it starts from
		gridBagConstraints.gridy = 0; // what column it starts from
		gridBagConstraints.gridwidth = 2;//number of columns it takes
		gridBagConstraints.weighty = 0.05;
		gridBagConstraints.fill = GridBagConstraints.BOTH; //resize to fit screen
		mainPanel.add(topLabel,gridBagConstraints);
		
		//add grid panel
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		mainPanel.add(midPanel, gridBagConstraints); 
		
		// add submit button 
		JButton submit=new JButton("Submit");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.weighty = 0.05;
		gridBagConstraints.insets=new Insets(0,10,0,10); //top left bottom right
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; //doesnt fill to top since we anchored to the PAGE_END
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		mainPanel.add(submit, gridBagConstraints); 
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentBoard = sb.newBoard(currentBoardReverse);
				m.printBoardString(currentBoard);
				if(!GameLogic.isValidConfig(currentBoard,9)) {
					System.out.println("PUZZLE IS INCORRECT");
					JOptionPane.showMessageDialog(frame, "PUZZLE IS INCORECT! \n            TRY AGAIN");
				}
				else {
					System.out.println("CORRECT SOLUTION");
					JOptionPane.showMessageDialog(frame, "     CORRECT SOLUTION! \n              WELL DONE");
				}
				
			}
		});
		
		//add solve button
		JButton solve=new JButton("Solve");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.weighty = 0.05; // the weight of the button y dimension in this case want it weigh smaller than midPanel
		gridBagConstraints.insets=new Insets(0,0,0,10); //top left bottom right
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		mainPanel.add(solve, gridBagConstraints);
		solve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int[][] originalBoard = sb.copyArray(sb.boards.get(currentBoardNum));
				m.sudokuSolver(originalBoard,0,0);
				m.printBoardString(originalBoard);
				currentBoardReverse =sb.newBoard(originalBoard);
				fillGUI(currentBoardReverse,2); // need to change it to not mark everything bold and editable=true
				
				
			}
		});
		
		//add reset button
		JButton reset=new JButton("Reset");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.weighty = 0.05; // the weight of the button y dimension in this case want it weigh smaller than midPanel
		gridBagConstraints.insets=new Insets(0,10,10,10); //top left bottom right
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		mainPanel.add(reset, gridBagConstraints);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("Hello");
				currentBoard=sb.copyArray((sb.boards.get(currentBoardNum)));
				currentBoardReverse=sb.copyArray(sb.newBoard(currentBoard)); //
//				m.printBoardString(currentBoard);
				for(int i=0;i<81;i++) {
					textField[i].setFont(new Font("Verdana",Font.PLAIN,11));
					textField[i].setForeground(Color.GRAY);
					textField[i].setEditable(true);
				}
				fillGUI(currentBoardReverse);
				
			}
		});
		
		//add solve button
		JButton newPuzzle=new JButton("newPuzzle");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.weighty = 0.05; // the weight of the button y dimension in this case want it weigh smaller than midPanel
		gridBagConstraints.insets=new Insets(0,0,10,10); //top left bottom right
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		mainPanel.add(newPuzzle, gridBagConstraints); 
		newPuzzle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("Hello");

				Random rand = new Random();
//				int index= rand.nextInt(50);
				
				currentBoardNum = rand.nextInt(50);
				System.out.println(currentBoardNum);
				
				currentBoard = sb.copyArray((sb.boards.get(currentBoardNum)));
				currentBoardReverse=sb.copyArray(sb.newBoard(currentBoard)); //
//				m.printBoardString(currentBoard);
				
				for(int i=0;i<81;i++) {
					textField[i].setFont(new Font("Verdana",Font.PLAIN,11));
					textField[i].setForeground(Color.GRAY);
					textField[i].setEditable(true);
				}
				fillGUI(currentBoardReverse);
				
			}
		});



		frame.getContentPane().add(mainPanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();

	}

}
