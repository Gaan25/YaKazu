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
		int grille7 [][]=modeles.getModele7(); //ATTENTION 6x6 NON remplie 
		int grille8 [][]=modeles.getModele8(); //grille 6x6 remplie
		int grille9 [][]=modeles.getModele9(); // la meme remplie differemment
		int grille10[][]=modeles.getModele10(); // la meme mal remplie
		*/
		/*

		Tableau t = new Tableau(6);
		
		try {
			t.afficherGrille();
			System.out.print("La grille est : ");
			if (t.grilleValide()==true)
				System.out.println("VALIDE");
			else System.out.println("Non VALIDE");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		try {
			//Pas finis generer (marche une 1 fois sur 2)
			//t.genererGrillee(1, 6);
			long tempsDebut;
			long tempsFin;
			tempsDebut = System.currentTimeMillis();
			t.estValide(0,System.currentTimeMillis());
			tempsFin = System.currentTimeMillis();
			System.out.println("La grille g�n�r� r�solue par estValide()");
			long temps = tempsFin-tempsDebut;
			System.out.println("Temps de resolution : "+ temps+" millisecondes");
			t.afficherGrille();
			System.out.print("La grille est : ");
			if (t.grilleValide()==true)
				System.out.println("VALIDE");
			else System.out.println("Non VALIDE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		//new Modeles().sauvegarderModeles();
		Jeu jeu = new Jeu();
		jeu.commencer();

	}

}