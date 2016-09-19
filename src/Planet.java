
public class Planet{
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public Planet(double xP, double yP, double xV, double yV, 
			double m, String img ){
		myXPos= xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = img;
	}
	

	public Planet(Planet p){
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
		
				
	}

	public double calcDistance(Planet p1){
		
		double x1 = p1.myXPos;
		double y1 = p1.myYPos;
		double x2 = this.myXPos;
		double y2 = this.myYPos;
		
		double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		return distance;
	}
	public double calcForceExertedBy(Planet p1){
		double m1 = p1.myMass;
		double m2 = this.myMass;
		double distance = calcDistance(p1);
		
		double forceBetweenTwo = ((m1 * m2 * (6.67 * Math.pow(10, -11)))/ (distance*distance));
		return forceBetweenTwo;
	}
	public double calcForceExertedByX(Planet p1){
		double x1 = p1.myXPos;
		double x2 = this.myXPos;
		double force = calcForceExertedBy(p1);
		double distance = calcDistance(p1);
		
		double forceX = (force * (x1 - x2))/ distance;
		return forceX;
	}
	public double calcForceExertedByY(Planet p1){
		double y1 = p1.myYPos;
		double y2 = this.myYPos;
		double force = calcForceExertedBy(p1);
		double distance = calcDistance(p1);
		
		double forceY = (force * (y1 - y2))/ distance;
		return forceY;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double force1=0;
		double netForceX = 0;
		for (int k = 0; allPlanets.length > k; k++){
			if (! allPlanets[k].equals(this)) {
				force1 = 0;
			force1 = calcForceExertedByX(allPlanets[k]);
			netForceX += force1;
			}
		}
		return netForceX;
	}	
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double force1 = 0;
		double netForceY = 0;
		for (int k = 0; allPlanets.length > k; k++){
			if (! allPlanets[k].equals(this)){
				force1 = 0;
				force1 = calcForceExertedByY(allPlanets[k]);
			netForceY += force1;
			}
		}
		return netForceY;
	}
	public void update(double seconds, double xforce, double yforce){
		double m = myMass;
		double accelerationX = xforce/m;
		double accelerationY = yforce/m;
		myXVel = myXVel + (seconds * accelerationX);
		myYVel = myYVel + (seconds * accelerationY);
		myXPos = myXPos + seconds * myXVel;
		myYPos = myYPos + seconds * myYVel;
		
		
	}

	public static void drawer(Planet p1){
			StdDraw.picture(p1.myXPos, p1.myYPos, "images/" + p1.myFileName);
		
	}
}

