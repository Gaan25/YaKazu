
public class Point {
private int x,y;


public Point (){
	x=0;
	y=0;
}

public Point (final int x, final int y){
	this.x=x;
	this.y=y;
}

void deplacer (final int x , final int y)
{
	this.x=x+this.x;
	this.y=y+this.y;
}

void afficher (){
	System.out.println(x+","+y);
}

int distance (Point p){
	return (x-p.x)+(y-p.y);
}

boolean egaux (Point p1, Point p2){ // =return (p1.x==p2.x && p1.y==p2.y)
	if (p1.x==p2.x && p1.y==p2.y)
		return true;
	else
		return false;
}

boolean egaux (Point p){
	if (x==p.x && y==p.y){
		return true;
	}
	else
		return false ;
}

static Point dupliquer (Point p1){
	Point p2 = new Point(p1.x,p1.y);
	return p2;
}

}
