import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicDriver extends JPanel{
	Game game;
	
	public GraphicDriver(Game game){
        this.setPreferredSize(new Dimension(600,600));
		this.game = game;
	}

	public void paintComponent(Graphics g){
		super.repaint();
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 600);
        game.updateDraw(g);
        game.update();
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Dots");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game = new Game();
        GraphicDriver gd = new GraphicDriver(game);
        
        frame.getContentPane().add(gd);
        
        frame.pack();
        frame.setVisible(true);
	}
}
