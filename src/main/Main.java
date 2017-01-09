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
		
	/*	if (t.absentLigne(5,4)==true){
			System.out.println("5 n'est pas pr�sent sur la ligne 5");
		}
		else System.out.println("5 est pr�sent sur la ligne 5");
		
		if (t.absentColonne(3,0)==true){
			System.out.println("5 n'est pas pr�sent sur la colonne 5");
		}
		else System.out.println("3 est pr�sent sur la colonne 0");
	
	
		System.out.println("la position 6 est sur la ligne :" + t.getLigne(6));
		System.out.println("la position 6 est sur la colonne :" + t.getColonne(6));
		
		System.out.println("a la position 6 se situe"+ t.getCase(6));
		System.out.println("a la position 0 se situe"+ t.getCase(0));
		System.out.println("a la position 5 se situe"+ t.getCase(5));
		System.out.println("a la position 35 se situe"+ t.getCase(35));
		*/
		//t.afficherGrille();

		try {
			t.genererGrillee(1,6);
			t.afficherGrille();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//t.estValide(0);
		
		//t.afficherGrille();
	}

}
