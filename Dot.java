import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class Dot{
	int width = 600, height = 600;
	QuerySet dots;
	double accel;
	String type;
	int mode;
	double x, y;
	Vector velocity;

	public Dot(String type, int mode, QuerySet dots, int x, int y){
		this.dots = dots;
		this.accel = 0.0;
		this.type = type;
		this.mode = mode;
		this.x = x;
		this.y = y;
		this.velocity = new Vector(0,0);
	}
	
	public void updateDraw(Graphics g){
		if(type.equals("Runner")){
			g.setColor(Color.blue);
		}else{
			g.setColor(Color.magenta);
		}
		g.drawOval((int) x, (int) y, 2, 2);
	}
	
	public void update(){
		if(type.equals("Runner")){
			run();
		}else{
			chase();
		}
		if(x + velocity.x >= 0 && x + velocity.x < width) x += velocity.x;
		if(y + velocity.y >= 0 && y + velocity.y < height) y += velocity.y;
	}
	
	public void run(){
		Dot closest_dot = closestDot("Chaser", this);
		double distance = distance(this, closest_dot);
		Vector wall_priority = wallPriority();
		
		
		double xvector =  (this.x-closest_dot.x)/3+wall_priority.x*wall_priority.x*wall_priority.x*(width/2-this.x);
		double yvector =  (this.x-closest_dot.x)/3+wall_priority.y*wall_priority.y*wall_priority.y*(height/2-this.y);
		//System.out.println(this + ": (" + xvector + "," + yvector + ")");
		//double xvector = (this.x-closest_dot.x) + 500*wall_priority.y - 1000000000*wall_priority.x*wall_priority.x*wall_priority.x*(this.x-closest_dot.x);
		//double yvector = (this.x-closest_dot.x) + 500*wall_priority.x - 1000000000*wall_priority.y*wall_priority.y*wall_priority.y*(this.x-closest_dot.x);
		double vector_hat = Math.sqrt(xvector*xvector + yvector*yvector);
		velocity = new Vector(xvector/vector_hat, yvector/vector_hat);
	}
	
	public void chase(){
		Dot closest_dot = closestDot("Runner", this);
		double distance = distance(this, closest_dot);
		double xvector = closest_dot.x-this.x;
		double yvector = closest_dot.y-this.y;
		double vector_hat = Math.sqrt(xvector*xvector + yvector*yvector);
		velocity = new Vector(xvector/vector_hat/2, yvector/vector_hat/2);	
	}
	
	public Dot closestDot(String type, Dot origin){
		double min_distance = Double.MAX_VALUE;
		Dot closest_dot = null;		
		Object[] type_dots = ((Set) this.dots.get(type)).toArray(); 
		for(Object d : type_dots){
			if(d!=origin && min_distance > distance(origin, (Dot) d)){
				closest_dot = (Dot) d;
			}
		}
		return closest_dot;
	}
	public double distance(Dot dot1, Dot dot2){
		return Math.sqrt((dot1.x - dot2.x)*(dot1.x - dot2.x) + (dot1.y - dot2.y)*(dot1.y - dot2.y)); 
	}
	
	//returns wall priority
	public Vector wallPriority(){
		double xpriority = velocity.x/(this.x - width/2);
		double ypriority = velocity.y/(this.y - height/2);
		if(xpriority < 0){
			xpriority = 0;
		}
		if(ypriority < 0){
			ypriority = 0;
		}
		return new Vector(xpriority, ypriority);
	}
	
	private class Vector{
		double x, y;
		public Vector(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
}






