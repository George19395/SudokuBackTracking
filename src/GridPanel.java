
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

/* Nested class for the grid panel used in the GUI */
	public class GridPanel extends JPanel{
		private static final long serialVersionUID = -6157041650150998205L;

		GridPanel(GridLayout layout){
			super(layout);
		}
		
		//draw lines for 3x3 quardrants
		public void paintComponent(Graphics g){
			g.fillRect(getWidth()/3 - 2,0,4,getHeight());
			g.fillRect(2*getWidth()/3 - 1,0,3,getHeight());
			g.fillRect(0,getHeight()/3 - 1,getWidth(),3);
			g.fillRect(0,2*getHeight()/3 - 2,getWidth(),3);
		}
	}