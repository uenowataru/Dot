import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Game{
	QuerySet dots;
	ArrayList<Dot> dotlist;
	public Game(){
		dots = new QuerySet<String, Dot>();
		createDots(3);
	}
	public void createDots(int numdots){
		Random r = new Random();
		for(;numdots > 0; numdots--){
			dots.add("Runner", new Dot("Runner", 0,dots, r.nextInt(600), r.nextInt(600)));
			dots.add("Chaser", new Dot("Chaser", 1,dots, r.nextInt(600), r.nextInt(600)));
		}
	}
	
	public void updateDraw(Graphics g){
		this.dotlist = dots.getDots();
		for(Dot d : dotlist){
			d.updateDraw(g);
		}
	}
	
	public void update(){
		for(Dot d : dotlist){
			d.update();
		}
	}
}




