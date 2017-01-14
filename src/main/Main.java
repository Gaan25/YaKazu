package main;
import jeu.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tab[][] = {	{3, -1,-1,-1,0,-1},
						{2,0,-1,-1,1,0},
						{0, -1,1,0,2,-1},
						{0,0,-1,1,0,0},
						{5,3,0,2,0,0},
						{-1,0,-1,0,-1,0}};
		Tableau t = new Tableau(tab,6);
		
		//t.afficherGrille();

		try {
			//Pas finis generer (marche une 1 fois sur 2)
			t.genererGrillee(1,6);
			t.afficherGrille();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}