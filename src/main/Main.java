package main;
import jeu.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Modeles modeles = new Modeles();
		modeles.sauvegarderModeles();
		int grille1 [][]= modeles.getModele1();
		int grille2 [][]= modeles.getModele2();
		int grille3 [][]= modeles.getModele3();
		int grille4 [][]= modeles.getModele4();
		int grille5 [][]= modeles.getModele5();
		int grille6 [][]= modeles.getModele6();
		*/
		Tableau t = new Tableau(9);

		try {
			//Pas finis generer (marche une 1 fois sur 2)
			t.genererGrillee(1,9);
			t.estValide(0);
			System.out.println("La grille générée résolue par estValide()");
			t.afficherGrille();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}