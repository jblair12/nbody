import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NBody {
	
	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		//Planet[] planets = null;
		Planet[] planets = readPlanets(pfile);
		double radius = 0.0;
		radius += readRadius(pfile);
		StdDraw.setScale(-radius, radius);


		
		for (double t =0; t< T; t= t + dt){
			double xForces[] = new double[5];
			double yForces[] = new double[5];
			for (int i = 0; i< planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				

			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			for (int s = 0; s< planets.length; s++){
				planets[s].update(dt, xForces[s], yForces[s]);
				Planet.drawer(planets[s]);
				xForces[s] = 0;
				yForces[s] = 0;
				
			}
			StdDraw.show(10);
				
				
			
		}
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	 
		}		
	}


	public static double readRadius(String fname){
		double rad = 0;	
			try { 
				File f = new File(fname);
				Scanner sc = new Scanner(f);
				
				sc.nextDouble();
				rad = sc.nextDouble();
				sc.close();
			}
			catch (FileNotFoundException fnf){
				fnf.printStackTrace();
				return -1;
			}
			return rad;

	}
	
	
	public static Planet[] readPlanets(String fname){
		Planet planets[];
		try{
			File f = new File(fname);
			Scanner sc = new Scanner(f);
			int numplanet = 0;
			numplanet = sc.nextInt();
			sc.nextDouble();
			planets = new Planet[numplanet];
			for (int i = 0 ; i< numplanet; i++){
				double myXPos = sc.nextDouble();
				double myYPos = sc.nextDouble();
				double myXVel = sc.nextDouble();
				double myYVel = sc.nextDouble();
				double myMass = sc.nextDouble();
				String myFileName = sc.next();
				planets[i] = new Planet(myXPos, myYPos, myXVel, myYVel, myMass, myFileName);
			}
			sc.close();
		}
		catch(FileNotFoundException fnf){
			planets = new Planet[1];
			fnf.printStackTrace();
			return planets;
		}
		return planets;	
	}

	
}

