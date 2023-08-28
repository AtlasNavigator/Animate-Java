import javax.swing.*;
import java.awt.*;

final public class Animate {

    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 200; // Starting X coordinate
    private int oneY = 200; // Starting Y coordinate
    private int sunOnly = 200; //Starting sun value

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
    
    int scale = 2; // Defines scale for the dot

    public static void main(String[] args) {
        new Animate().go();
    }

    private void go() {
        frame = new JFrame("DVD Screensaver");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setVisible(true);
        frame.setResizable(true);
        frame.setSize(300, 300);
        frame.setLocation(375, 55);
        moveDot();
    }

    class DrawPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3030379568821478211L;
		
		public void paintComponent(Graphics g) {
        	//White Border
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            //Black Border
            g.setColor(Color.BLACK);
            g.fillRect(3, 3, this.getWidth()-6, this.getHeight()-6);
            //Inside Color
            g.setColor(Color.PINK);
            g.fillRect(6, 6, this.getWidth()-12, this.getHeight()-12);
            //Sun
            g.setColor(Color.YELLOW);
            g.fillOval(40, sunOnly, this.getWidth()/4, this.getHeight()/4);
                        
            //Dot
            Color purple = new Color(128, 0, 128); //Make purple a color
            g.setColor(purple);
            g.fillOval(oneX - scale/2, oneY - scale/2, scale*6, scale*6); // This is the dot
//            g.fillRect(oneX-scale/2, oneY - scale*2, 2, 10);
//            g.fillRect(oneX - scale/2, oneY + scale*2, 10, 2);
//            g.fillOval(x, y, scale*6, scale*6);

            
            //Test Dot
            g.setColor(Color.white);
            g.fillRect(oneX, oneY, scale*2, scale*2);
        }
    }

    private void moveDot() {
        while(true){
            checkBounds();
            moveLoc();
            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
    private void moveLoc(){
    	if(up){
//            oneY--;
    		oneY++;
    		sunOnly++;
        }
        if(down){
//            oneY++;
        	oneY--;
        	sunOnly--;
        }
        if(left){
//            oneX--;
        	oneX++;
        }
        if(right){
//            oneX++;
        	oneX--;
        }	
    }
    private void checkBounds(){ //Edge Detection
    	if(oneX >= frame.getWidth()-30 - scale){
//            right = false;
//            left = true;
    		right = true;
    		left = false;
        }
        if(oneX <= 6){
//            right = true;
//            left = false;
        	right = false;
    		left = true;
        }
        if(oneY >= frame.getHeight()-27*scale - scale){
//            up = true;
//            down = false;
        	up = false;
            down = true;
        }
        if(oneY <= 6){
//            up = false;
//            down = true;
        	up = true;
            down = false;
        }	
        if(sunOnly <= frame.getHeight()/3) {
        	up = true;
        	down  = false;
        	
        }
        if(sunOnly >= frame.getHeight()-27*scale) {
        	up = false;
        	down = true;
        }
        	
    }
}